package com.example.mshehab.db_contacts.DB;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mshehab on 12/4/17.
 */

public class NotesTable {
    public static final String TABLENAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_TEXT = "text";

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_SUBJECT + " text not null, ");
        sb.append(COLUMN_TEXT + " text not null");
        sb.append(");");

        try{
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLENAME + ";");
            NotesTable.onCreate(db);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
