package com.example.test_khevoyan.ui.movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test_khevoyan.db.TestApp;


public class MovieViewModel extends ViewModel {
    MutableLiveData<String> genresString = new MutableLiveData<>();
    public void getGenres(String[] genresTd){

        new Thread(){
            @Override
            public void run() {
                String genres ="";
                for(int i =0; i<genresTd.length;i++){
                   genres = genres + TestApp.getInstance().getDatabase().moviesDao().getGenreById(genresTd[i]);
                   if(i!=genresTd.length-1){
                       genres = genres + ", ";
                   }


                }
                genresString.postValue(genres);

            }
        }.start();


    }

}
