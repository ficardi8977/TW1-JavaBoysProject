package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidador;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidadorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorCuidador {

    private ServicioCuidador servicioCuidador;

    @Autowired
    public ControladorCuidador(ServicioCuidador servicioCuidador){
        this.servicioCuidador = servicioCuidador;
    }

    @RequestMapping(path = "/cuidadores", method = RequestMethod.GET)
    public ModelAndView getTodosLosCuidadores(){
        ModelMap model = new ModelMap();
        List<Cuidado> cuidadores = this.servicioCuidador.ObtenerTodosLosCuidadores();
        model.put("cuidadores", cuidadores);
        return new ModelAndView("todos-los-cuidadores",model);
    }


}
