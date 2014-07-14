package me.jessicawu.routime;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

public class TimerManager {
    private static ArrayList<ListExercisesItem> routine;
    private static int exerciseIndex = 0;

    public static void startWorkout (String routineName, Activity a) {
        routine = FileManager.findAndReadFile(routineName, a);

        Bundle bundle = new Bundle();
        bundle.putString("EXERCISE_NAME", routine.get(0).getExerciseName());
        bundle.putString("TIME_STRING", routine.get(0).getDuration());

        TimerFragment timerFragment = new TimerFragment();
        timerFragment.setArguments(bundle);

        FragmentTransaction t = a.getFragmentManager().beginTransaction();
        t.replace(android.R.id.content, timerFragment);
        t.addToBackStack(null);

        exerciseIndex = 1;

        t.commit();
    }

    public static void nextExercise(Activity a) {
        if (exerciseIndex < routine.size()) {
            Bundle bundle = new Bundle();
            bundle.putString("EXERCISE_NAME", routine.get(exerciseIndex).getExerciseName());
            bundle.putString("TIME_STRING", routine.get(exerciseIndex).getDuration());
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
