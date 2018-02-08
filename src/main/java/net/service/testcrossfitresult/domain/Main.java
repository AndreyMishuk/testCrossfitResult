package net.service.testcrossfitresult.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.service.testcrossfitresult.model.Results;
import net.service.testcrossfitresult.model.WorkoutType;
import net.service.testcrossfitresult.service.GenericService;
import net.service.testcrossfitresult.util.HIbernateUtil;

public class Main {

    public static void main(String[] args) {
        WorkoutType wt = new WorkoutType();
        Results results = new Results();
        GenericService gs;

        List<WorkoutType> workoutList = new ArrayList<>();
        List<Results> resultsList = new ArrayList<>();

        try {
            gs = new GenericService(wt.getClass());
            workoutList = gs.findAll();
            gs = new GenericService(results.getClass());
            resultsList = gs.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("********** begin Workout **************");
        for (WorkoutType value : workoutList) {
            System.out.println(value.getId() + " " + value.getName() + " " + value.getDescribtion());
        }
        System.out.println("********** end Workout **************");

        System.out.println("********** begin Results **************");
        for (Results value : resultsList) {
            System.out.println("id: " + value.getId() + " nameExercise: " + value.getExercises().getName() +
                    " Date: " + value.getWorkoutDate() + " nameWorkout: " + value.getWorkoutType().getName() +
                    " Coment: " + value.getComment() + " Result: " + value.getResult());
        }
        System.out.println("********** end Results **************");

        HIbernateUtil.shutdown();
    }
}
