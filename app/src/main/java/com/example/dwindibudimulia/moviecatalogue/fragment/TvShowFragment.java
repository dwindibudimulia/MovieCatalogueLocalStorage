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
import com.example.dwindibudimulia.moviecatalogue.activity.ListTvShowFavoriteActivity;
import com.example.dwindibudimulia.moviecatalogue.activity.TvShowDetailActivity;
import com.example.dwindibudimulia.moviecatalogue.adapter.TvShowAdapter;
import com.example.dwindibudimulia.moviecatalogue.itemClick.ItemClickSupport;
import com.example.dwindibudimulia.moviecatalogue.model.TvShow;
import com.example.dwindibudimulia.moviecatalogue.viewmodel.TvShowViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {
    private RecyclerView rvTvShow;
    private TvShowAdapter tvShowAdapter;
    private TvShowViewModel tvShowViewModel;
    private ProgressBar progressBarTv;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        progressBarTv = view.findViewById(R.id.progress_tv);
        showLoading(true);

        showRecyclerTvShow(view);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.setTvShow();
        tvShowViewModel.gettvShow().observe(this, getTvShow);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ListTvShowFavoriteActivity.class);
            startActivity(intent);
        });
        showLoading(true);
        return;
    }

    private final Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                tvShowAdapter.setTvShow(tvShows);
                showLoading(false);
                ItemClickSupport.addTo(rvTvShow).setOnItemClickListener((recyclerView, position, v) ->
                        showSelectedTvShow(tvShows.get(position)));
            }
        }
    };

    private void showRecyclerTvShow(View view) {
        tvShowAdapter = new TvShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, view.isInLayout()));
        rvTvShow.setAdapter(tvShowAdapter);

    }

    private void showSelectedTvShow(TvShow tvShow) {
        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(intent);
    }


    private void showLoading(Boolean state) {
        if (state) {
            progressBarTv.setVisibility(View.VISIBLE);
        } else {
            progressBarTv.setVisibility(View.GONE);
        }
    }
}
