package me.jessicawu.routime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by scottso on 2014-05-29.
 */
public class ListExercisesActivity extends Activity {


    ListView  listView;

    ArrayList<String> workout;

    ArrayAdapter<String> adapter;

    private boolean first = true;

    public void updateList(View view) {
        Intent intent = getIntent();

        String exercise = intent.getStringExtra(AddWorkoutActivity.LIST_EXERCISE);
        String time = intent.getStringExtra(AddWorkoutActivity.LIST_TIMER_AMOUNT);

        workout.add(exercise);
        workout.add(time);

        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // ListView Clicked item index
//                int itemPosition  = position;
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//            }
//        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addworkout);

        if (first) {
            // Get ListView object from xml
            listView = (ListView) findViewById(R.id.routineList);
            workout = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, workout);
            first = false;
        }

        updateList(findViewById(R.id.routineList));
    }
}
