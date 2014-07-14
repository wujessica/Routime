package me.jessicawu.routime;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListExercisesActivity extends RoutimeActivity implements AddWorkoutDialog.OnDataPass{
    private ListView  listView;
    private ArrayList<ListExercisesItem> workout;
    private ListExercisesViewAdapter adapter;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        TextView routineName = (TextView) findViewById(R.id.routine_name);
        Bundle bundle= getIntent().getExtras();
        String name = bundle.getString("FILE_NAME");
        fileName = name;
        routineName.setText(name);

        listView = (ListView) findViewById(R.id.exercise_list);
        workout = new ArrayList<ListExercisesItem>();
        adapter = new ListExercisesViewAdapter(this, R.layout.list_exercises_item, workout);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    private void showDeleteDialog(final int pos) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(getString(R.string.dialog_delete_exercise_prompt))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.button_yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        adapter.remove(adapter.getItem(pos));
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(getString(R.string.button_cancel),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onDataPass(String exercise, String time) {
        ListExercisesItem item = new ListExercisesItem(exercise, time);
        workout.add(item);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }

    public void onAdd() {
        DialogFragment newFragment = new AddWorkoutDialog();
        newFragment.show(getFragmentManager(), "AddWorkoutDialog");
    }

    public void onSubmit() {
        if (workout.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty_routine_warning, Toast.LENGTH_SHORT).show();
        } else {
            FileManager.saveFile(fileName, workout, this);
            finish();
        }
    }
}
