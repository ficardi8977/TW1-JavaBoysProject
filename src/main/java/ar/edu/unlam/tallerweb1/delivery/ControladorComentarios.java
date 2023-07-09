package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.ComentarioInexistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.PermisosExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.EncontrarMascotaExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
            return new ModelAndView("redirect:../cuidador/detalle?id=" + Long.toString(datosComentario.getIdCuidado()));
        } catch (UsuarioNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error", model);
        } catch (CuidadoNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error", model);
        }
    }

    @RequestMapping(path = "/comentario/mascota", method = RequestMethod.POST)
    public ModelAndView postComentariosMascotas(@ModelAttribute("datosComentario") DatosComentario datosComentario) {
        try {
            this.servicioComentarios.guardarMascotas(datosComentario);
            return new ModelAndView("redirect:../mascota/detalle?id=" + Long.toString(datosComentario.getIdMascota()));
        } catch (EncontrarMascotaExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error", model);
        }
    }

    @RequestMapping(path = "/comentario/refugio", method = RequestMethod.POST)
    public ModelAndView getComentariosDeRefugio(@ModelAttribute("datosComentario") DatosComentario datosComentario) {
        try {
            this.servicioComentarios.guardar(datosComentario);
            return new ModelAndView("redirect:../refugio/" + Long.toString(datosComentario.getIdCuidado()));
        } catch (UsuarioNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error", model);
        } catch (CuidadoNoExistenteExcepcion e) {
            ModelMap model = new ModelMap();
            model.put("msg", e.getMessage());
            return new ModelAndView("error", model);
        }
    }
    @RequestMapping(path = "/comentario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminar(@PathVariable("id") long id, @RequestParam("idUsuario") long idUsuario) {
        try {
            this.servicioComentarios.eliminar(id, idUsuario);
            return new ResponseEntity<String>("se realizó la baja correctamente", HttpStatus.OK);
        } catch (UsuarioNoExistenteExcepcion | ComentarioInexistenteExcepcion ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PermisosExcepcion ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Error al procesar la baja", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(path = "/comentarios/cuidado/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DTOComentario>> getComentariosDeCuidador(@PathVariable("id") long id) {
        try {
            var response = this.servicioComentarios.obtenerPorIdCuidado(id);
            return new ResponseEntity<List<DTOComentario>>(response, HttpStatus.OK);
        } catch (CuidadoNoExistenteExcepcion ex) {
            return new ResponseEntity<List<DTOComentario>>(new ArrayList<DTOComentario>(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<DTOComentario>>(new ArrayList<DTOComentario>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

