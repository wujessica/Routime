package me.jessicawu.routime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity{

    public final static String TIMER_AMOUNT = "me.jessicawu.routime.TIMER_AMOUNT";
    public final static String EXERCISE = "me.jessicawu.routime.EXERCISE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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
        Intent intent = new Intent(this, TimerActivity.class);
        EditText exercise = (EditText) findViewById(R.id.exercise_name);
        EditText timerAmount = (EditText) findViewById(R.id.timer_amount);
        String exerciseName = exercise.getText().toString();
        String message = timerAmount.getText().toString();
        intent.putExtra(EXERCISE, exerciseName);
        intent.putExtra(TIMER_AMOUNT, message);
        startActivity(intent);
    }
}
