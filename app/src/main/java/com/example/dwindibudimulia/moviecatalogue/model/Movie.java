package com.example.dwindibudimulia.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class Movie implements Parcelable {

    private int id;

    private String posterPath;

    private String backDrop;

    private String nameMovie;

    private String ratingMovie;

    private String descriptionMovie;

    private String dateMovie;

    private String genreMovie;

    private String language;

    //JSON
    public Movie(JSONObject movie) {
        try {
            this.id = movie.getInt("id");
            this.nameMovie = movie.getString("original_title");
            this.ratingMovie = String.valueOf(movie.getDouble("vote_average"));
            this.descriptionMovie = movie.getString("overview");
            this.dateMovie = movie.getString("release_date");
            this.genreMovie = movie.getString("genre_ids");
            this.language = movie.getString("original_language");
            String poster = movie.getString("poster_path");
            String backdrop = movie.getString("backdrop_path");
            this.backDrop = "https://image.tmdb.org/t/p/w185/" + backdrop;
            this.posterPath = "https://image.tmdb.org/t/p/w185/" + poster;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Data", e.getMessage());
        }
    }

    public Movie() {

    }


    // Getter
    public int getId() {
        return id;
    }

    public String getnameMovie() {
        return nameMovie;
    }

    public String getRatingMovie() {
        return ratingMovie;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackDrop() {
        return backDrop;
    }

    public String getgenreMovie() {
        return genreMovie;
    }

    public String getdateMovie() {
        return dateMovie;
    }

    public String getdescriptionMovie() {
        return descriptionMovie;
    }

    public String getLanguage() {
        return language;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRatingMovie(String ratingMovie) {
        this.ratingMovie = ratingMovie;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setBackDrop(String backDrop) {
        this.backDrop = backDrop;
    }

    public void setgenreMovie(String genreMovie) {
        this.genreMovie = genreMovie;
    }


    public void setdateMovie(String dateMovie) {
        this.dateMovie = dateMovie;
    }


    public void setnameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }


    public void setdescriptionMovie(String descriptionMovie) {
        this.descriptionMovie = descriptionMovie;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.backDrop);
        dest.writeString(this.nameMovie);
        dest.writeString(this.ratingMovie);
        dest.writeString(this.descriptionMovie);
        dest.writeString(this.dateMovie);
        dest.writeString(this.genreMovie);
        dest.writeString(this.language);
    }

    Movie(Parcel in) {
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.backDrop = in.readString();
        this.nameMovie = in.readString();
        this.ratingMovie = in.readString();
        this.descriptionMovie = in.readString();
        this.dateMovie = in.readString();
        this.genreMovie = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
