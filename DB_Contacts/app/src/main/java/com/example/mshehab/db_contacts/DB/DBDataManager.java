package com.example.mshehab.db_contacts.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mshehab on 12/4/17.
 */

public class DBDataManager {
    private Context mContext;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private PersonDAO personDAO;

    public DBDataManager(Context context) {
        this.mContext = context;
        dbOpenHelper = new DBOpenHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        personDAO = new PersonDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }
}
