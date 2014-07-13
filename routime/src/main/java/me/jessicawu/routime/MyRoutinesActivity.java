package me.jessicawu.routime;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by jessica on 06/07/14.
 */
public class MyRoutinesActivity extends Activity {
    private ListView listView;
    private ArrayList<ListWorkoutItem> myRoutines;
    private ListWorkoutViewAdapter adapter;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault("fonts/SourceSansPro-Black.ttf", R.attr.fontPath);
        setContentView(R.layout.activity_myroutines);

        listView = (ListView) findViewById(R.id.routine_list);
        myRoutines = new ArrayList<ListWorkoutItem>();
        adapter = new ListWorkoutViewAdapter(this, R.layout.list_workout_item, myRoutines);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(i);
                return true;
            }
        });
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListWorkoutItem routine = (ListWorkoutItem) listView.getItemAtPosition(position);
                String routineName = routine.getWorkoutName();
                startRoutine(routineName);
            }
        });

        tv = (TextView) findViewById(R.id.no_routines);
        loadRoutines();
    }

    private void startRoutine(String routineName) {
        TimerManager.startWorkout(routineName, this);
    }

    public void loadRoutines() {
        String routine;
        int totalDuration = 0;
        FileManager.refreshFiles(this);

        if (FileManager.routineCount == 0) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);

            for (int i = 0; i < FileManager.routineCount; i++) {
                //TODO: think of a better way than just calculating etc on load
                ArrayList<ListExercisesItem> currentRoutine = FileManager.findAndReadFile(FileManager.fileNames[i], this);

                for (int k = 0; k < currentRoutine.size(); k++) {
                    totalDuration += Integer.parseInt(currentRoutine.get(k).getDuration());
                }
                routine = FileManager.fileNames[i];


                ListWorkoutItem item = new ListWorkoutItem(routine, String.valueOf(totalDuration));
                myRoutines.add(item);
                totalDuration = 0;
            }

            listView.setAdapter(adapter);

            for (int i = 0; i < FileManager.routineCount; i++) {
                Log.d("test", FileManager.fileNames[i]);
            }
        }
    }

    public void showDeleteDialog(final int itemPosition) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.dialog_delete_prompt))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.button_yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        deleteFile(itemPosition);
                        refreshView();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(getString(R.string.button_cancel),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void deleteFile(int itemPosition) {
        FileManager.deleteFile(FileManager.fileNames[itemPosition], this);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }

    public void refreshView() {
        //TODO: set up refresh and auto remove from listivew
    }
}
