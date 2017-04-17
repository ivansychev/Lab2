package models;

/**
 * Created by ivans on 16/04/2017.
 */
public class Exercise {
    private int exercise_id;
    private int user_id;
    private String exercise_name;
    private int exercise_set;
    private int exercise_reps;
    private float exercise_duration_mins;
    private float exercise_weight_kg;

    public Exercise(int exercise_id, int user_id, String exercise_name, int exercise_set, int exercise_reps, float exercise_duration_mins, float exercise_weight_kg) {
        this.exercise_id = exercise_id;
        this.user_id = user_id;
        this.exercise_name = exercise_name;
        this.exercise_set = exercise_set;
        this.exercise_reps = exercise_reps;
        this.exercise_duration_mins = exercise_duration_mins;
        this.exercise_weight_kg = exercise_weight_kg;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public int getExercise_set() {
        return exercise_set;
    }

    public int getExercise_reps() {
        return exercise_reps;
    }

    public float getExercise_duration_mins() {
        return exercise_duration_mins;
    }

    public float getExercise_weight_kg() {
        return exercise_weight_kg;
    }
}
