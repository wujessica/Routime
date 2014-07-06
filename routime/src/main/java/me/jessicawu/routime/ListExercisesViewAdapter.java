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
public class ListExercisesViewAdapter extends ArrayAdapter<ListExercisesItem> {
    Context context;

    public ListExercisesViewAdapter(Context context, int resource, List<ListExercisesItem> items) {
        super(context, resource, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView exerciseNameTV;
        TextView durationTV;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListExercisesItem item = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_exercises_item, null);
            holder = new ViewHolder();
            holder.exerciseNameTV = (TextView) convertView.findViewById(R.id.exercise_name);
            holder.durationTV = (TextView) convertView.findViewById(R.id.timer_amount);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.exerciseNameTV.setText(item.getExerciseName());
        holder.durationTV.setText(item.getDuration());

        return convertView;
    }
}
