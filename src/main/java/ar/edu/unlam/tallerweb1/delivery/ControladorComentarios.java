package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorComentarios {

    private ServicioComentario servicioComentarios;

    @Autowired
    public ControladorComentarios(ServicioComentario servicioComentarios) {
        this.servicioComentarios = servicioComentarios;
    }

    @RequestMapping(path = "/comentario/cuidador", method = RequestMethod.POST)
    public ModelAndView getComentariosDeCuidador(@ModelAttribute("datosComentario") DatosComentario datosComentario) {
        try {
            this.servicioComentarios.guardar(datosComentario);
            return new ModelAndView("redirect:../cuidador/detalle?id=" + Integer.toString(datosComentario.getIdCuidado()));
        } catch (UsuarioNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error",model);
        } catch (CuidadoNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error",model);
        }
    }

    @RequestMapping(path = "/comentario/refugio", method = RequestMethod.POST)
    public ModelAndView getComentariosDeRefugio(@ModelAttribute("datosComentario") DatosComentario datosComentario) {
        try {
            this.servicioComentarios.guardar(datosComentario);
            return new ModelAndView("redirect:../refugio/detalle?id=" + Integer.toString(datosComentario.getIdCuidado()));
        } catch (UsuarioNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error",model);
        } catch (CuidadoNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error",model);
        }
    }
}

