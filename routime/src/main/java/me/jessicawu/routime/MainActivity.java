package me.jessicawu.routime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button newWorkout;
    private Button loadWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/SourceSansPro-Semibold.ttf", R.attr.fontPath);
        setContentView(R.layout.activity_main);

        newWorkout = (Button) this.findViewById(R.id.new_routine);
        newWorkout.setOnClickListener(this);

        loadWorkout = (Button) this.findViewById(R.id.load_routine);
        loadWorkout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.new_routine:

                goToAddWorkout(v);

                break;

            case R.id.load_routine:

                break;
        }

    }

    public void goToAddWorkout(View v) {
        Intent intent = new Intent(this, ListExercisesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }
}
