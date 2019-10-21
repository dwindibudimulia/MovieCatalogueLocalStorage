package com.example.dwindibudimulia.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class TvShow implements Parcelable {

    private int id;

    private String backDropTv;

    private String photoTv;

    private String nameTv;

    private String ratingTv;

    private String descriptionTv;

    private String dateTv;

    private String genreTv;

    private String language;

    // Getter
    public int getId() {
        return id;
    }

    public String getBackDropTv() {
        return backDropTv;
    }

    public String getLanguage() {
        return language;

    }


    public String getRatingTv() {
        return ratingTv;
    }

    public String getgenreTv() {
        return genreTv;
    }

    public String getPhotoTv() {
        return photoTv;
    }

    public String getnameTv() {
        return nameTv;
    }

    public String getdescriptionTv() {
        return descriptionTv;
    }

    public String getdateTv() {
        return dateTv;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setBackDropTv(String backDropTv) {
        this.backDropTv = backDropTv;
    }


    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRatingTv(String ratingTv) {
        this.ratingTv = ratingTv;
    }

    public void setgenreTv(String genreTv) {

        this.genreTv = genreTv;
    }

    public void setPhotoTv(String photoTv) {
        this.photoTv = photoTv;
    }

    public void setnameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public void setdescriptionTv(String descriptionTv) {
        this.descriptionTv = descriptionTv;
    }

    public void setdateTv(String dateTv) {
        this.dateTv = dateTv;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.backDropTv);
        dest.writeString(this.photoTv);
        dest.writeString(this.nameTv);
        dest.writeString(this.ratingTv);
        dest.writeString(this.descriptionTv);
        dest.writeString(this.dateTv);
        dest.writeString(this.genreTv);
        dest.writeString(this.language);
    }

    // JSON
    public TvShow(JSONObject tvShow) {
        try {
            this.id = tvShow.getInt("id");
            this.nameTv = tvShow.getString("name");
            this.ratingTv = String.valueOf(tvShow.getDouble("vote_average"));
            this.dateTv = tvShow.getString("first_air_date");
            this.genreTv = tvShow.getString("genre_ids");
            this.descriptionTv = tvShow.getString("overview");
            this.language = tvShow.getString("original_language");
            String photoTv = tvShow.getString("poster_path");
            String backdropTv = tvShow.getString("backdrop_path");
            this.backDropTv = "https://image.tmdb.org/t/p/w185/" + backdropTv;
            this.photoTv = "https://image.tmdb.org/t/p/w185/" + photoTv;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Data", e.getMessage());
        }
    }

    TvShow(Parcel in) {
        this.id = in.readInt();
        this.backDropTv = in.readString();
        this.photoTv = in.readString();
        this.nameTv = in.readString();
        this.ratingTv = in.readString();
        this.descriptionTv = in.readString();
        this.dateTv = in.readString();
        this.genreTv = in.readString();
        this.language = in.readString();
    }

    public TvShow() {

    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
