package com.example.frcscoutingapp2022;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class save extends Fragment implements View.OnClickListener{

    private String data = "";

    public static Bitmap bitmap;
    private static boolean qrReady = false;

    private ImageView ivOutput;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        view.findViewById(R.id.generateQR).setOnClickListener(this);
        view.findViewById(R.id.newMatch2).setOnClickListener(this);
        ivOutput = view.findViewById(R.id.iv_output);


        return view;

    }





    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.generateQR:
                data = MainActivity.teamNumber + "," + MainActivity.matchNumber + ","
                        /* Auto */   + MainActivity.taxi + "," + MainActivity.lowerScoredAuto + "," + MainActivity.lowerMissedAuto + "," + MainActivity.upperScoredAuto + "," + MainActivity.upperMissedAuto + "," + MainActivity.collectedCargo + ","
                        /* TeleOp */ + MainActivity.groundPickup + "," + MainActivity.HPPickup + "," + MainActivity.playedDefense + "," + MainActivity.defendedOn + "," + MainActivity.defendedOnByNumber + "," + MainActivity.lowerScoredTeleop + "," + MainActivity.lowerMissedTeleop + "," + MainActivity.upperScoredTeleop + "," + MainActivity.upperMissedTeleop + "," + MainActivity.fender + "," + MainActivity.tarmac + "," + MainActivity.launchPad + "," + MainActivity.genLoc + ","
                        /* Climb */  + MainActivity.lowFail + "," + MainActivity.lowSuccess + "," + MainActivity.midFail + "," + MainActivity.midSuccess + "," + MainActivity.highFail + "," + MainActivity.highSuccess + "," + MainActivity.travFail + "," + MainActivity.travSuccess + ","
                        /* AddInfo*/ + MainActivity.penalty + "," + MainActivity.deadBot + "," + MainActivity.noClimbAttempt;


                //Initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    //Initialize bit matrix
                    BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 350, 350);
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


        }
    }
}