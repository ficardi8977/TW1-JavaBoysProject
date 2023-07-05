package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.EncontrarMascotaExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPago {

    @RequestMapping(path = "/pago/preferencia",method = RequestMethod.POST)
    public ResponseEntity<String> getComentariosDeCuidado() {

        // este lo genere previamente con la api de crear preferencia de mercado pago
        String  idPreferencia =  "221225807-2fb3adef-86e1-4724-aa95-fe343b3dd1ff";
        return new ResponseEntity<>(idPreferencia, HttpStatus.OK);
    }
}
