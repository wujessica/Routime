package me.jessicawu.routime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by scottso on 2014-05-29.
 */
public class ListExcercisesActivity extends Activity {


    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworkout);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.routineList);

        Intent intent = getIntent();

        String exerciseName = intent.getStringExtra(AddWorkoutActivity.LIST_EXERCISE);
        String message = intent.getStringExtra(AddWorkoutActivity.LIST_TIMER_AMOUNT);


        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View"

        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition  = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);



            }

            public void addToList() {




            }

        });
    }



}
