package ar.edu.unlam.tallerweb1.domain.mercadoPago;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.HttpEntity;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioMercadoPagoImpl implements  ServicioMercadoPago {
    private final String API_URL = "https://api.mercadopago.com/checkout/preferences";
    private final String ACCESS_TOKEN = "TEST-5599099813004507-070419-16a9a1be36cf4d15bf0d4d97dc9387b6-221225807"; // Reemplaza por tu propio token de acceso

    @Override
    public String crearPedido() throws MPException, MPApiException, IOException {

        MercadoPagoConfig.setAccessToken("TEST-5599099813004507-070419-16a9a1be36cf4d15bf0d4d97dc9387b6-221225807");

        // creado un pedido
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("793324980")
                        .title("Games")
                        .description("PS5")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("games")
                        .quantity(2)
                        .currencyId("BRL")
                        .unitPrice(new BigDecimal("100"))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).build();
        PreferenceClient client = new PreferenceClient();
        client.create(preferenceRequest);

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.add("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(items.toString(),headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        String preferenceId = null;
        if (response.getStatusCode().is2xxSuccessful()) {
            // Analizar el cuerpo de la respuesta JSON para obtener el preferenceId
            JsonNode responseJson = new ObjectMapper().readTree(responseBody);
            preferenceId = responseJson.get("preferenceId").asText();
        }

        return preferenceId;
    }
}
