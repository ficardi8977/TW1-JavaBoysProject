package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Controller
public class ControladorRegistracion {
    private ServicioRegistracion servicioRegistracion;

    @Autowired
    public ControladorRegistracion(ServicioRegistracion servicioRegistracion) {
        this.servicioRegistracion = servicioRegistracion;
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.GET)
    public ModelAndView registrarme() {
        ModelMap model = new ModelMap();
        model.put("datosRegistracion", new DatosRegistracion());
        return new ModelAndView("registrar-usuario", model);
    }

    @RequestMapping(value = "/registrar-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("datosRegistracion")
                                                     DatosRegistracion datosRegistracion,
                                         @RequestPart("imagen") MultipartFile imagen)
            throws IOException {
        ModelMap model = new ModelMap();
        String viewName = "";

        if (!imagen.isEmpty()) {
            // Obtener los bytes del archivo
            byte[] imageData = imagen.getBytes();

            // Convertir la imagen a Base64
            String base64Image = Base64.getEncoder().encodeToString(imageData);

            // Asignar el valor de base64Image al objeto DatosRegistracion
            datosRegistracion.setImagenBase64(base64Image);
        }

        if (this.servicioRegistracion.datosValidos(datosRegistracion) && this.servicioRegistracion.registroUsuario(datosRegistracion)) {
            model.put("error", "Registro exitoso");
            model.put("datosLogin", new DatosLogin(datosRegistracion.getEmail()));
            viewName = "login";
        } else {
            model.put("error", "Registro fallido");
            viewName = "registrar-usuario";
        }

        return new ModelAndView(viewName, model);
    }
}
