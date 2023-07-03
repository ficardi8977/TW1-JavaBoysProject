package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mercadoPago.ServicioMercadoPago;


import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMercadoPago {

    private ServicioMercadoPago servicioMercadoPago;

    @RequestMapping(path = "/crear-pedido",method = RequestMethod.GET)
    public ModelAndView crearPreferencia() throws MPException, MPApiException {
        ModelMap model = new ModelMap();

        this.servicioMercadoPago.crearPedido();

        return new ModelAndView("/detalle-cuidador");
    }
}
