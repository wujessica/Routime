package me.jessicawu.routime;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddWorkoutActivity extends ActionBarActivity{

    public final static String TIMER_AMOUNT = "me.jessicawu.routime.TIMER_AMOUNT";
    public final static String EXERCISE = "me.jessicawu.routime.EXERCISE";

    public final static String LIST_TIMER_AMOUNT = "me.jessicawu.routime.TIMER_AMOUNT";
    public final static String LIST_EXERCISE = "me.jessicawu.routime.EXERCISE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworkout);
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

    public void goToList(View view) {

        Intent intent = new Intent(this, ListExercisesActivity.class);

        EditText exercise = (EditText) findViewById(R.id.exercise_name);
        EditText timerAmount = (EditText) findViewById(R.id.timer_amount);

        String exerciseName = exercise.getText().toString();
        String message = timerAmount.getText().toString();

        intent.putExtra(LIST_EXERCISE, exerciseName);
        intent.putExtra(LIST_TIMER_AMOUNT, message);

        startActivity(intent);
    }


    public void goToTimer(View v) {

        Intent intent = new Intent(this, TimerFragment.class);

        EditText exercise = (EditText) findViewById(R.id.exercise_name);
        EditText timerAmount = (EditText) findViewById(R.id.timer_amount);

        String exerciseName = exercise.getText().toString();
        String message = timerAmount.getText().toString();
        intent.putExtra(EXERCISE, exerciseName);
        intent.putExtra(TIMER_AMOUNT, message);

        Bundle bundle = new Bundle();
        bundle.putString("EXERCISE_NAME", exerciseName);
        bundle.putString("TIME_STRING", message);

        TimerFragment timerFragment = new TimerFragment();
        timerFragment.setArguments(bundle);
        // Create new fragment and transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(android.R.id.content, timerFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
