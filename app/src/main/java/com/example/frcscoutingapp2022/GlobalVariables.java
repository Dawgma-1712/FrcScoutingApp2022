package com.example.frcscoutingapp2022;


import android.app.Application;

//created by michael aizenberg
public class GlobalVariables extends Application {

    private static String teamNum;
    private static String matchNum;

    public static String getTeamNum(){
        return teamNum;
    }

    public static void setTeamNum(String tn){
        teamNum = tn;
    }




    public static String getMatchNum(){
        return matchNum;
    }

    public static void setMatchNum(String mn){
        matchNum = mn;
    }



}
