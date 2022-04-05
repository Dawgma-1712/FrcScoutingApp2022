package com.example.frcscoutingapp2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Auto extends Fragment implements View.OnClickListener {


    //initialize variables

    //Initialize upper hub text views
    private TextView upperAutoScored;
    private TextView upperAutoMissed;

    //initialize lower hub text views
    private TextView lowerAutoMissed;
    private TextView lowerAutoScored;

    int i;



    //counter variables
    private int upperAutoScoredCounter = 0;
    private int upperAutoMissedCounter = 0;
    private int lowerAutoScoredCounter = 0;
    private int lowerAutoMissedCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto, container, false);



        //Define TextViews
        upperAutoScored = (TextView) view.findViewById(R.id.upperHubAutoScored);
        upperAutoMissed = (TextView) view.findViewById(R.id.upperHubAutoMissed);

        lowerAutoScored = (TextView) view.findViewById(R.id.lowerHubAutoScored);
        lowerAutoMissed = (TextView) view.findViewById(R.id.lowerHubAutoMissed);




        //inner buttons
        view.findViewById(R.id.upperHubAutoScoredIncrease).setOnClickListener(this);
        view.findViewById(R.id.upperHubAutoScoredDecrease).setOnClickListener(this);

        view.findViewById(R.id.upperHubAutoMissedIncrease).setOnClickListener(this);
        view.findViewById(R.id.upperHubAutoMissedDecrease).setOnClickListener(this);

        //outer port buttons
        view.findViewById(R.id.lowerHubAutoScoredIncrease).setOnClickListener(this);
        view.findViewById(R.id.lowerHubAutoScoredDecrease).setOnClickListener(this);

        view.findViewById(R.id.lowerHubAutoMissedIncrease).setOnClickListener(this);
        view.findViewById(R.id.lowerHubAutoMissedDecrease).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.upperHubAutoScoredIncrease:
                upperAutoScoredCounter++;
                upperAutoScored.setText(Integer.toString(upperAutoScoredCounter));
                //MainActivity.editMatchData(0, 0, MainActivity.getButtonData()[0][0] + 1);
                MainActivity.upperScoredAuto++;
                break;

            case R.id.upperHubAutoScoredDecrease:
                if(upperAutoScoredCounter > 0) {
                    upperAutoScoredCounter--;
                    upperAutoScored.setText(Integer.toString(upperAutoScoredCounter));
                    //MainActivity.editMatchData(0, 0, MainActivity.getButtonData()[0][0] - 1);
                    MainActivity.upperScoredAuto--;
                } break;

            case R.id.upperHubAutoMissedIncrease:
                upperAutoMissedCounter++;
                upperAutoMissed.setText(Integer.toString(upperAutoMissedCounter));
                //MainActivity.editMatchData(0, 1, MainActivity.getButtonData()[0][1] + 1);\
                MainActivity.upperMissedAuto++;

                break;

            case R.id.upperHubAutoMissedDecrease:
                if(upperAutoMissedCounter > 0) {
                    upperAutoMissedCounter--;
                    upperAutoMissed.setText(Integer.toString(upperAutoMissedCounter));
                    //MainActivity.editMatchData(0, 1, MainActivity.getButtonData()[0][1] - 1);
                    MainActivity.upperMissedAuto--;
                } break;

            case R.id.lowerHubAutoScoredIncrease:
                lowerAutoScoredCounter++;
                lowerAutoScored.setText(Integer.toString(lowerAutoScoredCounter));
                //MainActivity.editMatchData(0, 2, MainActivity.getButtonData()[0][2] + 1);
                MainActivity.lowerScoredAuto++;

                break;

            case R.id.lowerHubAutoScoredDecrease:
                if(lowerAutoScoredCounter > 0) {
                    lowerAutoScoredCounter--;
                    lowerAutoScored.setText(Integer.toString(lowerAutoScoredCounter));
                    //MainActivity.editMatchData(0, 2, MainActivity.getButtonData()[0][2] - 1);
                    MainActivity.lowerScoredAuto--;
                } break;

            case R.id.lowerHubAutoMissedIncrease:
                lowerAutoMissedCounter++;
                lowerAutoMissed.setText(Integer.toString(lowerAutoMissedCounter));
                //MainActivity.editMatchData(0, 3, MainActivity.getButtonData()[0][3] + 1);
                MainActivity.lowerMissedAuto++;
                break;

            case R.id.lowerHubAutoMissedDecrease:
                if(lowerAutoMissedCounter > 0) {
                    lowerAutoMissedCounter--;
                    lowerAutoMissed.setText(Integer.toString(lowerAutoMissedCounter));
                    //MainActivity.editMatchData(0, 3, MainActivity.getButtonData()[0][3] - 1);
                    MainActivity.lowerMissedAuto--;
                } break;
        }
    }

    public void onResume() {
        super.onResume();

        upperAutoScored.setText(Integer.toString(upperAutoScoredCounter));
        upperAutoMissed.setText(Integer.toString(upperAutoMissedCounter));
        lowerAutoScored.setText(Integer.toString(lowerAutoScoredCounter));
        lowerAutoMissed.setText(Integer.toString(lowerAutoMissedCounter));

    }
}