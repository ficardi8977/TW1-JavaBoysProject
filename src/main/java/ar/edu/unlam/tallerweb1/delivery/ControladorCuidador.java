package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidadoImpl;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorCuidador {

    private ServicioCuidado servicioCuidador;

    @Autowired
    public ControladorCuidador(ServicioCuidado servicioCuidador) {
        this.servicioCuidador = servicioCuidador;
    }

    @RequestMapping(path = "/cuidadores", method = RequestMethod.GET)
    public ModelAndView getTodosLosCuidadores() {
        ModelMap model = new ModelMap();
        List<Cuidado> cuidadores = this.servicioCuidador.ObtenerTodosLosCuidadores();
        model.put("cuidadores", cuidadores);
        return new ModelAndView("todos-los-cuidadores-mdq", model);
    }

    @RequestMapping(path = "/cuidador/detalle", method = RequestMethod.GET)
    public ModelAndView getDetalle(long id) {
        ModelMap model = new ModelMap();
        Cuidado result = this.servicioCuidador.ObtenerDetalle(id);
        model.put("cuidado", result);
        return new ModelAndView("detalle-cuidador", model);
    }


}
