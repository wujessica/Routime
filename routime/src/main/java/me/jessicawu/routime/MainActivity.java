package me.jessicawu.routime;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends RoutimeActivity implements View.OnClickListener{

    private Button newWorkout;
    private Button loadWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newWorkout = (Button) this.findViewById(R.id.new_routine);
        newWorkout.setOnClickListener(this);

        loadWorkout = (Button) this.findViewById(R.id.load_routine);
        loadWorkout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_routine:
                goToAddWorkout();
                break;

            case R.id.load_routine:
                goToMyRoutines();
                break;

            case R.id.help:
                goToHelp();
                break;
        }
    }

    public void goToAddWorkout () {
        DialogFragment newFragment = new AddWorkoutNameDialog();
        newFragment.show(getFragmentManager(), "AddWorkoutNameDialog");
    }

    public void goToMyRoutines() {
        Intent intent = new Intent(this, MyRoutinesActivity.class);
        startActivity(intent);
    }

    public void goToHelp() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
}
