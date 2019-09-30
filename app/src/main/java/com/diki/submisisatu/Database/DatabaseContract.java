package com.diki.submisisatu.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {

    public static String TABLE_FAVORITE = "favorite";

    public static final class FavouriteColumns implements BaseColumns {

        public static String MOVIE_ID = "movie_id";

        public static String TITLE = "title";

        public static String RELEASE_DATE = "release_date";


        public static String VOTE_COUNT = "vote_count";

        public static String OVERVIEW = "overview";


        public static String ORIGINAL_LANGUAGE = "original_language";


        public static String POSTER_URL = "poster_url";

        public static String BACKDROP_PATH = "backdrop";

        public static final String CATEGORY = "category";



    }

    public static final String AUTHORITY = "com.diki.submisisatu;";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }
}
