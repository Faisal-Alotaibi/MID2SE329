package com.faisal.mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time=(TextView)findViewById(R.id.textView);
        new CountDownTimer(6000, 10) {
            public void onTick(long millisUntilFinished) {
                time.setText("seconds remaining: " +new SimpleDateFormat("ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                time.setText("done!");
            }
        }.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(MainActivity.this,Master.class));
            }
        };
        Timer opening = new Timer();
        opening.schedule(task,6000);

    }
    }
