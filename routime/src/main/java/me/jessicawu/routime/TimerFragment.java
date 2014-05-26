package me.jessicawu.routime;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by jessica on 23/05/14.
 */
public class TimerFragment extends Fragment implements OnClickListener {
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    private Button restartB;
    public TextView timeLeft;
    public TextView exerciseNameTV;
    private int startingTime = 0;
    private long startTime = 0;
    private final long interval = 5;
    double time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container,
                false);
        startingTime = Integer.parseInt(getArguments().getString("timeString"));
        startTime = startingTime * 1000;
        exerciseNameTV = (TextView) v.findViewById(R.id.current_exercise);
        exerciseNameTV.setText(getArguments().getString("exerciseName"));

        startB = (Button) v.findViewById(R.id.start);
        startB.setOnClickListener(this);

        restartB = (Button) v.findViewById(R.id.restart);
        restartB.setOnClickListener(this);

        timeLeft = (TextView) v.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval, this);
        timeLeft.setText(timeLeft.getText() + String.valueOf(startTime / 1000) + ".00");

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if(!timerHasStarted) {
                    countDownTimer.start();
                    timerHasStarted = true;
                    startB.setText("PAUSE");
                } else {
                    countDownTimer.cancel();
                    countDownTimer = new MyCountDownTimer((long)time, interval, this);
                    timerHasStarted = false;
                    startB.setText("CONTINUE");
                }
                break;

            case R.id.restart:
                countDownTimer.cancel();
                countDownTimer = new MyCountDownTimer(startTime, interval, this);
                timeLeft.setText(String.valueOf(startTime / 1000) + ".00");
                startB.setText("START");
                timerHasStarted = false;
                break;
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        private Fragment fragment;
        public MyCountDownTimer (long startTime, long interval, Fragment f) {
            super(startTime, interval);
            fragment = f;
        }

        @Override
        public void onFinish() {
            //timeLeft.setText("Next!");
            //destroy current fragment
            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            ft.remove(fragment);

            //populate the next fragment
            TimerManager tm = new TimerManager();
            tm.nextExercise(getActivity());

            ft.commit();

        }

        @Override
        public void onTick(long millisUntilFinished){
            time = (double) millisUntilFinished;
            DecimalFormat df = new DecimalFormat("0.00");
            timeLeft.setText(df.format(time/1000));
        }
    }
}
