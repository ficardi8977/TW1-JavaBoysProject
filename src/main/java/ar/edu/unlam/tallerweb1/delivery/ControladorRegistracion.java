package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("registro-usuario",model);
    }

    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute DatosRegistracion datosRegistracion) {
        ModelMap model = new ModelMap();
        String viewName = "";

        if(this.servicioRegistracion.esValido(datosRegistracion.getCorreo()) && this.servicioRegistracion.registrarUsuario(datosRegistracion.getCorreo(), datosRegistracion.getClave())){
            model.put("msg", "Registro exitoso");
            model.put("datosLogin", new DatosLogin(datosRegistracion.getCorreo()));
            viewName = "login";
        }else{
            model.put("msg", "Registro fallido");
            viewName = "registro-usuario";
        }

        return new ModelAndView(viewName, model);
    }

}
