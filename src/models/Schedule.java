package models;

/**
 * Created by ivans on 16/04/2017.
 */
public class Schedule {
    private int schedule_id;
    private int user_id;
    private int excercise_id;
    private int excercise_order;

    public Schedule(int schedule_id, int user_id, int excercise_id, int excercise_order) {
        this.schedule_id = schedule_id;
        this.user_id = user_id;
        this.excercise_id = excercise_id;
        this.excercise_order = excercise_order;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getExcercise_id() {
        return excercise_id;
    }

    public int getExcercise_order() {
        return excercise_order;
    }
}
