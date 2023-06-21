package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
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
    public ControladorComentarios(ServicioComentario servicioComentarios){
        this.servicioComentarios = servicioComentarios;
    }

    @RequestMapping(path = "/comentario/cuidador",method = RequestMethod.POST)
    public ModelAndView getComentariosDeCuidado(@ModelAttribute("datosComentario") DatosComentario datosComentario) {
        var idComentario  =  this.servicioComentarios.guardar(datosComentario);
        return new ModelAndView("redirect:../cuidador/detalle?id="+ Integer.toString(datosComentario.getIdCuidado()));
    }
}
