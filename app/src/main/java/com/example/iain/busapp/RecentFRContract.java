package com.example.iain.busapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by iain on 13/11/14.
 */
public final class RecentFRContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public RecentFRContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String RECENT_TABLE = "history";
        public static final String RECENT_TIMESTAMP= "timestamp";
        public static final String RECENT_ROUTE = "route";
        public static final String RECENT_FROM = "from";
        public static final String RECENT_TO = "to";
        public static final String RECENT_DEPARTURE = "departure";
        public static final String RECENT_COST = "cost";
        public static final String RECENT_STATUS = "status";


        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.RECENT_TABLE + " (" +

                        FeedEntry._ID + " INT NOT NULL PRIMARY KEY AUTO_INCREMENT" + COMMA_SEP +
                        FeedEntry.RECENT_TIMESTAMP + " TIMESTAMP DEFAULT NOW()" + COMMA_SEP +
                        FeedEntry.RECENT_ROUTE + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.RECENT_FROM + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.RECENT_TO + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.RECENT_DEPARTURE + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.RECENT_COST + " DECIMAL(4,2)" + COMMA_SEP +
                        FeedEntry.RECENT_STATUS + " TEXT_TYPE" + COMMA_SEP +
                " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.RECENT_TABLE;


        public class RecentFRDbHelper extends SQLiteOpenHelper {
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            public RecentFRDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
            }
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // This database is only a cache for online data, so its upgrade policy is
                // to simply to discard the data and start over
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }
        }
    }

}
