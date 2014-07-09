package me.jessicawu.routime;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteFile(i);
                return false;
            }
        });
        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // ListView Clicked item index
//                int itemPosition = position;
//
//                // ListView Clicked item value
//                String itemValue = (String) listView.getItemAtPosition(position);
//            }
//        });

        loadRoutines();
    }

    public void loadRoutines() {
        FileManager fm = new FileManager();
        String routine = "";
        int totalDuration = 0;

        for (int i = 0; i < fm.routineCount; i++) {
            //TODO: think of a better way than just calculating etc on load
            ArrayList<ListExercisesItem> currentRoutine =  fm.findAndReadFile(fm.fileNames.get(i), this);

            for(int k = 0; k < currentRoutine.size(); k++) {
                totalDuration += Integer.parseInt(currentRoutine.get(i).getDuration());
            }
            routine = fm.fileNames.get(i);

            ListWorkoutItem item = new ListWorkoutItem(routine, String.valueOf(totalDuration));
            myRoutines.add(item);
        }

        listView.setAdapter(adapter);
    }

    public void deleteFile(int itemPosition) {
        FileManager fm = new FileManager();
        fm.deleteFile(fm.fileNames.get(itemPosition), this);
    }
}
