package com.example.mshehab.db_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mshehab.db_contacts.DB.DBDataManager;

public class NewContactActivity extends AppCompatActivity {

    ImageView imageViewSelect;
    EditText editTextName, editTextPhone, editTextEmail;
    RadioGroup radioGroup;
    int id;


    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        imageViewSelect = findViewById(R.id.imageViewSelect);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);

        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.imageViewSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewContactActivity.this, PickIconActivity.class);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String dept = "SIS";

                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonSIS){
                    dept = "SIS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonCS){
                    dept = "CS";
                } else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonBIO){
                    dept = "BIO";
                }

                if(name.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if(email.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if(phone.equals("")){
                    Toast.makeText(NewContactActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                } else{
                    Person person = new Person(name, email, phone, dept, id);
                    // save the person information !!
                    DBDataManager db = new DBDataManager(NewContactActivity.this);
                    db.getPersonDAO().save(person);

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            int iconId = data.getExtras().getInt("ICON");
            id = iconId;
            imageViewSelect.setImageResource(iconId);
        }
    }
}
