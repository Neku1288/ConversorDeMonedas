package API;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConeccionesAPI {
    private static final String API_KEY = "6d7c7e11f7080637bd2e6167";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static JsonObject getExchangeRates(String baseCurrency) throws IOException {
        String apiUrl = API_BASE_URL + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // verifica si la respuesta de la pagina no tiene error
        if (conn.getResponseCode() == 200) {
            // Read the response body
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBody = new StringBuilder();
            while (scanner.hasNextLine()) {
                responseBody.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            // convierte la cadena a un Json
            JsonParser parser = new JsonParser();
            return parser.parse(responseBody.toString()).getAsJsonObject();
        } else {
            throw new IOException("Failed to fetch exchange rates. Response code: " + conn.getResponseCode());
        }
    }
}