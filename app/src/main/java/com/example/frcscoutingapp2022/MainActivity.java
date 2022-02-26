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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private save saveFragment;

    public static Bitmap bitmap;
    private static boolean qrReady = false;

    public static String teamNumber;
    public static String matchNumber;
    public static int[][] buttonData;
    public static int[] checkBoxData;
    public static String defendedOnByNumber = "";


    public static int taxi = 0;
    public static int groundPickup = 0;
    public static int HPPickup = 0;
    public static int playedDefense=0;
    public static int defendedOn = 0 ;

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
    public static int collectedCargo = 0;

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
        vpAdapter.addFragment(new endgame(),"Endgame");
        vpAdapter.addFragment(new save(),"Save");
        viewPager.setAdapter(vpAdapter);

        teamNumber = GlobalVariables.getTeamNum();
        matchNumber = GlobalVariables.getMatchNum();





    }

    public static void editMatchData(int ind0, int ind1, int value) {
        buttonData[ind0][ind1] = value;
    }

    public static void onCheckBoxClicked(View view) {
        // Is view checked
        boolean checked = ((CheckBox) view).isChecked();

        // Check which one clicked
        switch(view.getId()) {
            case R.id.groundPickupCheckbox:
                //MainActivity.checkBoxData[0] = checked ? 1 : 0;
                MainActivity.groundPickup = checked ? 1 : 0;
                break;
            case R.id.HPPickupCheckBox:
                //MainActivity.checkBoxData[1] = checked ? 1 : 0;
                MainActivity.HPPickup = checked ? 1 : 0;

                break;
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

                break;
            case R.id.lowHangSuccess:
                //MainActivity.checkBoxData[9] = checked ? 1 : 0;
                MainActivity.lowSuccess = checked ? 1 : 0;

                break;
            case R.id.midHangFailure:
                //MainActivity.checkBoxData[10] = checked ? 1 : 0;
                MainActivity.midFail = checked ? 1 : 0;

                break;
            case R. id.midHangSuccess:
                //MainActivity.checkBoxData[11] = checked ? 1 : 0;
                MainActivity.midSuccess = checked ? 1 : 0;

                break;
            case R. id.highHangFailure:
                //MainActivity.checkBoxData[12] = checked ? 1 : 0;
                MainActivity.highFail = checked ? 1 : 0;

                break;
            case R. id.highHangSuccess:
                //MainActivity.checkBoxData[13] = checked ? 1 : 0;
                MainActivity.highSuccess = checked ? 1 : 0;

                break;
            case R. id.traversalHangFailure:
                //MainActivity.checkBoxData[14] = checked ? 1 : 0;
                MainActivity.travFail = checked ? 1 : 0;

                break;
            case R. id.traversalHangSuccess:
                //MainActivity.checkBoxData[15] = checked ? 1 : 0;
                MainActivity.travSuccess = checked ? 1 : 0;

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

    public static int[][] getButtonData() {
        return buttonData;
    }
    public static int[] getCheckBoxData() { return checkBoxData; }
//
//    public void updateTeamAndMatchNum() {
//        teamNumber = Integer.parseInt(((EditText)findViewById(R.id.teamNum)).getText().toString());
//        matchNumber = Integer.parseInt(((EditText)findViewById(R.id.matchNum)).getText().toString());
//    }
    public static String getTeamNumber() {return teamNumber;}
    public static String getMatchNumber() {return matchNumber;}



    public static final int CREATE_FILE = 1;
    public static Uri fileUri;



    public void saveMatchData(View view) {
        //Get text field values
//        teamNumber = 0;
//        matchNumber = 0;

        defendedOnByNumber = "";
        try {
            if (((EditText) findViewById(R.id.defendedOnTeamNum)).getText().toString() != null) {
                defendedOnByNumber = ((EditText) findViewById(R.id.defendedOnTeamNum)).getText().toString();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(teamNumber);
        System.out.println(matchNumber);
        System.out.println(defendedOnByNumber);

        String data = "";


        data += teamNumber + "," + matchNumber + ","
    /* Auto */   + taxi + "," + lowerScoredAuto + "," + lowerMissedAuto + "," + upperScoredAuto + "," + upperMissedAuto + "," + collectedCargo + ","
    /* TeleOp */ + groundPickup + "," + HPPickup + "," + playedDefense + "," + defendedOn + "," + defendedOnByNumber + "," + lowerScoredTeleop + "," + lowerMissedTeleop + "," + upperScoredTeleop + "," + upperMissedTeleop + "," + fender + "," + tarmac + "," + launchPad + "," + genLoc + ","
    /* Climb */  + lowFail + "," + lowSuccess + "," + midFail + "," + midSuccess + "," + highFail + "," + highSuccess + "," + travFail + "," + travSuccess + ","
    /* AddInfo*/ + penalty + "," + deadBot + "," + noClimbAttempt;



        // Create and save file

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/csv");
        String fileName = "match" + matchNumber + "_team" + teamNumber + ".csv";
        intent.putExtra(Intent.EXTRA_TITLE, fileName);

            // TODO: Automatically direct user to correct save location

            startActivityForResult(intent, CREATE_FILE);








    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                fileUri = uri;

                // Good luck reading :)
                String data = "";
                data += (new Integer(teamNumber)).toString() + "," + (new Integer(matchNumber)).toString() + ","
                        /* Auto */   +taxi + "," + lowerScoredAuto + "," + lowerMissedAuto + "," + upperScoredAuto + "," + upperMissedAuto + "," + collectedCargo + ","
                        /* TeleOp */ + groundPickup + "," + HPPickup + "," + playedDefense + "," + defendedOn + "," + defendedOnByNumber + "," + lowerScoredTeleop + "," + lowerMissedTeleop + "," + upperScoredTeleop + "," + upperMissedTeleop + "," + fender + "," + tarmac + "," + launchPad + "," + genLoc + ","
                        /* Climb */  + lowFail + "," + lowSuccess + "," + midFail + "," + midSuccess + "," + highFail + "," + highSuccess + "," + travFail + "," + travSuccess + ","
                        /* AddInfo*/ + penalty + "," + deadBot + "," + noClimbAttempt;

                alterDocument(uri, data);
            }
        }
    }

    private void alterDocument(Uri uri, String data) {
        try {
            ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());
            fileOutputStream.write((data + "\n").getBytes());
            // Let the document provider know you're done by closing the stream.
            fileOutputStream.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}