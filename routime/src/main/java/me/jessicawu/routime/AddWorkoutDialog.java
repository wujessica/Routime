package me.jessicawu.routime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddWorkoutDialog extends DialogFragment {
    private OnDataPass dataPasser;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_addworkout, null);
        builder.setView(v);

        final EditText exercise = (EditText) v.findViewById(R.id.exercise);
        final EditText timerAmount = (EditText) v.findViewById(R.id.timer);

        builder.setPositiveButton(R.string.button_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String exerciseName = exercise.getText().toString();
                String time = timerAmount.getText().toString();

                passData(exerciseName, time);
                AddWorkoutDialog.this.getDialog().cancel();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddWorkoutDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    public interface OnDataPass {
        public void onDataPass(String exercise, String time);
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        try {
            dataPasser = (OnDataPass) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnDataPass");
        }
    }

    public void passData(String exercise, String time) {
        dataPasser.onDataPass(exercise, time);
    }
}
