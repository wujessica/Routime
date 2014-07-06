package me.jessicawu.routime;

import android.content.Context;
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
    public void saveFile(String workoutName, ArrayList<String> workout, Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(workoutName, Context.MODE_PRIVATE);
            for (int i = 0; i < workout.size(); i++) {
                outputStream.write(workout.get(i).getBytes());
                outputStream.write(workout.get(++i).getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> findAndReadFile(String workoutName, Context context) {
        ArrayList<String> workout = new ArrayList<String>();

        try {
            FileInputStream fis = context.openFileInput(workoutName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                workout.add(line);
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

    public void deleteFile(String workoutName, Context context) {
        context.deleteFile(workoutName);
    }
}
