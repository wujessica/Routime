package me.jessicawu.routime;

public class ListWorkoutItem {
    private String workoutName;
    private String workoutDuration;

    public ListWorkoutItem(String name, String duration) {
        this.workoutName = name;
        this.workoutDuration = duration;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutName (String name) {
        this.workoutName = name;
    }

    public void setWorkoutDuration (String time) {
        this.workoutDuration = time;
    }
}
