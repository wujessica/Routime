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

/**
 * Created by scottso on 2014-07-07.
 */
public class AddWorkoutNameDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_addworkout, null);
        builder.setView(v);

        final EditText routineName = (EditText) v.findViewById(R.id.exercise);


        // Add action buttons
        builder.setPositiveButton(R.string.button_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String routineName = exercise.getText().toString();


                passData(routineName, time);
                AddWorkoutNameDialog.this.getDialog().cancel();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddWorkoutNameDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    OnDataPass dataPasser;

    public interface OnDataPass {
        public void onDataPass(String exercise, String time);
    }


    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        try {
            dataPasser = (OnDataPass) a;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(a.toString()
                    + " must implement OnDataPass");
        }
    }

    public void passData(String exercise, String time) {
        dataPasser.onDataPass(exercise, time);
    }
}


