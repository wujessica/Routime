package me.jessicawu.routime;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jessica on 24/06/14.
 */
public class AddWorkoutFragment extends Fragment {

    OnDataPass dataPasser;

    public interface OnDataPass {
        public void onDataPass(String exercise, String time);
    }


    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }

    public void passData(String exercise, String time) {
        dataPasser.onDataPass(exercise, time);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_addworkout, container, false);

        Button button = (Button) v.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdd();
            }
        });
        return v;
    }

    public void onClickAdd () {
        View v = getView();
        EditText exercise = (EditText) v.findViewById(R.id.exercise_name);
        EditText timerAmount = (EditText) v.findViewById(R.id.timer_amount);

        String exerciseName = exercise.getText().toString();
        String time = timerAmount.getText().toString();

        passData(exerciseName, time);

        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        //getActivity().getFragmentManager().popBackStack();
    }

}
