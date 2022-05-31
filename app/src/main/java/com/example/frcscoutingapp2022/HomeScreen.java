package com.example.frcscoutingapp2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        findViewById (R.id.startMatch).setOnClickListener(this);
        findViewById(R.id.pitScouting).setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.startMatch:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.pitScouting:
                Intent i = new Intent(this, PitScouting.class);
                startActivity(i);
                break;


        }
    }
}