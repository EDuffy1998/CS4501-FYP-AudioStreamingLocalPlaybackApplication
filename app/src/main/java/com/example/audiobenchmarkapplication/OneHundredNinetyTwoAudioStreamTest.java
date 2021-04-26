package com.example.audiobenchmarkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.os.Message;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

/*
    Student Name: Edward Duffy
    Student Number: 117501529
    Date: 21/01/2021

    Final Year Project:
    Project: Benchmarks for Many Core Smart Phones
    Project Supervisor: Dr. Dan Grigoras

    Application: Audio Benchmark Application

    Activity Goal:
    - To be able to play audio that is of a rate of 24kbit's and be able to monitor how the mobile device executes such a task
    - The mobile device is monitored using the Profiler tool which is integrated with the Android Studio IDE



 */

public class OneHundredNinetyTwoAudioStreamTest extends AppCompatActivity {

    /*
        Initializing the following variables
        Play button
        position bar, volume bar
        Remaining time label, time passed/elapsed time label
        Mediaplayer to handle the content
        Variable containing the total amount of time remaining
     */
    Button playButton;
    SeekBar positionbar, volumebar;
    TextView remainingTimeLabel, elapsedTimeLabel;
    MediaPlayer mediaplayer;
    int TotalAmountOfTime;



    /*
        Activity is loaded by the end-user
        User interface is loaded & presented to the user
        Playbutton, elapsedtimelabel & reamining time label assigned values relating to the ID's of the user interface components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_hundred_ninety_two_kbps_audio_test);

        // Getting the ID's of the buttons and labels
        playButton = findViewById(R.id.playBtn);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);

        /*
            Setting up the Media Player
            The url of the resource data is parsed
            Initialising it with the raw audio file which is located in the raw folder/directory
            .setLooping set to true so once the audio ends it restarts without prompt from the end-user
            .seekTo starts the audio file at the beginning
            .setVolume is used to set the Volume of the smartphone during audio playback
            Credit: The music was obtained when searching for copyright free music
            Software/Website used to obtain the raw audio: https://www.youtube.com/watch?v=kIab29rBHa0
         */

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/cs4501-audio-streaming-app.appspot.com/o/onehundredninetytwokbps.mp3?alt=media&token=ed6b68ef-af92-41fe-9968-dfbd28fc63c9");
        mediaplayer = MediaPlayer.create(this, uri);
        mediaplayer.setLooping(true);
        mediaplayer.seekTo(0);
        mediaplayer.setVolume(0.5f,0.5f);
        TotalAmountOfTime = mediaplayer.getDuration();

        // Initializing the position bar
        positionbar = findViewById(R.id.positionBar);
        // Setting the maximum of the position bar to the songs/audio files length
        positionbar.setMax(TotalAmountOfTime);
        positionbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser){
                            mediaplayer.seekTo(progress);
                            positionbar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // Initializing the volume bar
        volumebar = findViewById(R.id.volumeBar);
        volumebar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float VolumeNumber = progress / 100f;
                        mediaplayer.setVolume(VolumeNumber, VolumeNumber);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // Creating a thread that updates the position bar and the timer
        new Thread(new Runnable() {
            @Override
            public void run() {
                while  (mediaplayer != null){
                    try {
                        Message msg = new Message();
                        msg.what = mediaplayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored){}
                }
            }
        }).start();

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int currentPosition = msg.what;

            // Updating the position bar as the music is being played
            positionbar.setProgress(currentPosition);

            // Updating the labels as the music/audio is being played
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            // Creating the remaining time
            String TimeRemaining = "-" + createTimeLabel(TotalAmountOfTime - currentPosition);
            remainingTimeLabel.setText(TimeRemaining);

            return true;

        }
    });

    // Creating the label for the amount of time the audio file's been playing for
    public String createTimeLabel(int time){

        /*
            Intializing the timelabel variable
            creating variables to contain minutes & seconds
         */
        String timeLabel = "";
        int minutes = time / 1000 / 60;
        int seconds = time / 1000 % 60;

        /*
            Timelabel is equal to the amount of time remaining in minutes & seconds
            if there is less than ten seconds remaining on the time, time is formatted minute: 0 + seconds
            Timelabel is returned to the user for every second of playback
         */
        timeLabel = minutes + ":";
        if (seconds < 10) timeLabel += "0";
        timeLabel += seconds;

        return timeLabel;
    }

    /*
        Function to handle a button press by the user

        1st time the user presses the play button the audio file plays and the play button changes to the pause button

        2nd time the user presses the now pause button, playback resumes & the pause button changes to the playbutton
     */
    public void playButtonClick(View view){

        if (!mediaplayer.isPlaying()){

            // Stopping the music
            mediaplayer.start();
            playButton.setBackgroundResource(R.drawable.ic_launcher_background); // Stop
        } else {
            // Playing the audio
            mediaplayer.pause();
            playButton.setBackgroundResource(R.drawable.ic_launcher_background); // play
        }
    }
}