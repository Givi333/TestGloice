package com.example.test_khevoyan.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_khevoyan.R;
import com.example.test_khevoyan.ui.list.ListFragment;

public class MainFragment extends Fragment {


    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initUi(view);
        return view;
    }


    private void initUi(View view){
        Button bt = view.findViewById(R.id.start_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });
    }

    private void addFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ListFragment().newInstance())
                .addToBackStack(null)
                .commit();
    }

}
