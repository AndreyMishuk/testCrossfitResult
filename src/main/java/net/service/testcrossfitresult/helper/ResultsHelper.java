package net.service.testcrossfitresult.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.service.testcrossfitresult.service.GenericService;

public class ResultsHelper<T> {

    private GenericService gs;
    private final Gson gson;

    public ResultsHelper() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String getJsonAll(Class type) {

        gs = new GenericService(type);
        List<T> resultsList = null;

        try {
            resultsList = new ArrayList<>(gs.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(resultsList);
    }

    public String getResultsById(Class type, int id) {
        T entity = null;
        try {
            gs = new GenericService(type);
            entity = (T) gs.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(entity);
    }

}
