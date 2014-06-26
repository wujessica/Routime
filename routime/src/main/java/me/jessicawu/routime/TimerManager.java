package me.jessicawu.routime;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by jessica on 24/05/14.
 */
public class TimerManager {
    String [][] workout = {{"squats", "5"},
                           {"push-ups", "5"},
                           {"sit-ups", "5"}};
    private static int exerciseIndex = 0;


    void startWorkout (Activity a) {
            Bundle bundle = new Bundle();
            bundle.putString("EXERCISE_NAME", workout[0][0]);
            bundle.putString("TIME_STRING", workout[0][1]);
            //Log.d("exercises", workout[i][0]);
            TimerFragment timerFragment = new TimerFragment();
            timerFragment.setArguments(bundle);

            FragmentTransaction t = a.getFragmentManager().beginTransaction();
            t.replace(android.R.id.content, timerFragment);
            t.addToBackStack(null);

            exerciseIndex = 1;

            t.commit();

    }

    void nextExercise (Activity a) {
        if (exerciseIndex < workout.length) {
            Bundle bundle = new Bundle();
            bundle.putString("EXERCISE_NAME", workout[exerciseIndex][0]);
            bundle.putString("TIME_STRING", workout[exerciseIndex][1]);
            //Log.d("exercises", workout[i][0]);
            TimerFragment timerFragment = new TimerFragment();
            timerFragment.setArguments(bundle);

            FragmentTransaction t = a.getFragmentManager().beginTransaction();
            t.replace(android.R.id.content, timerFragment);
            t.addToBackStack(null);
            t.commit();
            exerciseIndex++;
        }
    }
}
