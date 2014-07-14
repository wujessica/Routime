package me.jessicawu.routime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddWorkoutNameDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_addworkoutname, null);
        builder.setView(v);

        final EditText workoutName = (EditText) v.findViewById(R.id.workout_name);

        builder.setPositiveButton(R.string.button_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String workName = workoutName.getText().toString();
                if (workName.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.toast_empty_routine_name, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), ListExercisesActivity.class);
                    intent.putExtra("FILE_NAME", workName);
                    startActivity(intent);

                    AddWorkoutNameDialog.this.getDialog().cancel();
                }
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddWorkoutNameDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }
}


