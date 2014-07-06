package me.jessicawu.routime;

/**
 * Created by jessica on 06/07/14.
 */
public class ListExercisesItem {
    private String exerciseName;
    private String duration;

    public ListExercisesItem(String exerciseName, String duration) {
        this.exerciseName = exerciseName;
        this.duration = duration;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setExerciseName (String name) {
        this.exerciseName = name;
    }

    public void setDuration (String time) {
        this.duration = time;
    }
}
