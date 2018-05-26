package com.example.mshehab.db_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mshehab.db_contacts.DB.DBDataManager;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    ListView listView;
    PersonAdapter personAdapter;
    ArrayList<Person> persons;
    EditText editTextFilter;
    DBDataManager dbDataManager;
    final String TAG = "demo";
    final int CREATE_USER_RES = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        dbDataManager = new DBDataManager(this);


        //dbDataManager.getPersonDAO().save(new Person("Bob","b@b.com","1111","SIS",0));
        //dbDataManager.getPersonDAO().save(new Person("Alice","a@a.com","2222","CS",1));
        //dbDataManager.getPersonDAO().save(new Person("tom","t@t.com","3333","BIO",2));


        persons = dbDataManager.getPersonDAO().getAll();

        listView = findViewById(R.id.listView);
        personAdapter = new PersonAdapter(this,R.layout.contact_item, persons);
        listView.setAdapter(personAdapter);


        editTextFilter = findViewById(R.id.editTextFilter);

        findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, NewContactActivity.class);
                startActivityForResult(intent, CREATE_USER_RES);
            }
        });

        findViewById(R.id.buttonFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personAdapter.clear();
                personAdapter.addAll(dbDataManager.getPersonDAO().getFiltered(editTextFilter.getText().toString()));

                personAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dbDataManager.getPersonDAO().delete(personAdapter.getItem(i));
                personAdapter.remove(personAdapter.getItem(i));
                personAdapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_USER_RES && resultCode == RESULT_OK){
            //refresh the list!!

            personAdapter.clear();
            personAdapter.addAll(dbDataManager.getPersonDAO().getAll());

           //persons.clear();
           //persons = dbDataManager.getPersonDAO().getAll();

           personAdapter.notifyDataSetChanged();

        }

    }
}
