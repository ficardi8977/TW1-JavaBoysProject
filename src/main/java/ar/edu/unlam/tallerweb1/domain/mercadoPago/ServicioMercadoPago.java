package ar.edu.unlam.tallerweb1.domain.mercadoPago;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

public interface ServicioMercadoPago {

    void crearPedido() throws MPException, MPApiException;
}
