package com.example.test_khevoyan.ui.list;

import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test_khevoyan.db.TestApp;
import com.example.test_khevoyan.db.TestDatabase;
import com.example.test_khevoyan.db.TestService;
import com.example.test_khevoyan.response.GenreResponse;
import com.example.test_khevoyan.response.Genres;
import com.example.test_khevoyan.response.Movies;
import com.example.test_khevoyan.response.Results;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListViewModel extends ViewModel {

    private String baseUrl = "https://api.themoviedb.org/";
    MutableLiveData<Movies> movies = new MutableLiveData<>();
    MutableLiveData<Boolean> networkError = new MutableLiveData<>();

    private TestService getTestService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TestService.class);
    }

    public void getAllPopularNetwork() {

        getTestService().getAllPopular().enqueue(new Callback<Movies>() {

            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies fromServer = response.body();

                new Thread(){
                    @Override
                    public void run() {
                        TestApp.getInstance().getDatabase().moviesDao().insert(fromServer);
                    }
                }.start();

                movies.postValue(fromServer);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                networkError.postValue(true);
            }
        });

    }

    public void getFromBase(){
        new Thread(){
            @Override
            public void run() {
                List<Movies> list =  TestApp.getInstance().getDatabase().moviesDao().getAll();
                if(list.size()>0){
                    movies.postValue(list.get(0));
                }
            }
        }.start();
    }


    public void getAllGenres() {
        getTestService().getAllGenres().enqueue(new Callback<GenreResponse>() {

            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {

                Genres[] genres= response.body().getGenres();

                new Thread(){
                    @Override
                    public void run() {
                        for(int i =0; i<genres.length; i++){
                            TestApp.getInstance().getDatabase().moviesDao().insert(genres[i]);
                        }

                    }
                }.start();
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                networkError.postValue(true);
            }
        });
    }
}
