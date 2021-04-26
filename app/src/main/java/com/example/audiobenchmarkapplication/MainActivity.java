package com.example.audiobenchmarkapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
    Student Name: Edward Duffy
    Student Number: 117501529
    Date: 19/01/2021
    Final Year Project:
    Project: Benchmarks for Many Core Smart Phones

    Project Mentor: Dr. Dan Grigoras

    Application: Audio Application for playing audio from both a local and from an online source,
    the online source that will be used will be Firebase.

    Purpose of this Application:


    How mobile device is being monitored:
    - In order to be able to monitor how the mobile device is performing when trying to execute the task
    the Profiler Tool which is integrated wth Android Studio is being used.
    - The Profiler Tool allows the following apsects of the mobile device to be monitored:
    - CPU
    - Memory
    - Graphics
    - Network


    Why I felt this was an important task to benchmark/monitor/test:
    - When I was tasked with creating applications that would be used to monitor a mobile device when
    tasked with performing everyday tasks or tasks that you would expect users to do when using their own
    mobile device, streaming/ playing local audio was one of the first I came up with.
    - Streaming content is now becoming the convention and along with video, audio is the most streamed
    - With apps like Spotify being one of the most popular applications for mobile devices
    - Spotify has over 286 million users a month and with an average listening time of each user being 25 hours a month
    - A user on average spends 1.2hrs a day listening to music so thats why i feel that it's important to monitor how the phone is handling dealing with this task.

    Aspects were testing with this application:
    - How the mobile device handles executing audio from a local source such as internal storage/external storage such as SD Cards
    - How the mobile phone handles streaming music from a server such as Firebase, this would be a way of emulating how people listen to music on Spotify
    - Being able to turn on bluetooth by clicking a button and see how the phone/mobile device handles sending the audio to headphones/audio devices via a bluetooth connection.
 */
public class MainActivity extends AppCompatActivity {

    public static final int Bluetooth_request_code = 1;
    Button bluetoothButton;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothButton = findViewById(R.id.bluetoothButton);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the device doesnt have any bluetooth capability, let them know by presenting them with a "Device Doesnt support bluetooth message"
        if(bluetoothAdapter == null){
            Toast.makeText(MainActivity.this,"Device doesn't support Bluetooth",Toast.LENGTH_LONG).show();
        }
        // If the bluetooth is already on/off change the text presented accordingly asking them whether they would like to turn it on or off
        if(!bluetoothAdapter.isEnabled()){
            bluetoothButton.setText("Turn Bluetooth On");
        }else{
            bluetoothButton.setText("Turn Bluetooth Off");
        }

        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isEnabled()){
                    // Enabling bluetooth
                    Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bluetoothIntent, Bluetooth_request_code);
                } else{
                    // if the bluetooth is turned off by the user, present them with the option of turning it on
                    bluetoothAdapter.disable();
                    bluetoothButton.setText("Turn Bluetooth On");
                }

            }
        });
    }

    /*
       function to handle the user requesting the bluetooth feature of their smartphone to be turned on or off by taking in a request code
       If the request code received is of a result OK, turn on the bluetooth and set the text button to turn of bluetooth
       If the result code is to cancel the bluetooth, turn off bluetooth &
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Toast.makeText(MainActivity.this,"Bluetooth is now on",Toast.LENGTH_SHORT).show();
            bluetoothButton.setText("Turn Bluetooth Off");
        }else{
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(MainActivity.this,"Bluetooth has been cancelled",Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Creating methods that will handle the onClick of the buttons on the
    public void streaming(android.view.View view){
        Toast.makeText(MainActivity.this, "Presenting audio streaming options",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getBaseContext(),StreamingList.class);
        startActivity(myIntent);
    }

    public void local(android.view.View View){
        Toast.makeText(MainActivity.this, "Presenting local audio options",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getBaseContext(),LocalPlayList.class);
        startActivity(myIntent);
    }


}