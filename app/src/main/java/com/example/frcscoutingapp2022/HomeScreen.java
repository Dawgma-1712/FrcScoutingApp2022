package com.example.frcscoutingapp2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity {

    private EditText teamNum;
    private EditText matchNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        teamNum = findViewById(R.id.teamNum);
        matchNum = findViewById(R.id.matchNum);

    }

    public void switchToMainActivity (View view) {
        Intent intent = new Intent(this, MainActivity.class);

        if (!teamNum.getText().toString().equals("") || !matchNum.getText().toString().equals("")) {
            GlobalVariables.setTeamNum(teamNum.getText().toString());
            GlobalVariables.setMatchNum(matchNum.getText().toString());
            startActivity(intent);
        }
        else {
        }
    }
}