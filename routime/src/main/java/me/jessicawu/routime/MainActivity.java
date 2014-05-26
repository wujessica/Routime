package me.jessicawu.routime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToTimer(View v) {
        EditText exercise = (EditText) findViewById(R.id.exercise_name);
        EditText timerAmount = (EditText) findViewById(R.id.timer_amount);
        String exerciseName = exercise.getText().toString();
        String message = timerAmount.getText().toString();

        TimerManager tm = new TimerManager();
        tm.startWorkout(this);

    }
}
