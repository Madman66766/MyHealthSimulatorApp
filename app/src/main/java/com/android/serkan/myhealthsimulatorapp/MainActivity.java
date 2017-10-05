package com.android.serkan.myhealthsimulatorapp;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT=1;
    static TextView text;
    BluetoothConnector bluetoothConnector;
    Simulator simulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simulator = new Simulator();
        text = (TextView) findViewById(R.id.textView2);
        text.setText("");
        text.setMovementMethod(new ScrollingMovementMethod());

    }

    public static void appendMessage(String msg){
        text.append(msg + "\n");
    }
    public static TextView getTextView(){
        return text;
    }

    public void bloodPressure(View view){
        if(bluetoothConnector == null){
            simulator.simulateBloodPressure();
        }else{
            bluetoothConnector.write(simulator.simulateBloodPressure().getBytes());
        }
    }
    public void heartRate(View view){
        if(bluetoothConnector == null){
            simulator.simulateBloodPressure();
        }else{
            bluetoothConnector.write(simulator.simulatePulse().getBytes());
        }
    }
    public void ecgWaves(View view){
        simulator.ecgWaves(bluetoothConnector);
    }

    public void startConnection(View view){bluetoothConnector = new BluetoothConnector();}

}
