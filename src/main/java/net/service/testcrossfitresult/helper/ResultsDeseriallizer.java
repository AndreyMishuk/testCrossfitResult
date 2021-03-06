package net.service.testcrossfitresult.helper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.service.testcrossfitresult.entity.Exercises;
import net.service.testcrossfitresult.entity.Results;
import net.service.testcrossfitresult.entity.WorkoutType;

public class ResultsDeseriallizer implements JsonDeserializer<Results> {

    @Override
    public Results deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

        JsonObject json = je.getAsJsonObject();
        Results results = new Results();
        SimpleDateFormat fDate = new SimpleDateFormat("dd.MM.yyyy");
        Date parsDate = null;


        if(json.get("id").isJsonPrimitive()) {
            results.setId(json.get("id").getAsInt());
        }
        
        
        results.setComment(json.get("comment").getAsString());
        results.setResult(json.get("result").getAsString());
        results.setUserId(json.get("userId").getAsInt());

        try {
            parsDate = fDate.parse(json.get("workoutDate").getAsString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        results.setWorkoutDate(parsDate);
        
        results.setExercises(new Exercises());
        results.getExercises().setId(json.get("workoutExercise").getAsInt());
        results.setWorkoutType(new WorkoutType());
        results.getWorkoutType().setId(json.get("workoutType").getAsInt());
              
        return results;
    }

}
