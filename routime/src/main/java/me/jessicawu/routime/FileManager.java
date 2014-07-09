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
    public static String[] fileNames;
    public static int routineCount;

    public static void saveFile(String workoutName, ArrayList<ListExercisesItem> workout, Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(workoutName, Context.MODE_PRIVATE);
            for (int i = 0; i < workout.size(); i++) {
                outputStream.write(workout.get(i).getExerciseName().getBytes());
                outputStream.write((""+"\n").getBytes());
                outputStream.write(workout.get(i).getDuration().getBytes());
                outputStream.write((""+"\n").getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("test","save called: " +  workoutName);

        refreshFiles(context);
    }

    public static ArrayList<ListExercisesItem> findAndReadFile(String workoutName, Context context) {
        refreshFiles(context);

        ArrayList<ListExercisesItem> workout = new ArrayList<ListExercisesItem>();
        boolean onExercise = true;

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
                    Log.d("test", "exercise: " + exerciseName);
                } else {
                    duration = line;
                    ListExercisesItem item = new ListExercisesItem(exerciseName, duration);
                    workout.add(item);
                    Log.d("test", "duration: " + duration);
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

    public static void deleteFile(String workoutName, Context context) {
        context.deleteFile(workoutName);
        refreshFiles(context);
        Log.d("test", "delete called: " + workoutName);
    }

    //TODO: make routines editable
    public static void editExerciseInFile(String workoutName, String toReplaceExercise, String replacementExercise,
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
        refreshFiles(context);
    }

    public static void editAddToFile(String workoutName, String exercise, Context context) {
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
        refreshFiles(context);
    }

    public void editDeleteFromFile(String workoutName, String exercise, Context context) {
        //do this
    }

    public static void refreshFiles(Context context) {
        fileNames = context.fileList();
        routineCount = fileNames.length;
    }
}
