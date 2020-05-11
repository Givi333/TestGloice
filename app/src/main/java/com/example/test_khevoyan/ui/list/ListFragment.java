package com.example.test_khevoyan.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_khevoyan.R;
import com.example.test_khevoyan.interfaces.ActivityAction;
import com.example.test_khevoyan.response.Movies;
import com.example.test_khevoyan.response.Results;


import java.util.ArrayList;
import java.util.Collections;

public class ListFragment extends Fragment {

    private ActivityAction activityAction;
    private RecyclerView moviesGrid;
    private MoviesAdapter moviesAdapter;
    private ListViewModel mViewModel;


    public static ListFragment newInstance(){
        return new ListFragment();
    }

    public ListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel =  ViewModelProviders.of(this).get(ListViewModel.class);
        initObserves();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getAllGenres();
        mViewModel.getAllPopularNetwork();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityAction = (ActivityAction)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        moviesGrid = view.findViewById(R.id.moviesGrid);
    }

    private void initObserves(){
        mViewModel.movies.observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                setInfo(movies);
            }
        });

        mViewModel.networkError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mViewModel.getFromBase();
            }
        });
    }

    private void setInfo(Movies movies){
        moviesAdapter = new MoviesAdapter(new MoviesAdapter.MoviesAction() {
            @Override
            public void itemClick(Results results) {
                activityAction.openMovieFragment(results);
            }
        });
        ArrayList<Results> resultsList = new ArrayList();
        Collections.addAll(resultsList, movies.getResults());
        moviesAdapter.setData(resultsList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        moviesGrid.setLayoutManager(mLayoutManager);
        moviesGrid.setAdapter(moviesAdapter);
    }

}

