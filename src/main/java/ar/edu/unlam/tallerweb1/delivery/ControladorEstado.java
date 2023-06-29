package ar.edu.unlam.tallerweb1.delivery;


import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControladorEstado {

    private ServicioEstado servicioEstado;

    @Autowired
    public ControladorEstado(ServicioEstado servicioEstado) {

        this.servicioEstado = servicioEstado;
    }

    @RequestMapping(path = "/estadoMascota")
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> result = this.servicioEstado.listar();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
