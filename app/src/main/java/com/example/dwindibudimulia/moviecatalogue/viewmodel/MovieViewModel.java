package com.example.dwindibudimulia.moviecatalogue.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dwindibudimulia.moviecatalogue.BuildConfig;
import com.example.dwindibudimulia.moviecatalogue.R;
import com.example.dwindibudimulia.moviecatalogue.activity.MainActivity;
import com.example.dwindibudimulia.moviecatalogue.db.MovieHelper;
import com.example.dwindibudimulia.moviecatalogue.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private static final String NULL_CONDITION = BuildConfig.IMG_URL_NULL;

    public void setMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> movieListItem = new ArrayList<>();
        String API_KEY = "API_KEY";
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray movList = responseObject.getJSONArray("results");
                    for (int i = 0; i < movList.length(); i++) {
                        JSONObject movie = movList.getJSONObject(i);
                        Movie itemMovie = new Movie(movie);
                        if (!itemMovie.getBackDrop().equals(NULL_CONDITION) && !itemMovie.getRatingMovie().equals("0.0"))
                            movieListItem.add(itemMovie);
                    }
                    movieList.postValue(movieListItem);
                } catch (Exception e) {
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                    Toast.makeText(MainActivity.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    movieList.postValue(movieListItem);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure", Objects.requireNonNull(error.getMessage()));
                Toast.makeText(MainActivity.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                movieList.postValue(movieListItem);
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovie() {
        return movieList;
    }

    public void setEmptyMovie() {
        ArrayList<Movie> movieListItem = new ArrayList<>();
        movieList.postValue(movieListItem);
    }

    public void setMovieFromDB(Context context) {
        MovieHelper helper = MovieHelper.getInstance(context);
        helper.open();
        ArrayList<Movie> movieListItem = helper.getALLMovie();
        movieList.postValue(movieListItem);
        if (movieListItem.isEmpty())
            Toast.makeText(context, context.getString(R.string.empty_list), Toast.LENGTH_LONG).show();

    }

}
