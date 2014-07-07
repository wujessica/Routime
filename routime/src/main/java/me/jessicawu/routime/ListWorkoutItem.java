package me.jessicawu.routime;

/**
 * Created by jessica on 06/07/14.
 */
public class ListWorkoutItem {
    private String workoutName;
    private String workoutDuration;

    public ListWorkoutItem(String name, String duration) {
        this.workoutName = name;
        this.workoutDuration = duration;
    }

    public String getExerciseName() {
        return workoutName;
    }

    public String getDuration() {
        return workoutDuration;
    }

    public void setExerciseName (String name) {
        this.workoutName = name;
    }

    public void setDuration (String time) {
        this.workoutDuration = time;
    }
}
