package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
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
public class ControladorCuidado {

    private ServicioCuidado servicioCuidado;

    @Autowired
    public ControladorCuidado(ServicioCuidado servicioCuidado){
        this.servicioCuidado = servicioCuidado;
    }

    @RequestMapping(path = "/refugios",method = RequestMethod.GET)
    public ModelAndView getTodosLosRefugios() {
        ModelMap model = new ModelMap();
        List<Cuidado> result = this.servicioCuidado.ObtenerTodosLosRefugios();
        model.put("cuidados", result);
        return new ModelAndView("todos-los-refugios-mdq",model);
    }
    @RequestMapping(path = "/refugio/{id}", method = RequestMethod.GET)
    public ModelAndView getDetalleRefugio(@PathVariable("id") long id) {
        ModelMap model = new ModelMap();
        Cuidado result = this.servicioCuidado.ObtenerDetalleRefugio(id);
        model.put("refugio", result);
        return new ModelAndView("refugios-detalle", model);
    }
}
