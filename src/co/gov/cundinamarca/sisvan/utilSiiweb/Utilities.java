package co.gov.cundinamarca.sisvan.utilSiiweb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Clase utilitaria
 *
 * @author Andres Cano
 */
public class Utilities {

    public static <T> String toJson(T object) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .create();
        Type type = new TypeToken<T>() {
        }.getType();
        return gson.toJson(object, type);
    }


    public static <T> T readFromJson(String json, Type type) {
        T object = null;
        if (json != null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                    .create();
            object = gson.fromJson(json, type);
        }
        return object;
    }

}
