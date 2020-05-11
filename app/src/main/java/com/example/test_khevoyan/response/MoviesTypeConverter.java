package com.example.test_khevoyan.response;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MoviesTypeConverter {
    @TypeConverter
    public static Results[] toResults(String value) {
        Type results = new TypeToken<Results[]>() {}.getType();
        return new Gson().fromJson(value, results);
    }

    @TypeConverter
    public static String fromResults(Results[] results) {
        Gson gson = new Gson();
        String json = gson.toJson(results);
        return json;
    }
}
