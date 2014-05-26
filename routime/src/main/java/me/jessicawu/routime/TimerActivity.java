package me.jessicawu.routime;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by jessica and scott  on 19/05/14.
 */
public class TimerActivity extends Activity implements View.OnClickListener {
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView timeLeft;
    public TextView exerciseNameTV;
    private int startingTime = 0;
    private long startTime = 0;
    private final long interval = 5;
    private String exerciseName = "";
    double time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
//        startingTime = Integer.parseInt(intent.getStringExtra(MainActivity.TIMER_AMOUNT));
//        startTime = startingTime * 1000;
//        exerciseName = intent.getStringExtra(MainActivity.EXERCISE);
//        exerciseNameTV = (TextView) this.findViewById(R.id.current_exercise);
//        exerciseNameTV.setText(exerciseName);
//
//        startB = (Button) this.findViewById(R.id.start);
//        startB.setOnClickListener(this);
//        timeLeft = (TextView) this.findViewById(R.id.timer);
//        countDownTimer = new MyCountDownTimer(startTime, interval);
//        timeLeft.setText(timeLeft.getText() + String.valueOf(startTime / 1000) + ".00");
    }

    @Override
    public void onClick(View v) {
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("PAUSE");
        } else {
            countDownTimer.cancel();
            countDownTimer = new MyCountDownTimer((long)time, interval);
            timerHasStarted = false;
            startB.setText("CONTINUE");
        }
    }

    public void restart(View v) {
        countDownTimer.cancel();
        countDownTimer = new MyCountDownTimer(startTime, interval);
        timeLeft.setText(String.valueOf(startTime / 1000) + ".00");
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer (long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            timeLeft.setText("Next!");
            //text.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished){
            //timeLeft.setText("" + millisUntilFinished / 1000 + "." + millisUntilFinished % 1000);
            time = (double) millisUntilFinished;
            DecimalFormat df = new DecimalFormat("0.00");
            timeLeft.setText(df.format(time/1000));
        }
    }
}
