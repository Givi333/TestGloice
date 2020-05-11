package com.example.test_khevoyan.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.test_khevoyan.response.Genres;
import com.example.test_khevoyan.response.Movies;
import com.example.test_khevoyan.response.Results;

@Database(entities = {Movies.class, Genres.class}, version = 1)
public abstract class TestDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}