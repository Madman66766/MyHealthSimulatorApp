package com.android.serkan.myhealthsimulatorapp;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Serkan on 3-10-2017.
 */

public class Simulator {


    public String simulateBloodPressure(){
        Random random = new Random();
        MainActivity.appendMessage("Measuring blood pressure...");
        int systolic = random.nextInt(180) + 70;
        int diastolic = random.nextInt(110) + 70;
        MainActivity.appendMessage("Systolic : "+systolic + " Diastolic : " + diastolic + "");
        JSONObject json = new JSONObject();
        try {
            json.put("type",1);
            json.put("systolic",""+systolic);
            json.put("diastolic",""+diastolic);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public String simulatePulse(){
        Random random = new Random();
        MainActivity.appendMessage("Measuring heart rate...");
        int heartRate = random.nextInt(180)+ 60;
        MainActivity.appendMessage("Heart rate : " + heartRate);
        JSONObject json = new JSONObject();
        try {
            json.put("type",2);
            json.put("pulse",""+heartRate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public void ecgWaves(final BluetoothConnector bluetoothConnector){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                boolean running = true;
                int counter = 0;
                //MainActivity.appendMessage("ECG sending...");
                while(running){
                    int ecg = random.nextInt(50) + -50;
                    //MainActivity.appendMessage("ECG : " + ecg);
                    JSONObject json = new JSONObject();
                    try {
                        json.put("type",3);
                        json.put("ecg",""+ecg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    bluetoothConnector.write(json.toString().getBytes());
                    if(counter == 10){
                        running = false;
                    }
                    counter++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
