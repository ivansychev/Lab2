import gens.Meals;
import gens.User;
import gens.Users;
import utils.DataBaseManager;
import utils.XMLManager;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Created by ivans on 16/04/2017.
 */
public class Main {
    public static void main(String args[]) throws DatatypeConfigurationException {

        //new DataBaseManager().insert();
        //new DataBaseManager().delete();
        //new DataBaseManager().getFullDataFromTable("meals");
        /*new XMLManager().exportXMLObjectToFile(new DataBaseManager().doUserPOJ(),"user.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doMealsPOJ(),"meals.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doExercisesPOJ(),"exercise.xml");
        new XMLManager().exportXMLObjectToFile(new DataBaseManager().doSchedulesPOJ(),"schedules.xml");*/

        Meals meals = new XMLManager().importObjectFromXMLFile(new Meals(), "meals.xml");
        new DataBaseManager().recreateMealsFromXMLFile(meals);

        //new XMLManager().exportXMLObjectToFile();
        //new XMLManager().importObjectFromXMLFile();
    }
}
