package com.example.test_khevoyan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test_khevoyan.interfaces.ActivityAction;
import com.example.test_khevoyan.response.Results;
import com.example.test_khevoyan.ui.main.MainFragment;
import com.example.test_khevoyan.ui.movie.MovieFragment;



public class MainActivity extends AppCompatActivity implements ActivityAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new MainFragment().newInstance())
                .commit();

    }




    @Override
    public void openMovieFragment(Results results) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new MovieFragment().newInstance(results))
                .addToBackStack(null)
                .commit();
    }
}
