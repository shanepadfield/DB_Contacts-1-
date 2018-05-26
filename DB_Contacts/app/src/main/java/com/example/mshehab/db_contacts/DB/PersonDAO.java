package com.example.mshehab.db_contacts.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mshehab.db_contacts.Person;

import java.util.ArrayList;

/**
 * Created by mshehab on 12/4/17.
 */

public class PersonDAO {
    private SQLiteDatabase db;

    public PersonDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsTable.COLUMN_NAME, person.getName());
        values.put(PersonsTable.COLUMN_EMAIL, person.getEmail());
        values.put(PersonsTable.COLUMN_PHONE, person.getPhone());
        values.put(PersonsTable.COLUMN_DEPT, person.getDept());
        values.put(PersonsTable.COLUMN_IMG, person.getPicID());
        return db.insert(PersonsTable.TABLENAME, null, values);
    }

    public boolean update(Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonsTable.COLUMN_NAME, person.getName());
        values.put(PersonsTable.COLUMN_EMAIL, person.getEmail());
        values.put(PersonsTable.COLUMN_PHONE, person.getPhone());
        values.put(PersonsTable.COLUMN_DEPT, person.getDept());
        values.put(PersonsTable.COLUMN_IMG, person.getPicID());
        return db.update(PersonsTable.TABLENAME, values,
                PersonsTable.COLUMN_ID + "=?", new String[]{String.valueOf(person.getId())}) > 0;
    }

    public boolean delete(Person person) {
        return db.delete(PersonsTable.TABLENAME,
                PersonsTable.COLUMN_ID + "=?", new String[]{String.valueOf(person.getId())}) > 0;
    }

    public Person get(long id) {
        Person person = null;
        Cursor cursor = db.query(true, PersonsTable.TABLENAME, new String[]{PersonsTable.COLUMN_ID,
                        PersonsTable.COLUMN_NAME, PersonsTable.COLUMN_PHONE, PersonsTable.COLUMN_EMAIL,
                        PersonsTable.COLUMN_DEPT, PersonsTable.COLUMN_IMG},
                PersonsTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            person = buildPersonFromCursor(cursor);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return person;
    }

    public ArrayList<Person> getAll() {
        ArrayList<Person> persons = new ArrayList<>();

        Cursor cursor = db.query(PersonsTable.TABLENAME, new String[]{PersonsTable.COLUMN_ID,
                PersonsTable.COLUMN_NAME, PersonsTable.COLUMN_PHONE, PersonsTable.COLUMN_EMAIL,
                PersonsTable.COLUMN_DEPT, PersonsTable.COLUMN_IMG}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Person person = buildPersonFromCursor(cursor);
                if (person != null) {
                    persons.add(person);
                }
            } while (cursor.moveToNext());

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return persons;
    }

    public ArrayList<Person> getFiltered(String filter) {
        ArrayList<Person> persons = new ArrayList<>();

        Cursor cursor = db.query(PersonsTable.TABLENAME, new String[]{PersonsTable.COLUMN_ID,
                PersonsTable.COLUMN_NAME, PersonsTable.COLUMN_PHONE, PersonsTable.COLUMN_EMAIL,
                PersonsTable.COLUMN_DEPT, PersonsTable.COLUMN_IMG},
                PersonsTable.COLUMN_NAME + " like ?", new String[]{ "%" + filter + "%" },
                null, null, PersonsTable.COLUMN_NAME+" COLLATE NOCASE asc");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Person person = buildPersonFromCursor(cursor);
                if (person != null) {
                    persons.add(person);
                }
            } while (cursor.moveToNext());

            if (!cursor.isClosed()) {


                cursor.close();
            }
        }
        return persons;
    }

    public Person buildPersonFromCursor(Cursor cursor) {
        Person person = null;

        if (cursor != null) {
            person = new Person();
            person.setId(cursor.getLong(0));
            person.setName(cursor.getString(1));
            person.setPhone(cursor.getString(2));
            person.setEmail(cursor.getString(3));
            person.setDept(cursor.getString(4));
            person.setPicID(cursor.getInt(5));
        }
        return person;
    }
}
