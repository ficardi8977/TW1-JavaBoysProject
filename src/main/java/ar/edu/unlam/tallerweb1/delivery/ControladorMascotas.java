package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorMascotas {

    private ServicioMascota servicioMascota;
    @Autowired
    public ControladorMascotas(ServicioMascota servicioMascota) {

        this.servicioMascota = servicioMascota;
    }
    @RequestMapping(path = "/mascotas", method = RequestMethod.GET)
    public ModelAndView getTodasLasMascotas() {
        ModelMap model = new ModelMap();
        List<Mascota> result = this.servicioMascota.ObtenerTodasLasMascotas();
        model.put("mascotas", result);
        return new ModelAndView("todas-las-mascotas",model);
    }

    @RequestMapping(path = "/mascotas/tipoMascota", method = RequestMethod.GET)
    public ModelAndView getMascotaPorTipo(Integer idTipoMascota) {
        ModelMap model = new ModelMap();
        int request = 1;
        List<Mascota> result = this.servicioMascota.ObtenerMascotasPorTipo(request);
        model.put("mascotas", result);
        // mapeada con la home pero faltaria definir con cual es
        return new ModelAndView("redirect:/home",model);
    }

}
