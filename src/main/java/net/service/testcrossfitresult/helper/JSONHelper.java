package net.service.testcrossfitresult.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.service.testcrossfitresult.entity.Exercises;
import net.service.testcrossfitresult.entity.Results;
import net.service.testcrossfitresult.entity.WorkoutType;
import net.service.testcrossfitresult.service.GenericService;

public class JSONHelper<T> {

    private GenericService gs;
    private final Gson gson;
    private final Class<T> type;

    public JSONHelper(Class<T> type) {
        this.type = type;
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Results.class, new ResultsSerializer())
                .registerTypeAdapter(WorkoutType.class, new WorkoutSerializer())
                .registerTypeAdapter(Exercises.class, new ExercisesSerializer())
                .registerTypeAdapter(Results.class, new ResulrDeseriallizer())
                .registerTypeAdapter(WorkoutType.class, new WorkoutDeserializer())
                .registerTypeAdapter(Exercises.class, new ExercisesDeserializer())
                .create();
    }
    
    public Class<T> getType() {
        return type;
    }

    public String getEntityAll() {

        gs = new GenericService(type);
        List<T> resultsList = null;

        try {
            resultsList = new ArrayList<>(gs.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(resultsList);
    }

    public String getEntityById(int id) {
        T entity = null;
        try {
            gs = new GenericService(type);
            entity = (T) gs.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(entity);
    }
    
    public void createEntity(String data) {
        try {
            gs = new GenericService(type);
            T entity = (T) gson.fromJson(data, type);
            gs.add(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
    }

}
