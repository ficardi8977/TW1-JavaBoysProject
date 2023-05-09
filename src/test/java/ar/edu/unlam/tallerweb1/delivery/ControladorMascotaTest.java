package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ControladorMascotaTest {

    private ControladorMascotas controladorMascotas;
    private ServicioMascota servicioMascota;

    @Before
    public void init()
    {
        this.servicioMascota = mock(ServicioMascotaImpl.class);
        controladorMascotas = new ControladorMascotas(this.servicioMascota);
    }
    @Test
    public void getMascotaPorTipo_Ok()
    {
        Integer tipo = 1;
        this.dadoQueExisteMascotas(tipo);
        ModelAndView result = this.controladorMascotas.getMascotaPorTipo(tipo);
        assertThat(result.getModel().values().size()).isEqualTo(1);
    }
    @Test
    public void getMascotaPorTipo_NotOk()
    {
        Integer tipo = 2;
        this.dadoQueNoExisteMascotas(tipo);
        ModelAndView result = this.controladorMascotas.getMascotaPorTipo(tipo);
        //revisar como comparar con un not ok de mascotas por tipo.
        //assertThat(result.getModel().values().size()).isEqualTo(0);
    }
    private void dadoQueExisteMascotas(Integer tipo) {
        List<Mascota> listaDeMascotas = new ArrayList<Mascota>();
        listaDeMascotas.add(new Mascota());
        when(this.servicioMascota.ObtenerMascotasPorTipo(tipo)).thenReturn(listaDeMascotas);
    }
    private void dadoQueNoExisteMascotas(Integer tipo) {
        List<Mascota> listaDeMascotas = new ArrayList<Mascota>();
        when(this.servicioMascota.ObtenerMascotasPorTipo(tipo)).thenReturn(listaDeMascotas);
    }
}
