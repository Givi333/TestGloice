package com.example.test_khevoyan.response;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ResultsTypeConverter {
    @TypeConverter
    public static String[] toGenresIds(String value) {
        Type results = new TypeToken<String[]>() {}.getType();
        return new Gson().fromJson(value, results);
    }

    @TypeConverter
    public static String fromGenresIds(String[] genres) {
        Gson gson = new Gson();
        String json = gson.toJson(genres);
        return json;
    }
}
