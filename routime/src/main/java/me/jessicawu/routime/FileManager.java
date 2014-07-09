package me.jessicawu.routime;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by jessica on 02/07/14.
 */
public class FileManager {
    public static int routineCount = 0;
    public static ArrayList<String> fileNames = new ArrayList<String>();

    public void saveFile(String workoutName, ArrayList<ListExercisesItem> workout, Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(workoutName, Context.MODE_PRIVATE);
            for (int i = 0; i < workout.size(); i++) {
                outputStream.write(workout.get(i).getExerciseName().getBytes());
                outputStream.write(workout.get(i).getDuration().getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        routineCount++;
        fileNames.add(workoutName);
        Log.d("test","save called!" +  workoutName + " " + routineCount);
    }

    public ArrayList<ListExercisesItem> findAndReadFile(String workoutName, Context context) {
        ArrayList<ListExercisesItem> workout = new ArrayList<ListExercisesItem>();
        Boolean onExercise = true;

        try {
            FileInputStream fis = context.openFileInput(workoutName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            String exerciseName = "";
            String duration = "";

            while ((line = bufferedReader.readLine()) != null) {
                if (onExercise) {
                    exerciseName = line;
                    onExercise = false;
                } else {
                    duration = line;
                    ListExercisesItem item = new ListExercisesItem(exerciseName, duration);
                    workout.add(item);
                    onExercise = true;
                }
            }
            fis.close();
            return workout;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String workoutName, Context context) {
        context.deleteFile(workoutName);
        routineCount--;
        fileNames.remove(workoutName);
        Log.d("test", "delete called" + workoutName);
    }

    //TODO: make routines editable
    public void editExerciseInFile(String workoutName, String toReplaceExercise, String replacementExercise,
                                   String toReplaceTime, String replacementTime, Context context) {
        try {
            FileInputStream fis = context.openFileInput(workoutName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line == toReplaceExercise) {
                    //dostuff etc
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editAddToFile(String workoutName, String exercise, Context context) {
        try {
            FileInputStream fis = context.openFileInput(workoutName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line == exercise) {
                    //ugh
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editDeleteFromFile(String workoutName, String exercise, Context context) {
        //do this
    }
}
