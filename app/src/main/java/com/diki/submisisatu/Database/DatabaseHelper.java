package com.diki.submisisatu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.diki.submisisatu.Database.DatabaseContract.TABLE_FAVORITE;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmovieapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAVOURITE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT)",
            TABLE_FAVORITE,
            DatabaseContract.FavouriteColumns._ID,
            DatabaseContract.FavouriteColumns.MOVIE_ID,
            DatabaseContract.FavouriteColumns.TITLE,
            DatabaseContract.FavouriteColumns.RELEASE_DATE,
            DatabaseContract.FavouriteColumns.VOTE_COUNT,
            DatabaseContract.FavouriteColumns.OVERVIEW,
            DatabaseContract.FavouriteColumns.ORIGINAL_LANGUAGE,
            DatabaseContract.FavouriteColumns.POSTER_URL,
            DatabaseContract.FavouriteColumns.CATEGORY
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAVOURITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(db);
    }
}
