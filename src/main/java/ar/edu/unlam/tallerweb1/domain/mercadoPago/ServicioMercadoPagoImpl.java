package ar.edu.unlam.tallerweb1.domain.mercadoPago;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.resources.payment.PaymentItem;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioMercadoPagoImpl implements  ServicioMercadoPago {
    private final String API_URL = "https://api.mercadopago.com/checkout/preferences";
    private final String ACCESS_TOKEN = "TEST-5599099813004507-070419-16a9a1be36cf4d15bf0d4d97dc9387b6-221225807"; // Reemplaza por tu propio token de acceso

    @Override
    public String crearPedido(String nombreRefugio) throws MPException, MPApiException, IOException {
        String apiKey = "TEST-5599099813004507-070419-16a9a1be36cf4d15bf0d4d97dc9387b6-221225807";

        // Replace with the Mercado Pago API endpoint URL
        URL url = new URL("https://api.mercadopago.com/checkout/preferences");

        // Create an HTTP connection to the API endpoint
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

        // Create a JSON object with the data to send to the API
        String jsonInputString = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"title\": \""+nombreRefugio + "\",\n" +
                "      \"description\": \"refugio de animales\",\n" +
                "      \"category_id\": \"refugios\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"currency_id\": \"ARS\",\n" +
                "      \"unit_price\": 100\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        // Write the JSON object to the output stream of the HTTP connection
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response from the API
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Parse the response JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract the preference ID
            String preferenceId = jsonResponse.getString("id");

            // Print the preference ID
            return preferenceId;
        }
    }
}
