package ar.edu.unlam.tallerweb1.domain.mercadoPago;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import java.io.IOException;

public interface ServicioMercadoPago {

    String crearPedido(String nombreRefugio) throws MPException, MPApiException, IOException;
}
