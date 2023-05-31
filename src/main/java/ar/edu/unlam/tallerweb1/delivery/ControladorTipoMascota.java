package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.ServicioTipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorTipoMascota {

    private ServicioTipoMascota servicioTipoMascota;

    @Autowired
    public ControladorTipoMascota(ServicioTipoMascota servicioTipoMascota) {

        this.servicioTipoMascota = servicioTipoMascota;
    }

    @RequestMapping(path = "/tipoMascota")
    public ResponseEntity<List<TipoMascota>> Listar() {
        List<TipoMascota> result = this.servicioTipoMascota.Listar();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
