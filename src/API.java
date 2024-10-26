import java.net.*;
import java.net.http.*;

public class API
{
    /** Metodo per eseguire un HTTP GET con una API generica
     *
     * @param urlString stringa con l'URL dell'API
     * @return una stringa in formato JSON con la risposta
     * @throws Exception se si verifica un'eccezione
     */
    public static String GET(String urlString) throws Exception
    {
        try
        {
            // crea un client HttpClient per gestire la richiesta HTTP
            HttpClient client = HttpClient.newHttpClient();

            // crea una richiesta
            HttpRequest request = HttpRequest.newBuilder() //nuova richiesta
                    .uri(new URI(urlString)) // indica l'url
                    .GET() //specifica il metodo
                    .header("Accept", "application/json") //richiede che la risposta sia JSON
                    .build();

            // crea una risposta, dove invio la request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // se il codice HTTP è 200 (OK)
            if (response.statusCode() == 200)
                return response.body(); // restituisce la risposta (come stringa formattata in JSON)
            else // altrimenti lancia un eccezione Runtime indicante il codice d'errore HTTP
                throw new RuntimeException("Failed: HTTP error code: " + response.statusCode());
        }
        catch (Exception e) // se avviene un'eccezione la passa al metodo chiamante
        {
            throw new Exception(e);
        }
    }

    /** Metodo per eseguire un HTTP POST con una API generica
     *
     * @param urlString stringa con l'URL dell'API
     * @param content stringa (formattata in JSON) con l'oggeto da POSTare
     * @return una stringa indicante cosa è stato caricato (formattato in JSON)
     * @throws Exception se si verifica un'eccezione
     */
    public static String POST(String urlString, String content) throws Exception
    {
        try
        {
            // crea un client HttpClient per gestire la richiesta HTTP
            HttpClient client = HttpClient.newHttpClient();

            // crea un oggetto HttpRequest per configurare e inviare la richiesta POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlString)) // Imposta l'URI dell'API
                    .POST(HttpRequest.BodyPublishers.ofString(content)) // Imposta il corpo della richiesta
                    .header("Content-Type", "application/json") // Specifica che il contenuto è in JSON
                    .header("Accept", "application/json") // Richiede una risposta in formato JSON
                    .build();

            // Invia la richiesta e ottiene una risposta come stringa
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Controlla se il codice di stato HTTP è "OK" (200)
            if (response.statusCode() == 200)
                return response.body(); // Restituisce il corpo della risposta come stringa
            else
                // Se il codice di stato non è 200, lancia un'eccezione con il codice di errore
                throw new RuntimeException("Failed: HTTP error code: " + response.statusCode());
        }
        catch (Exception e) // se avviene un'eccezione la passa al metodo chiamante
        {
            throw new Exception(e);
        }
    }

    /** Metodo per implementare un HTTP PUT con un'API generica
     *
     * @param urlString stringa dell'URL dell'API
     * @param content stringa (formattata in JSON) dell'oggetto da pubblicare
     * @return una stringa con l'oggetto aggiornato (sempre in JSON)
     * @throws Exception se si verifica un'eccezione
     */
    public static String PUT(String urlString, String content) throws Exception
    {
        try
        {
            // Crea un client HttpClient per gestire la richiesta HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea un oggetto HttpRequest per configurare e inviare la richiesta PUT
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlString)) // Imposta l'URI dell'API
                    .PUT(HttpRequest.BodyPublishers.ofString(content)) // Imposta il corpo della richiesta
                    .header("Content-Type", "application/json") // Specifica che il contenuto è in JSON
                    .header("Accept", "application/json") // Richiede una risposta in formato JSON
                    .build();

            // Invia la richiesta e ottiene una risposta come stringa
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Controlla se il codice di stato HTTP è "OK" (200)
            if (response.statusCode() == 200)
                return response.body(); // Restituisce il corpo della risposta come stringa
            else
                // Se il codice di stato non è 200, lancia un'eccezione con il codice di errore
                throw new RuntimeException("Failed: HTTP error code: " + response.statusCode());
        }
        catch (Exception e) // se avviene un'eccezione la passa al metodo chiamante
        {
            throw new Exception(e);
        }
    }

    /** Metodo per implementare un HTTP DELETE con un'API generica
     *
     * @param urlString stringa con l'URL dell'API
     * @return una stringa indicante se l'oggetto è stato eliminato
     * @throws Exception se si verifica un'eccezione
     */
    public static String DELETE(String urlString) throws Exception
    {
        try
        {
            // Crea un client HttpClient per gestire la richiesta HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crea un oggetto HttpRequest per configurare e inviare la richiesta DELETE
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlString)) // Imposta l'URI dell'API
                    .DELETE() // Imposta il metodo della richiesta come DELETE
                    .header("Accept", "application/json") // Richiede una risposta in formato JSON
                    .build();

            // Invia la richiesta e ottiene una risposta come stringa
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Controlla se il codice di stato HTTP è "NO CONTENT" (204) o "OK" (200)
            if (response.statusCode() == 204 || response.statusCode() == 200)
                return "Resource deleted successfully."; // Risorsa eliminata con successo
            else
                // Se il codice di stato non è 204 o 200, lancia un'eccezione con il codice di errore
                throw new RuntimeException("Failed: HTTP error code: " + response.statusCode());
        }
        catch (Exception e) // se avviene un'eccezione la passa al metodo chiamante
        {
            throw new Exception(e);
        }
    }
}
