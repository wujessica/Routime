package me.jessicawu.routime;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by scottso on 2014-06-19.
 */
public class ListExercisesFragment extends Fragment {

    ListView listView;
    ArrayList<String> workout;
    ArrayAdapter<String> adapter;
    private boolean first = true;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_addworkout, container,false);

        if (first) {
            // Get ListView object from xml
            listView = (ListView) v.findViewById(R.id.routineList);
            workout = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, workout);
            first = false;
        }

        updateList(v.findViewById(R.id.routineList));

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void updateList(View v) {

        String exercise = intent.getStringExtra(AddWorkoutActivity.LIST_EXERCISE);
        String time = intent.getStringExtra(AddWorkoutActivity.LIST_TIMER_AMOUNT);

        workout.add(exercise);
        workout.add(time);

        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }





}



}
