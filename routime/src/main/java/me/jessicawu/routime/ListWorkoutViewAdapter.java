package me.jessicawu.routime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jessica on 06/07/14.
 */
public class ListWorkoutViewAdapter extends ArrayAdapter<ListWorkoutItem> {
    Context context;

    public ListWorkoutViewAdapter(Context context, int resource, List<ListWorkoutItem> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder {
        TextView workoutNameTV;
        TextView workoutDurationTV;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListWorkoutItem item = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_workout_item, null);
            holder = new ViewHolder();
            holder.workoutNameTV = (TextView) convertView.findViewById(R.id.workout_name);
            holder.workoutDurationTV = (TextView) convertView.findViewById(R.id.workout_duration);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.workoutNameTV.setText(item.getWorkoutName());
        holder.workoutDurationTV.setText(item.getWorkoutDuration());

        return convertView;
    }
}
