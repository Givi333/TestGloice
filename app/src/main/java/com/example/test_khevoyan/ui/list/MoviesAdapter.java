package com.example.test_khevoyan.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_khevoyan.R;
import com.example.test_khevoyan.response.Results;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private MoviesAction moviesAction;
    private List<Results> resultsList = new ArrayList<>();
    protected MoviesAdapter(MoviesAction moviesAction){
        this.moviesAction = moviesAction;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(resultsList.get(position));
    }

    public void setData(ArrayList<Results>resultsList){
        this.resultsList = resultsList;
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        private ConstraintLayout moviesConstraint;

        public MovieViewHolder(View itemView) {
            super(itemView);
            moviesConstraint = itemView.findViewById(R.id.movies_constraint);
            imageView = itemView.findViewById(R.id.movies_pict);
            title = itemView.findViewById(R.id.movies_title);

        }


        public void bind(Results results) {
            Glide.with(imageView.getContext())
                    .load("http://image.tmdb.org/t/p/w185/" + results.getPoster_path())
                    .into(imageView)
                    .waitForLayout();
            title.setText(results.getTitle());
            moviesConstraint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moviesAction.itemClick(results);
                }
            });



        }

    }

    public interface MoviesAction {
        void itemClick(Results results);
    }
}
