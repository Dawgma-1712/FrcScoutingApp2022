package com.example.frcscoutingapp2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Teleop extends Fragment implements View.OnClickListener{

    //initialize variables

    public static EditText defendedByNum;

    //Initialize upper hub text views
    private TextView upperHubTeleopScored;
    private TextView upperHubTeleopMissed;

    //initialize lower hub text views
    private TextView lowerHubTeleopMissed;
    private TextView lowerHubTeleopScored;
    
    //counter variables
    private int upperHubTeleopScoredCounter = 0;
    private int upperHubTeleopMissedCounter = 0;
    private int lowerHubTeleopScoredCounter = 0;
    private int lowerHubTeleopMissedCounter = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_teleop, container, false);

        defendedByNum = (EditText) view.findViewById(R.id.defendedOnTeamNum);
        //Define TextViews
        upperHubTeleopScored = (TextView) view.findViewById(R.id.upperHubTeleopScored);
        upperHubTeleopMissed = (TextView) view.findViewById(R.id.upperHubTeleopMissed);

        lowerHubTeleopScored = (TextView) view.findViewById(R.id.lowerHubTeleopScored);
        lowerHubTeleopMissed = (TextView) view.findViewById(R.id.lowerHubTeleopMissed);





        //Upper buttons
        view.findViewById(R.id.upperHubTeleopScoredIncrease).setOnClickListener(this);
        view.findViewById(R.id.upperHubTeleopScoredDecrease).setOnClickListener(this);

        view.findViewById(R.id.upperHubTeleopMissedIncrease).setOnClickListener(this);
        view.findViewById(R.id.upperHubTeleopMissedDecrease).setOnClickListener(this);

        //Lower port buttons
        view.findViewById(R.id.lowerHubTeleopScoredIncrease).setOnClickListener(this);
        view.findViewById(R.id.lowerHubTeleopScoredDecrease).setOnClickListener(this);

        view.findViewById(R.id.lowerHubTeleopMissedIncrease).setOnClickListener(this);
        view.findViewById(R.id.lowerHubTeleopMissedDecrease).setOnClickListener(this);


        return view;
    }



    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.upperHubTeleopScoredIncrease:
                System.out.println(MainActivity.checkBoxData);
                upperHubTeleopScoredCounter++;
                upperHubTeleopScored.setText(Integer.toString(upperHubTeleopScoredCounter));
                //MainActivity.editMatchData(1, 0, MainActivity.getButtonData()[0][0] + 1);
                MainActivity.upperScoredTeleop++;
                break;

            case R.id.upperHubTeleopScoredDecrease:
                if(upperHubTeleopScoredCounter > 0) {
                    upperHubTeleopScoredCounter--;
                    upperHubTeleopScored.setText(Integer.toString(upperHubTeleopScoredCounter));
                    //MainActivity.editMatchData(1, 0, MainActivity.getButtonData()[0][0] - 1);
                    MainActivity.upperScoredTeleop--;
                } break;

            case R.id.upperHubTeleopMissedIncrease:
                upperHubTeleopMissedCounter++;
                upperHubTeleopMissed.setText(Integer.toString(upperHubTeleopMissedCounter));
                //MainActivity.editMatchData(1, 1, MainActivity.getButtonData()[0][1] + 1);
                MainActivity.upperMissedTeleop++;
                break;

            case R.id.upperHubTeleopMissedDecrease:
                if(upperHubTeleopMissedCounter > 0) {
                    upperHubTeleopMissedCounter--;
                    upperHubTeleopMissed.setText(Integer.toString(upperHubTeleopMissedCounter));
                    //MainActivity.editMatchData(1, 1, MainActivity.getButtonData()[0][1] - 1);
                    MainActivity.upperMissedTeleop--;
                } break;

            case R.id.lowerHubTeleopScoredIncrease:
                lowerHubTeleopScoredCounter++;
                lowerHubTeleopScored.setText(Integer.toString(lowerHubTeleopScoredCounter));
                //MainActivity.editMatchData(1, 2, MainActivity.getButtonData()[0][2] + 1);
                MainActivity.lowerScoredTeleop++;
                System.out.println(MainActivity.lowerScoredTeleop);
                break;

            case R.id.lowerHubTeleopScoredDecrease:
                if(lowerHubTeleopScoredCounter > 0) {
                    lowerHubTeleopScoredCounter--;
                    lowerHubTeleopScored.setText(Integer.toString(lowerHubTeleopScoredCounter));
                    //MainActivity.editMatchData(1, 2, MainActivity.getButtonData()[0][2] - 1);
                    MainActivity.lowerScoredTeleop--;
                    System.out.println(MainActivity.lowerScoredTeleop);

                } break;

            case R.id.lowerHubTeleopMissedIncrease:
                lowerHubTeleopMissedCounter++;
                lowerHubTeleopMissed.setText(Integer.toString(lowerHubTeleopMissedCounter));
                //MainActivity.editMatchData(1, 3, MainActivity.getButtonData()[0][3] + 1);
                MainActivity.lowerMissedTeleop++;
                break;

            case R.id.lowerHubTeleopMissedDecrease:
                if(lowerHubTeleopMissedCounter > 0) {
                    lowerHubTeleopMissedCounter--;
                    lowerHubTeleopMissed.setText(Integer.toString(lowerHubTeleopMissedCounter));
                    //MainActivity.editMatchData(1, 3, MainActivity.getButtonData()[0][3] - 1);
                    MainActivity.lowerMissedTeleop--;
                } break;
        }
    }

    public void onResume(){
        super.onResume();

        upperHubTeleopScored.setText(Integer.toString(upperHubTeleopScoredCounter));
        upperHubTeleopMissed.setText(Integer.toString(upperHubTeleopMissedCounter));
        lowerHubTeleopScored.setText(Integer.toString(lowerHubTeleopScoredCounter));
        lowerHubTeleopMissed.setText(Integer.toString(lowerHubTeleopMissedCounter));
    }

    public void clear(){
        upperHubTeleopScored.setText(Integer.toString(0));
        upperHubTeleopMissed.setText(Integer.toString(0));
        lowerHubTeleopScored.setText(Integer.toString(0));
        lowerHubTeleopMissed.setText(Integer.toString(0));

        upperHubTeleopScoredCounter = 0;
        upperHubTeleopMissedCounter = 0;

        lowerHubTeleopScoredCounter = 0;
        lowerHubTeleopMissedCounter = 0;

    }





}
