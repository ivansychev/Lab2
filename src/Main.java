import gens.*;
import utils.DataBaseManager;
import utils.XMLManager;

import java.lang.reflect.Field;
import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 * Created by ivans on 16/04/2017.
 */
public class Main {
    public static void main(String args[]) throws DatatypeConfigurationException, ClassNotFoundException {

        //new DataBaseManager().insert();
        //new DataBaseManager().delete();
        //new DataBaseManager().getFullDataFromTable("meals");
        /*new XMLManager().exportXMLObjectToFile(new DataBaseManager().doUserPOJ(),"user.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doMealsPOJ(),"meals.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doExercisesPOJ(),"exercise.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doSchedulesPOJ(),"schedules.xml");*/

        /*User user = new User();
        user.setUserRights("user");
        user.setUserFirstname("nahme");
        user.setUserSurname("surname");
        user.setUserEmail("email");
        user.setUserPassword("pass");
        user.setUserMobile("mobile");*/

        /*Exercise exercise = new Exercise();

        exercise.setUserId(1);
        exercise.setExerciseName("Pull Ups");
        exercise.setExerciseSet(4);
        exercise.setExerciseReps(10);
        exercise.setExerciseDurationMins(1.5f);
        exercise.setExerciseWeightKg(0);*/

        Schedule schedule = new Schedule();

        schedule.setUserId(1);
        schedule.setExerciseId(1);
        schedule.setExerciseOrder(1);

        new DataBaseManager().create(schedule);


       /* Meals meals = new XMLManager().importObjectFromXMLFile(new Meals(), "meals.xml");
        new DataBaseManager().recreateMealsFromXMLFile(meals);*/

        //new XMLManager().exportXMLObjectToFile();
        //new XMLManager().importObjectFromXMLFile();
    }
}
