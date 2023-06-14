package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.home.ServicioHome;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {
    private ServicioHome servicioHome;
    @Autowired
    public ControladorHome(ServicioHome servicioHome){
        this.servicioHome = servicioHome;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        ModelMap model = new ModelMap();
        var carruseles = this.servicioHome.ListCarruseles();
        model.put("carruseles", carruseles);
        return new ModelAndView("home",model);
    }
}
