package com.example.dwindibudimulia.moviecatalogue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwindibudimulia.moviecatalogue.R;
import com.example.dwindibudimulia.moviecatalogue.activity.ListMovieFavoriteActivity;
import com.example.dwindibudimulia.moviecatalogue.activity.MovieDetailActivity;
import com.example.dwindibudimulia.moviecatalogue.adapter.MovieAdapter;
import com.example.dwindibudimulia.moviecatalogue.itemClick.ItemClickSupport;
import com.example.dwindibudimulia.moviecatalogue.model.Movie;
import com.example.dwindibudimulia.moviecatalogue.viewmodel.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private MovieViewModel movieViewModel;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_movie);
        showLoading(true);

        showRecyclerList(view);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.setMovie();
        movieViewModel.getMovie().observe(this, getMovie);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ListMovieFavoriteActivity.class);
            startActivity(intent);
        });
        showLoading(true);
        return;
    }

    private final Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                movieAdapter.setMovies(movies);
                showLoading(false);
                ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) ->
                        showSelectedMovie(movies.get(position)));
            }
        }
    };

    private void showRecyclerList(View view) {
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, view.isInLayout()));
        recyclerView.setAdapter(movieAdapter);

    }

    private void showSelectedMovie(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
