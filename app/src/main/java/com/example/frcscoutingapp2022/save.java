package com.example.frcscoutingapp2022;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class save extends Fragment implements View.OnClickListener{

    private String data = "";

    public static Bitmap bitmap;
    private static boolean qrReady = false;

    public static final int CREATE_FILE = 1;
    public static Uri fileUri;



    private ImageView ivOutput;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        view.findViewById(R.id.generateQR).setOnClickListener(this);
        view.findViewById(R.id.newMatch2).setOnClickListener(this);
        view.findViewById(R.id.saveFile2).setOnClickListener(this);
        ivOutput = view.findViewById(R.id.iv_output);


        return view;

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

                data += MainActivity.teamNumber + "," + MainActivity.matchNumber + ","
                        /* Auto */   + MainActivity.taxi + "," + MainActivity.lowerScoredAuto + "," + MainActivity.lowerMissedAuto + "," + MainActivity.upperScoredAuto + "," + MainActivity.upperMissedAuto + ","
                        /* TeleOp */ + MainActivity.playedDefense + "," + MainActivity.defendedOn + "," + MainActivity.defendedOnByNumber + "," + MainActivity.lowerScoredTeleop + "," + MainActivity.lowerMissedTeleop + "," + MainActivity.upperScoredTeleop + "," + MainActivity.upperMissedTeleop + "," + MainActivity.fender + "," + MainActivity.tarmac + "," + MainActivity.launchPad + "," + MainActivity.genLoc + ","
                        /* Climb */  + MainActivity.lowFail + "," + MainActivity.lowSuccess + "," + MainActivity.midFail + "," + MainActivity.midSuccess + "," + MainActivity.highFail + "," + MainActivity.highSuccess + "," + MainActivity.travFail + "," + MainActivity.travSuccess + ","
                        /* AddInfo*/ + MainActivity.penalty + "," + MainActivity.deadBot + "," + MainActivity.noClimbAttempt + "," + MainActivity.alliance + "," + MainActivity.additionalNotes  + "," + MainActivity.scoutName;

                alterDocument(uri, data);
            }
        }
    }

    private void alterDocument(Uri uri, String data) {
        try {
            ParcelFileDescriptor pfd = getActivity().getContentResolver().openFileDescriptor(uri, "w");
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





    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.generateQR:

                try {
                    if (MainActivity.teamNumText.getText().toString() != null) {
                        MainActivity.teamNumber = MainActivity.teamNumText.getText().toString();
                    }

                    if (MainActivity.matchNumText.getText().toString() != null){
                        MainActivity.matchNumber = MainActivity.matchNumText.getText().toString();
                    }

                    if (Teleop.defendedByNum.getText().toString() != null){
                        MainActivity.defendedOnByNumber = Teleop.defendedByNum.getText().toString();
                    }

                    if (MainActivity.scoutNameText.getText().toString() != null){
                        MainActivity.scoutName = MainActivity.scoutNameText.getText().toString();
                    }
                    if (endgame.additionalNotesText.getText().toString() != null){
                        MainActivity.additionalNotes = endgame.additionalNotesText.getText().toString();
                    }


                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }


                data = MainActivity.teamNumber + "," + MainActivity.matchNumber + ","
                        /* Auto */   + MainActivity.taxi + "," + MainActivity.lowerScoredAuto + "," + MainActivity.lowerMissedAuto + "," + MainActivity.upperScoredAuto + "," + MainActivity.upperMissedAuto + ","
                        /* TeleOp */ + MainActivity.playedDefense + "," + MainActivity.defendedOn + "," + MainActivity.defendedOnByNumber + "," + MainActivity.lowerScoredTeleop + "," + MainActivity.lowerMissedTeleop + "," + MainActivity.upperScoredTeleop + "," + MainActivity.upperMissedTeleop + "," + MainActivity.fender + "," + MainActivity.tarmac + "," + MainActivity.launchPad + "," + MainActivity.genLoc + ","
                        /* Climb */  + MainActivity.lowFail + "," + MainActivity.lowSuccess + "," + MainActivity.midFail + "," + MainActivity.midSuccess + "," + MainActivity.highFail + "," + MainActivity.highSuccess + "," + MainActivity.travFail + "," + MainActivity.travSuccess + ","
                        /* AddInfo*/ + MainActivity.noClimbAttempt + "," + MainActivity.penalty + "," + MainActivity.deadBot + "," + MainActivity.alliance + "," + MainActivity.additionalNotes  + "," + MainActivity.scoutName;


                //Initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    //Initialize bit matrix
                    BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 600, 600);
                    //Initialize barcode Encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    //initialize bitmap
                    bitmap = encoder.createBitmap(matrix);
                    System.out.println(bitmap);
                    qrReady = true;
                    //set bitmap on image view
                    //saveFragment.ivOutput.setImageBitmap(bitmap);


                } catch(WriterException e){
                    e.printStackTrace();
                }

                ivOutput.setImageBitmap(bitmap);
                break;
            case R.id.newMatch2:
                Intent intent = new Intent(getActivity(), HomeScreen.class);
                startActivity(intent);
                break;

            case R.id.saveFile2:
                //Get text field values
