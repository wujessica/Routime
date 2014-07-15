package me.jessicawu.routime;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TimerFragment extends Fragment implements OnClickListener {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    private Button restartB;

    public TextView fileName;

    private TextView timeLeft;
    private TextView exerciseNameTV;
    private TextView routineNameTV;

    private int startingTime = 0;
    private long startTime = 0;
    private final long interval = 5;
    private double time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_timer, container,
                false);

        fileName = (TextView) v.findViewById(R.id.routine_name);
        fileName.setText(getArguments().getString("FILE_NAME"));

        startingTime = Integer.parseInt(getArguments().getString("TIME_STRING"));
        startTime = startingTime * 1000;
        exerciseNameTV = (TextView) v.findViewById(R.id.current_exercise);
        exerciseNameTV.setText(getArguments().getString("EXERCISE_NAME"));
        routineNameTV = (TextView) v.findViewById(R.id.routine_name);
        routineNameTV.setText(TimerManager.ROUTINE_NAME);

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
                    startB.setText(R.string.button_pause);
                } else {
                    countDownTimer.cancel();
                    countDownTimer = new MyCountDownTimer((long)time, interval, this);
                    timerHasStarted = false;
                    startB.setText(R.string.button_continue);
                }
                break;

            case R.id.restart:
                countDownTimer.cancel();
                countDownTimer = new MyCountDownTimer(startTime, interval, this);
                timeLeft.setText(String.valueOf(startTime / 1000) + ".00");
                startB.setText(R.string.button_start);
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
            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            ft.remove(fragment);

            TimerManager.nextExercise(getActivity());

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
