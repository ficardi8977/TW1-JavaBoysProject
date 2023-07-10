package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.EncontrarMascotaExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mercadoPago.ServicioMercadoPago;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class ControladorPago {

    private ServicioMercadoPago servicioMercadoPago;
    @Autowired
    public ControladorPago(ServicioMercadoPago servicioMercadoPago) {
        this.servicioMercadoPago = servicioMercadoPago;
    }

    @RequestMapping(path = "/pago/preferencia",method = RequestMethod.POST)
    public ResponseEntity<String> getComentariosDeCuidado() throws MPException, MPApiException, IOException {

        // este lo genere previamente con la api de crear preferencia de mercado pago
        String  idPreferencia =  this.servicioMercadoPago.crearPedido();
        return new ResponseEntity<>(idPreferencia, HttpStatus.OK);
    }
}
