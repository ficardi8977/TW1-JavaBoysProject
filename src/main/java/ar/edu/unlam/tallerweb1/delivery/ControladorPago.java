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
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/pago/preferencia", method = RequestMethod.POST)
    public ResponseEntity<String> generarPreferencia(@RequestBody String nombreRefugio) {
        try {
            String  idPreferencia =  this.servicioMercadoPago.crearPedido(nombreRefugio);
            return new ResponseEntity<>(idPreferencia, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
