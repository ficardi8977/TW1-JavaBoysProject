package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistracion {
    private ServicioRegistracion servicioRegistracion;

    @Autowired
    public ControladorRegistracion(ServicioRegistracion servicioRegistracion){
        this.servicioRegistracion = servicioRegistracion;
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.GET)
    public ModelAndView registrarme() {
        ModelMap model = new ModelMap();
        model.put("datosRegistracion", new DatosRegistracion());
        return new ModelAndView("registrar-usuario",model);
    }

    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosRegistracion") DatosRegistracion datosRegistracion) {
        ModelMap model = new ModelMap();
        String viewName = "";

        if(this.servicioRegistracion.datosValidos(datosRegistracion.getEmail(), datosRegistracion.getPassword()) && this.servicioRegistracion.registroUsuario(datosRegistracion)){
            model.put("error", "Registro exitoso");
            model.put("datosLogin", new DatosLogin(datosRegistracion.getEmail()));
            viewName = "login";
        }else{
            model.put("error", "Registro fallido");
            viewName = "registrar-usuario";
        }

        return new ModelAndView(viewName, model);
    }

}
