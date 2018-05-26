package com.example.mshehab.db_contacts.DB;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mshehab on 12/4/17.
 */

public class PersonsTable {
    public static final String TABLENAME = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DEPT = "dept";
    public static final String COLUMN_IMG = "image";


    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_NAME + " text not null, ");
        sb.append(COLUMN_PHONE + " text not null, ");
        sb.append(COLUMN_EMAIL + " text not null, ");
        sb.append(COLUMN_DEPT + " text not null, ");
        sb.append(COLUMN_IMG + " integer not null");
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
            PersonsTable.onCreate(db);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
