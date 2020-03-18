package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.SeekBar;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MediaPlayer
        final MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(this, com.example.helloworld.R.raw.bazinga3);
        //image
        final ImageView imageView3 = findViewById(R.id.imageView3);
        //switch
        final Switch switch1 = findViewById(R.id.switch1);
        //volume
        final SeekBar sbVol = findViewById(R.id.sbVol);

        //night mode
        CheckBox cb = findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Theme");
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
            }
        });

        //set max and current volume
        final AudioManager am;
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sbVol.setMax(maxVol);
        int curVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbVol.setProgress(curVol);

        //SeekBar
        sbVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "SeekBar");
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                int curVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                sbVol.setProgress(curVol);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //start
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Start");
                if (switch1.isChecked()) {
                    Log.d(TAG, "Switch");
                    imageView3.setImageResource(R.mipmap.laugh);
                    mediaPlayer.start();
                } else {
                    imageView3.setImageResource(R.mipmap.nolaugh);
                }
            }
        });
    }
}
