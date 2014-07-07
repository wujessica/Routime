package me.jessicawu.routime;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jessica on 06/07/14.
 */
public class MyRoutinesActivity extends Activity {
    private ListView listView;
    private ArrayList<ListWorkoutItem> myRoutines;
    private ListWorkoutViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myroutines);

        listView = (ListView) findViewById(R.id.routine_list);
        myRoutines = new ArrayList<ListWorkoutItem>();
        adapter = new ListWorkoutViewAdapter(this, R.layout.list_workout_item, myRoutines);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
            }
        });

        loadRoutines();
    }

    public void loadRoutines() {
        FileManager fm = new FileManager();
        String exercise = "";
        String duration = "";
        int totalDuration = 0;

        for (int i = 0; i < fm.routineCount; i++) {
            //TODO: think of a better way than just calculating etc on load
            ArrayList<ListExercisesItem> currentRoutine =  fm.findAndReadFile(fm.fileNames.get(i), this);
            ListWorkoutItem item = new ListWorkoutItem(exercise, duration);
            myRoutines.add(item);
        }

        listView.setAdapter(adapter);
    }

//    @Override
//    public void onDataPass(String exercise, String time) {
//        ListExercisesItem item = new ListExercisesItem(exercise, time);
//        workout.add(item);
//
//        adapter.notifyDataSetChanged();
//
//        // Assign adapter to ListView
//        listView.setAdapter(adapter);
//    }

}
