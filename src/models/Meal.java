package models;

import java.util.Date;

/**
 * Created by ivans on 16/04/2017.
 */
public class Meal {
    private int meal_id;
    private int user_id;
    private Date meal_date;
    private int meal_kcal;
    private int meal_protein;
    private int meal_carbon;
    private int meal_fat;

    public Meal(int meal_id, int user_id, Date meal_date, int meal_kcal, int meal_protein, int meal_carbon, int meal_fat) {
        this.meal_id = meal_id;
        this.user_id = user_id;
        this.meal_date = meal_date;
        this.meal_kcal = meal_kcal;
        this.meal_protein = meal_protein;
        this.meal_carbon = meal_carbon;
        this.meal_fat = meal_fat;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getMeal_date() {
        return meal_date;
    }

    public int getMeal_kcal() {
        return meal_kcal;
    }

    public int getMeal_protein() {
        return meal_protein;
    }

    public int getMeal_carbon() {
        return meal_carbon;
    }

    public int getMeal_fat() {
        return meal_fat;
    }
}
