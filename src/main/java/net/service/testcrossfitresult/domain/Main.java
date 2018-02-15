package net.service.testcrossfitresult.domain;

import java.sql.SQLException;
import java.util.Date;
import net.service.testcrossfitresult.entity.Exercises;
import net.service.testcrossfitresult.entity.Results;
import net.service.testcrossfitresult.entity.WorkoutType;
import net.service.testcrossfitresult.helper.JSONHelper;
import net.service.testcrossfitresult.service.GenericService;
import net.service.testcrossfitresult.util.HibernateUtil;

public class Main {

    public static void main(String[] args) throws SQLException {

        Exercises exercises = new Exercises();
        Results results = new Results();
        WorkoutType wt = new WorkoutType();
        GenericService gs = new GenericService(Results.class);

//        JSONHelper helper = new JSONHelper(Results.class);
//        List<Results> list = gs.findAll();


//                

        String str
                = "{"
                + " \"userId\":\"77\","
                + " \"workoutDate\":\"01.12.2018\","
                + " \"result\":\"result one\","
                + " \"comment\":\"comment one\","
                + " \"workoutType\":\"1\","
                + " \"workoutExercise\":\"1\""
                + "}";

        
//        helper.createEntity(str);

//        wt = gson.fromJson(str, wt.getClass());
//        gs.add(results);
        Date date = new Date();
        results.setId(18);
        results.setComment("comment");
        results.setResult("result");
        results.setUserId(18);
        results.setWorkoutDate(date);
        results.setExercises(new Exercises());
        results.getExercises().setId(1);
        results.setWorkoutType(new WorkoutType());
        results.getWorkoutType().setId(2);
//        wt = (WorkoutType) gs.findById(2);
//        results = gson.fromJson(str, results.getClass());
        gs.update(results);
        HibernateUtil.shutdown();

    }
}
