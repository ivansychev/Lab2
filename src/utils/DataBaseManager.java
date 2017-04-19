package utils;
import gens.*;


import java.lang.reflect.Field;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.*;
import java.util.GregorianCalendar;

public class DataBaseManager {
    public Connection initConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost/app2act",
                            "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void select() {

    }

    public Users doUserPOJ()
    {
        Connection connection = initConnection();
        Users users = new Users();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.users");
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            while (result.next()) {
                User user = new User();
                user.setUserId(result.getInt(1));
                user.setUserRights(result.getString(2));
                user.setUserFirstname(result.getString(3));
                user.setUserSurname(result.getString(4));
                user.setUserEmail(result.getString(5));
                user.setUserPassword(result.getString(6));
                user.setUserMobile(result.getString(7));
                users.getUser().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Meals doMealsPOJ() {
        Connection connection = initConnection();
        Meals meals = new Meals();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.meals");
            while (result.next()) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(result.getDate(3));
                XMLGregorianCalendar date = null;
                try {
                    date = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                }
                Meal meal = new Meal();
                meal.setMealId(result.getInt(1));
                meal.setUserId(result.getInt(2));
                meal.setMealDate(date);
                meal.setMealKcal(result.getInt(4));
                meal.setMealProtein(result.getInt(5));
                meal.setMealCarbon(result.getInt(6));
                meal.setMealFat(result.getInt(7));
                meals.getMeal().add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    public Exercises doExercisesPOJ()
    {
        Connection connection = initConnection();
        Exercises exercises = new Exercises();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.exercises");
            while (result.next()) {
                Exercise exercise = new Exercise();
                exercise.setExerciseId(result.getInt(1));
                exercise.setUserId(result.getInt(2));
                exercise.setExerciseName(result.getString(3));
                exercise.setExerciseSet(result.getInt(4));
                exercise.setExerciseReps(result.getInt(5));
                exercise.setExerciseDurationMins(result.getFloat(6));
                exercise.setExerciseWeightKg(result.getFloat(7));
                exercises.getExercise().add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public Schedules doSchedulesPOJ()
    {
        Connection connection = initConnection();
        Schedules schedules = new Schedules();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.exercises");
            while (result.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(result.getInt(1));
                schedule.setUserId(result.getInt(2));
                schedule.setExerciseId(result.getInt(3));
                schedule.setExerciseOrder(result.getInt(4));
                schedules.getSchedule().add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public void getFullDataFromTable(String tableName)
    {
        Connection connection = initConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public."+tableName);
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String type = resultSetMetaData.getColumnClassName(i).replaceAll(".*(?=\\.).","");
                    switch (type)
                    {
                        case "Integer":
                            System.out.println(result.getInt(i));
                            break;
                        case "Float":
                            System.out.println(result.getFloat(i));
                            break;
                        case "Date":
                            System.out.println(result.getDate(i));
                            break;
                        default:
                            System.out.println(result.getString(i));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> void create(T object)
    {
        Connection connection = initConnection();
        String dbName = object.getClass().getName().replaceAll(".*(?=\\.).","").toLowerCase()+"s";

        String qr = "";
        if (dbName.equals("users")) qr = "user_rights,user_firstname,user_surname,user_email,user_password,user_mobile";
        if (dbName.equals("meals")) qr = "user_user_id, meal_date, meal_kcal, meal_proteins, meal_carbons, meal_fat";
        if (dbName.equals("schedules")) qr = "user_user_id, exercise_exercise_id, exercise_order";
        if (dbName.equals("exercises")) qr = "user_user_id, exercise_name, exercise_set, exercise_reps, exercise_duration_mins, exercise_weight_kg";

        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO "+dbName+"("+ qr+ ")" + " VALUES "+object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recreateUsersFromXMLFile(Users users) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users(" +
                            " user_id, user_rights, user_firstname, user_surname, user_email, user_password, user_mobile)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)");
            int i = 0;
            for (User user: users.getUser()) {
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setString(2, user.getUserRights());
                preparedStatement.setString(3, user.getUserFirstname());
                preparedStatement.setString(4, user.getUserSurname());
                preparedStatement.setString(5, user.getUserEmail());
                preparedStatement.setString(6, user.getUserPassword());
                preparedStatement.setString(7, user.getUserMobile());
                preparedStatement.addBatch();

                if (++i % 1000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public java.sql.Date convGregXmlToDateSql(XMLGregorianCalendar gregXML)
    {
        long time = gregXML.toGregorianCalendar().getTime().getTime();
        return new Date(time);
    }

    public void recreateMealsFromXMLFile(Meals meals) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO meals(\n" +
                            " meal_id ,user_user_id, meal_date, meal_kcal, meal_proteins, meal_carbons, meal_fat)\n" +
                            " VALUES (?,?,?,?,?,?,?)");
            int i = 0;
            for (Meal meal: meals.getMeal()) {
                preparedStatement.setInt(1, meal.getMealId());
                preparedStatement.setInt(2, meal.getUserId());
                preparedStatement.setDate(3, convGregXmlToDateSql(meal.getMealDate()));
                preparedStatement.setInt(4, meal.getMealKcal());
                preparedStatement.setInt(5, meal.getMealProtein());
                preparedStatement.setInt(6, meal.getMealCarbon());
                preparedStatement.setInt(7, meal.getMealFat());
                preparedStatement.addBatch();

                if (++i % 1000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recreateExercisesFromXMLFile(Exercises exercises) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO exercises(" +
                            " exercise_id, user_user_id, exercise_name, exercise_set, exercise_reps, exercise_duration_mins, exercise_weight_kg)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)");
            int i = 0;
            for (Exercise exercise: exercises.getExercise()) {
                preparedStatement.setInt(1, exercise.getExerciseId());
                preparedStatement.setInt(2, exercise.getUserId());
                preparedStatement.setString(3, exercise.getExerciseName());
                preparedStatement.setInt(4, exercise.getExerciseSet());
                preparedStatement.setInt(5, exercise.getExerciseReps());
                preparedStatement.setFloat(6, exercise.getExerciseDurationMins());
                preparedStatement.setFloat(7, exercise.getExerciseWeightKg());
                preparedStatement.addBatch();

                if (++i % 1000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recreateSchedulesFromXMLFile(Schedules schedules) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO schedules(" +
                            " schedule_id, user_user_id, exercise_exercise_id, exercise_order)" +
                            " VALUES (?, ?, ?, ?)");
            int i = 0;
            for (Schedule schedule: schedules.getSchedule()) {
                preparedStatement.setInt(1, schedule.getScheduleId());
                preparedStatement.setInt(2, schedule.getUserId());
                preparedStatement.setInt(3, schedule.getExerciseId());
                preparedStatement.setInt(4, schedule.getExerciseOrder());
                preparedStatement.addBatch();

                if (++i % 1000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*public void update() {
    }*/

    public void delete() {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM users WHERE user_id > 2");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
