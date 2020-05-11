package com.example.test_khevoyan.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.test_khevoyan.response.Genres;
import com.example.test_khevoyan.response.Movies;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM movies")
    List<Movies> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movies movies);

    @Update
    void update(Movies movies);

    @Delete
    void delete(Movies movies);

    @Query("SELECT * FROM genres")
    List<Genres> getAllGenres();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Genres genres);

    @Update
    void update(Genres genres);

    @Delete
    void delete(Genres genres);

    @Query("SELECT name FROM genres where id= :id")
    String getGenreById(String id);


}
