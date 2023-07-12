package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


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
        return new ModelAndView("registrar-usuario-mdq",model);
    }

    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosRegistracion") DatosRegistracion datosRegistracion, @RequestParam("img") MultipartFile img, RedirectAttributes redirectAttributes) {

        try {
            if(!img.isEmpty()){
                datosRegistracion.setImagen(this.servicioRegistracion.registrarImagen(img));
            }
            this.servicioRegistracion.registroUsuario(datosRegistracion);
            redirectAttributes.addFlashAttribute("error", "Usuario registrado");
        } catch (Exception e){
            ModelMap model = new ModelMap();
            model.put("error", e.getMessage());
            return new ModelAndView("registrar-usuario", model);
        }

        return new ModelAndView(new RedirectView("/login"));
    }

}
