package com.example.test_khevoyan.db;

import com.example.test_khevoyan.response.GenreResponse;
import com.example.test_khevoyan.response.Movies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET ("3/movie/popular?api_key=38e11407df722544ead3027c43b34507")
    Call<Movies> getAllPopular();

    @GET ("3/genre/movie/list?api_key=38e11407df722544ead3027c43b34507")
    Call<GenreResponse> getAllGenres();
}
