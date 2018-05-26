package com.example.mshehab.db_contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickIconActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_icon);

        findViewById(R.id.imageViewF1).setOnClickListener(this);
        findViewById(R.id.imageViewF2).setOnClickListener(this);
        findViewById(R.id.imageViewF3).setOnClickListener(this);
        findViewById(R.id.imageViewM1).setOnClickListener(this);
        findViewById(R.id.imageViewM2).setOnClickListener(this);
        findViewById(R.id.imageViewM3).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if(view.getId() == R.id.imageViewF1){
            intent.putExtra("ICON", R.drawable.avatar_f_1);
        } else if(view.getId() == R.id.imageViewF2){
            intent.putExtra("ICON", R.drawable.avatar_f_2);
        } else if(view.getId() == R.id.imageViewF3){
            intent.putExtra("ICON", R.drawable.avatar_f_3);
        } else if(view.getId() == R.id.imageViewM1){
            intent.putExtra("ICON", R.drawable.avatar_m_1);
        } else if(view.getId() == R.id.imageViewM2){
            intent.putExtra("ICON", R.drawable.avatar_m_2);
        } else if(view.getId() == R.id.imageViewM3){
            intent.putExtra("ICON", R.drawable.avatar_m_3);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