//        teamNumber = 0;
//        matchNumber = 0;


                try {
                    if (MainActivity.teamNumText.getText().toString() != null) {
                        MainActivity.teamNumber = MainActivity.teamNumText.getText().toString();
                    }

                    if (MainActivity.matchNumText.getText().toString() != null){
                        MainActivity.matchNumber = MainActivity.matchNumText.getText().toString();
                    }

                    if (Teleop.defendedByNum.getText().toString() != null){
                        MainActivity.defendedOnByNumber = Teleop.defendedByNum.getText().toString();
                    }


                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }


                System.out.println(MainActivity.teamNumber);
                System.out.println(MainActivity.matchNumber);
                System.out.println(MainActivity.defendedOnByNumber);

                data += MainActivity.teamNumber + "," + MainActivity.matchNumber + ","
                        /* Auto */   + MainActivity.taxi + "," + MainActivity.lowerScoredAuto + "," + MainActivity.lowerMissedAuto + "," + MainActivity.upperScoredAuto + "," + MainActivity.upperMissedAuto + ","
                        /* TeleOp */ + MainActivity.playedDefense + "," + MainActivity.defendedOn + "," + MainActivity.defendedOnByNumber + "," + MainActivity.lowerScoredTeleop + "," + MainActivity.lowerMissedTeleop + "," + MainActivity.upperScoredTeleop + "," + MainActivity.upperMissedTeleop + "," + MainActivity.fender + "," + MainActivity.tarmac + "," + MainActivity.launchPad + "," + MainActivity.genLoc + ","
                        /* Climb */  + MainActivity.lowFail + "," + MainActivity.lowSuccess + "," + MainActivity.midFail + "," + MainActivity.midSuccess + "," + MainActivity.highFail + "," + MainActivity.highSuccess + "," + MainActivity.travFail + "," + MainActivity.travSuccess + ","
                        /* AddInfo*/ + MainActivity.penalty + "," + MainActivity.deadBot + "," + MainActivity.noClimbAttempt + "," + MainActivity.alliance + "," + MainActivity.additionalNotes  + "," + MainActivity.scoutName;


                // Create and save file

                Intent newIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                newIntent.addCategory(Intent.CATEGORY_OPENABLE);
                newIntent.setType("application/csv");
                String fileName = "match" + MainActivity.matchNumber + "_team" + MainActivity.teamNumber + ".csv";
                newIntent.putExtra(Intent.EXTRA_TITLE, fileName);

                // TODO: Automatically direct user to correct save location

                startActivityForResult(newIntent, 1);

                break;


        }
    }
}