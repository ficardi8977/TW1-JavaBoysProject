package ar.edu.unlam.tallerweb1.domain.mercadoPago;


import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.stereotype.Service;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.resources.preference.PreferenceBackUrls;
import com.mercadopago.resources.preference.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioMercadoPagoImpl implements  ServicioMercadoPago {
    @Override
    public void crearPedido() throws MPException, MPApiException {

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

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:8080//home")
                        .pending("http://localhost:8080//home")
                        .failure("http://localhost:8080//home")
                        .build();

        PreferenceRequest.builder().backUrls(backUrls).build();
    }
}
