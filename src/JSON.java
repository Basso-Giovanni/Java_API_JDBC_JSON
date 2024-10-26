//ESEMPI DI UTILIZZO DI FILE JSON
//UTILIZZO DUE DIPENDENZE: org.json e gson, CHE DEVONO ESSERE IMPORTATE

import org.json.*;
import com.google.gson.*;

public class JSON
{
    /** Metodo per convertire un oggetto/classe in una stringa formattata in JSON
     *
     * @param obj oggetto da convertire
     * @return stringa in formato JSON dell'oggetto
     */
    public static String TO_JSON(Object obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);

        /*
            ESEMPIO DI OUTPUT:

            {
                "employees":
                [
                    {"name":"Shyam", "email":"shyamjaiswal@gmail.com"},
                    {"name":"Bob", "email":"bob32@gmail.com"},
                    {"name":"Jai", "email":"jai87@gmail.com"}
                ]
            }

        */
    }

    /** Metodo per convertire una stringa in formato JSON in un oggetto
     *
     * @param json stringa in formato json
     * @return oggetto rappresentato dalla stringa
     */
    public static Object FROM_JSON(String json)
    {
        /*
            ESEMPIO DI INPUT:

            {
                "employees":
                [
                    {"name":"Shyam", "email":"shyamjaiswal@gmail.com"},
                    {"name":"Bob", "email":"bob32@gmail.com"},
                    {"name":"Jai", "email":"jai87@gmail.com"}
                ]
            }

        */

        Gson gson = new Gson();
        return gson.fromJson(json, Object.class); //al posto di 'Object.class' va messo il nome della classe + '.class'
    }

    /** Metodo per costruire un oggetto JSON generico
     * Siccome deve essere adattato alle necessità del codice, verrà riportato un esempio di funzionamento
     * @return stringa rappresentante l'oggetto JSON
     */
    public static String CREA_JSON()
    {
        JSONObject json = new JSONObject(); // oggetto JSON
        JSONArray array = new JSONArray(); //array JSON

        JSONObject item = new JSONObject();
        item.put("name", "Shyam"); //con il metodo put posso aggiungere più JSONObject/JSONArray o tipi privati
        item.put("email", "shyamjaiswal@gmail.com");
        item.put("age", 20);
        array.put(item);

        item = new JSONObject();
        item.put("name", "Bob");
        item.put("email", "bob32@gmail.com");
        item.put("age", 44);
        array.put(item);

        item = new JSONObject();
        item.put("name", "Jai");
        item.put("email", "jai87@gmail.com");
        item.put("age", 38);
        array.put(item);

        json.put("employees", array);

        return json.toString();
    }
}
