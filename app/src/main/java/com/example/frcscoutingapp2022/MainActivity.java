package com.example.frcscoutingapp2022;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private save saveFragment;

    public static EditText teamNumText;
    public static EditText matchNumText;
    public static EditText scoutNameText;

    public static Bitmap bitmap;
    private static boolean qrReady = false;

    public static String teamNumber;
    public static String matchNumber;
    public static String scoutName;
    public static String additionalNotes;
    public static int[][] buttonData;
    public static int[] checkBoxData;
    public static String defendedOnByNumber = "";


    public static int taxi = 0;
    public static int alliance = 0;
    public static int playedDefense = 0;
    public static int defendedOn = 0;

    public static int fender = 0;
    public static int tarmac = 0;
    public static int launchPad = 0;
    public static int genLoc = 0;

    public static int lowFail = 0;
    public static int lowSuccess = 0;
    public static int midFail = 0;
    public static int midSuccess = 0;
    public static int highFail = 0;
    public static int highSuccess = 0;
    public static int travFail = 0;
    public static int travSuccess = 0;
    public static int penalty = 0;
    public static int deadBot = 0;
    public static int noClimbAttempt = 0;

    public static int upperScoredAuto = 0;
    public static int upperMissedAuto = 0;
    public static int lowerScoredAuto = 0;
    public static int lowerMissedAuto = 0;

    public static int upperScoredTeleop = 0;
    public static int upperMissedTeleop = 0;
    public static int lowerScoredTeleop = 0;
    public static int lowerMissedTeleop = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(viewPager);

        VPadapter vpAdapter = new VPadapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdapter.addFragment(new Auto(), "Auto");
        vpAdapter.addFragment(new Teleop(), "Teleop");
        vpAdapter.addFragment(new endgame(), "Endgame");
        vpAdapter.addFragment(new save(), "Save");
        viewPager.setAdapter(vpAdapter);

        teamNumText = (EditText) findViewById(R.id.teamNum);
        matchNumText = (EditText) findViewById(R.id.matchNum);
        scoutNameText = (EditText) findViewById(R.id.name);

        taxi = 0;
        playedDefense = 0;
        defendedOn = 0;

        fender = 0;
        tarmac = 0;
        launchPad = 0;
        genLoc = 0;

        lowFail = 0;
        lowSuccess = 0;
        midFail = 0;
        midSuccess = 0;
        highFail = 0;
        highSuccess = 0;
        travFail = 0;
        travSuccess = 0;
        penalty = 0;
        deadBot = 0;
        noClimbAttempt = 0;

        upperScoredAuto = 0;
        upperMissedAuto = 0;
        lowerScoredAuto = 0;
        lowerMissedAuto = 0;

        upperScoredTeleop = 0;
        upperMissedTeleop = 0;
        lowerScoredTeleop = 0;
        lowerMissedTeleop = 0;


    }

    public static void editMatchData(int ind0, int ind1, int value) {
        buttonData[ind0][ind1] = value;
    }

    public void onCheckBoxClicked(View view) {
        // Is view checked
        boolean checked = ((CheckBox) view).isChecked();

        // Check which one clicked
        switch (view.getId()) {
            case R.id.playedDefenseCheckBox:
                //MainActivity.checkBoxData[2] = checked ? 1 : 0;
                MainActivity.playedDefense = checked ? 1 : 0;

                break;
            case R.id.defendedOnCheckBox:
                //MainActivity.checkBoxData[3] = checked ? 1 : 0;
                MainActivity.defendedOn = checked ? 1 : 0;

                break;
            case R.id.fenderCheckBox:
                //MainActivity.checkBoxData[4] = checked ? 1 : 0;
                MainActivity.fender = checked ? 1 : 0;

                break;
            case R.id.tarmacCheckBox:
                //MainActivity.checkBoxData[5] = checked ? 1 : 0;
                MainActivity.tarmac = checked ? 1 : 0;

                break;
            case R.id.LaunchpadCheckBox:
                //MainActivity.checkBoxData[6] = checked ? 1 : 0;
                MainActivity.launchPad = checked ? 1 : 0;

                break;
            case R.id.generalLocationCheckBox:
                //MainActivity.checkBoxData[7] = checked ? 1 : 0;
                MainActivity.genLoc = checked ? 1 : 0;

                break;
            case R.id.lowHangFailure:
                //MainActivity.checkBoxData[8] = checked ? 1: 0;
                MainActivity.lowFail = checked ? 1 : 0;
                //MainActivity.lowSuccess = 0;

                break;
            case R.id.lowHangSuccess:
                //MainActivity.checkBoxData[9] = checked ? 1 : 0;
                MainActivity.lowSuccess = checked ? 1 : 0;
                //MainActivity.lowFail = 0;


                break;
            case R.id.midHangFailure:
                //MainActivity.checkBoxData[10] = checked ? 1 : 0;
                MainActivity.midFail = checked ? 1 : 0;
                //((CheckBox)findViewById(R.id.midHangSuccess)).setSelected(false);
                //MainActivity.midSuccess = 0;

                break;
            case R.id.midHangSuccess:
                //MainActivity.checkBoxData[11] = checked ? 1 : 0;
                MainActivity.midSuccess = checked ? 1 : 0;

                //MainActivity.midFail = 0;

                break;
            case R.id.highHangFailure:
                //MainActivity.checkBoxData[12] = checked ? 1 : 0;
                MainActivity.highFail = checked ? 1 : 0;
                //view.findViewById(R.id.highHangSuccess).setSelected(false);
                //MainActivity.highSuccess = 0;

                break;
            case R.id.highHangSuccess:
                //MainActivity.checkBoxData[13] = checked ? 1 : 0;
                MainActivity.highSuccess = checked ? 1 : 0;
                //view.findViewById(R.id.highHangFailure).setSelected(false);
                //MainActivity.highFail = 0;

                break;
            case R.id.traversalHangFailure:
                //MainActivity.checkBoxData[14] = checked ? 1 : 0;
                MainActivity.travFail = checked ? 1 : 0;
                //view.findViewById(R.id.traversalHangSuccess).setSelected(false);
                ///MainActivity.travSuccess = 0;

                break;
            case R.id.traversalHangSuccess:
                //MainActivity.checkBoxData[15] = checked ? 1 : 0;
                MainActivity.travSuccess = checked ? 1 : 0;
                //view.findViewById(R.id.traversalHangFailure).setSelected(false);
                //MainActivity.travFail = 0;

                break;
            case R.id.taxiCheckBox:
                //MainActivity.checkBoxData[16] = checked ? 1 : 0;
                MainActivity.taxi = checked ? 1 : 0;

                break;
            case R.id.penalized:
                //MainActivity.checkBoxData[17] = checked ? 1 : 0;
                MainActivity.penalty = checked ? 1 : 0;

                break;
            case R.id.deadBot:
                //MainActivity.checkBoxData[18] = checked ? 1 : 0;
                MainActivity.deadBot = checked ? 1 : 0;

                break;
            case R.id.noClimbAttempt:
                //MainActivity.checkBoxData[19] = checked ? 1 : 0;
                MainActivity.noClimbAttempt = checked ? 1 : 0;
        }


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.blue:
                MainActivity.alliance = 1;
                System.out.println(MainActivity.alliance);
                break;

            case R.id.red:
                MainActivity.alliance = 0;
                System.out.println(MainActivity.alliance);
                break;
        }

//
//    public void updateTeamAndMatchNum() {
//        teamNumber = Integer.parseInt(((EditText)findViewById(R.id.teamNum)).getText().toString());
//        matchNumber = Integer.parseInt(((EditText)findViewById(R.id.matchNum)).getText().toString());
//    }
    }
}
