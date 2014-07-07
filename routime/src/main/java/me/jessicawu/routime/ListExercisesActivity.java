package me.jessicawu.routime;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
* Created by scottso on 2014-05-29.
*/
public class ListExercisesActivity extends Activity implements AddWorkoutDialog.OnDataPass{
    private ListView  listView;
    private ArrayList<String> workout;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/SourceSansPro-Black.ttf", R.attr.fontPath);
        setContentView(R.layout.activity_listexercises);

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
            }
        });



        listView = (ListView) findViewById(R.id.exercise_list);
        workout = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, workout);

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
    }

    @Override
    public void onDataPass(String exercise, String time) {
        workout.add(exercise);
        workout.add(time);

        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    public void onAdd() {
        DialogFragment newFragment = new AddWorkoutDialog();
        newFragment.show(getFragmentManager(), "AddWorkout");
    }
}
