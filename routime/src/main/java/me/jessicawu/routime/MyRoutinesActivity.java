package me.jessicawu.routime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRoutinesActivity extends RoutimeActivity {
    private ListView listView;
    private ArrayList<ListWorkoutItem> myRoutines;
    private ListWorkoutViewAdapter adapter;

    private TextView noRoutimesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListWorkoutItem routine = (ListWorkoutItem) listView.getItemAtPosition(position);
                String routineName = routine.getWorkoutName();
                startRoutine(routineName);
            }
        });

        noRoutimesTV = (TextView) findViewById(R.id.no_routines);
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
            noRoutimesTV.setVisibility(View.VISIBLE);
        } else {
            noRoutimesTV.setVisibility(View.GONE);

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
        alertDialogBuilder
                .setMessage(getString(R.string.dialog_delete_prompt))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.button_yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        deleteFile(itemPosition);
                        refreshView(itemPosition);
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

    public void refreshView(int pos) {
        //TODO: set up refresh and auto remove from listview
        adapter.remove(adapter.getItem(pos));
        adapter.notifyDataSetChanged();
    }
}
