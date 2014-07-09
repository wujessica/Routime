package me.jessicawu.routime;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
* Created by scottso on 2014-05-29.
*/
public class ListExercisesActivity extends Activity implements AddWorkoutDialog.OnDataPass{
    private ListView  listView;
    private ArrayList<ListExercisesItem> workout;
    private ListExercisesViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/SourceSansPro-Black.ttf", R.attr.fontPath);
        setContentView(R.layout.activity_listexercises);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
            }
        });

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        listView = (ListView) findViewById(R.id.exercise_list);
        workout = new ArrayList<ListExercisesItem>();
        adapter = new ListExercisesViewAdapter(this, R.layout.list_exercises_item, workout);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, workout);

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
        ListExercisesItem item = new ListExercisesItem(exercise, time);
        workout.add(item);

        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    public void onAdd() {
        DialogFragment newFragment = new AddWorkoutDialog();
        newFragment.show(getFragmentManager(), "AddWorkout");
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }

    public void onSubmit() {
        EditText routineNameET = (EditText) findViewById(R.id.routine_name);
        String routineName = routineNameET.getText().toString();

        //Save everything (name and workout)
        FileManager fm = new FileManager();
        fm.saveFile(routineName, workout, this);

        finish();
    }
}
