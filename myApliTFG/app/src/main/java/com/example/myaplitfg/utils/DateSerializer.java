package com.example.myaplitfg.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

// Clase encargada de serializar y deserializar objetos Date en formato JSON
public class DateSerializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    // Método para deserializar un JSON en un objeto Date
    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        // Obtener la fecha como una cadena de texto del elemento JSON
        String date = je.getAsString();
        // Crear un formateador de fecha con el formato esperado
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        // Establecer la zona horaria del formateador al predeterminado del sistema
        formatter.setTimeZone(TimeZone.getDefault());
        try {
            // Intentar parsear la cadena de fecha y devolver el objeto Date resultante
            return new Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            // Manejar cualquier error de análisis de fecha
            System.err.println("Failed to parse Date due to:" + e.getMessage());
            return null;
        }
    }

    // Método para serializar un objeto Date a JSON
    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        // Crear un formateador de fecha con el formato esperado
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        // Establecer la zona horaria del formateador al predeterminado del sistema
        formatter.setTimeZone(TimeZone.getDefault());
        try {
            // Formatear la fecha y devolverla como un elemento JSON primitivo
            return new JsonPrimitive(formatter.format(date));
        } catch (Exception e) {
            // Manejar cualquier error de formateo de fecha
            System.err.println("Failed to parse Date due to:" + e.getMessage());
            return null;
        }
    }
}
