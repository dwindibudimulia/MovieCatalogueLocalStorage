package com.example.dwindibudimulia.moviecatalogue.db;

import android.provider.BaseColumns;

class DatabaseContract {
    static final class MovieColumns implements BaseColumns {
        static final String TABLE_MOVIE = "movie";

        static final String NAME_MOVIE = "name_movie";
        static final String POSTER_PATH = "poster_path";
        static final String BACKDROP = "backdrop";
        static final String RATING_MOVIE = "rating_movie";
        static final String RELEASE_DATE = "release_date";
        static final String OVERVIEW = "overview";
        static final String GENRE_MOVIE = "genre_movie";
        static final String LANGUAGE = "language";
    }

    static final class TvShowColumns implements BaseColumns {
        static final String TABLE_TV = "tv_show";

        static final String NAME_TV = "name_tv";
        static final String POSTER_PATH = "poster_path";
        static final String BACKDROP = "backdrop";
        static final String RATING_TV = "rating_TV";
        static final String RELEASE_DATE = "release_date";
        static final String OVERVIEW = "overview";
        static final String GENRE_TV = "genre_TV";
        static final String LANGUAGE = "language";
    }
}
