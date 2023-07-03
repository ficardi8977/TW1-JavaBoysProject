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
        // Configurar las credenciales de Mercado Pago
        MercadoPagoConfig.setAccessToken("TEST-2059267312759682-062718-5ba84f97b8aa1a37f9449c9812a377bd-793324980");

        // creado un pedido
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("1234")
                        .title("Games")
                        .description("PS5")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("games")
                        .quantity(2)
                        .currencyId("BRL")
                        .unitPrice(new BigDecimal("4000"))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).build();
        PreferenceClient client = new PreferenceClient();
        client.create(preferenceRequest);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:8080//detalle-cuidador")
                        .pending("http://localhost:8080//mascota")
                        .failure("http://localhost:8080//detalle-cuidador")
                        .build();

        PreferenceRequest.builder().backUrls(backUrls).build();
// ...


    }
}
