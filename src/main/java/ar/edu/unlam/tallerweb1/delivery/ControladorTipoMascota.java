package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.ServicioTipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView Listar() {

        ModelMap model = new ModelMap();
        List<TipoMascota> result = this.servicioTipoMascota.Listar();

        model.put("tiposMascota", result);
        return new ModelAndView("todas-las-mascotas",model);

    }
}
