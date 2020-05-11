package com.example.test_khevoyan.ui.movie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.test_khevoyan.R;
import com.example.test_khevoyan.interfaces.ActivityAction;
import com.example.test_khevoyan.response.Results;
import com.example.test_khevoyan.ui.list.ListViewModel;

public class MovieFragment extends Fragment {
    private MovieViewModel mViewModel;
    private Results results;
    private TextView moviesHead;
    private ImageView movieIv;
    private TextView desc;
    private TextView originalLanguage;
    private TextView genres;
    private TextView adult;
    private TextView popularity;
    private TextView vote;

    public static MovieFragment newInstance(Results results) {
        MovieFragment movieFragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putParcelable("results", results);
        movieFragment.setArguments(args);
        return movieFragment;
    }

    public MovieFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        this.results = getArguments().getParcelable("results");
        initObservers();

    }

    private void initObservers() {
        mViewModel.genresString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                genres.setText(s);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_movie, null);
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        initUI(view);
        return view;
    }


    private void initUI(View view) {
        moviesHead = view.findViewById(R.id.movies_head);
        movieIv = view.findViewById(R.id.movie_image);
        desc = view.findViewById(R.id.desc);
        originalLanguage = view.findViewById(R.id.original_langauge_value);
        genres = view.findViewById(R.id.genres_value);
        adult = view.findViewById(R.id.adult_value);
        popularity = view.findViewById(R.id.popularity_value);
        vote = view.findViewById(R.id.vote_value);
        setInfo();

   }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setInfo() {
        moviesHead.setText(results.getTitle());
        Glide.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/" + results.getPoster_path())
                .into(movieIv)
                .waitForLayout();
        desc.setText(results.getOverview());
        originalLanguage.setText(results.getOriginal_language());
        String genresString ="";
        for(int i = 0; i<results.getGenre_ids().length;i++){
           genresString = genresString + results.getGenre_ids()[i] + ", ";
        }
       mViewModel.getGenres(results.getGenre_ids());
        if(results.getAdult().equals("true")){
            adult.setText("Yes");
        }else{
            adult.setText("No");
        }
        popularity.setText(results.getPopularity());
        vote.setText(results.getVote_average()+ " (votes - " + results.getVote_count() + ")");
    }
}
