package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorMascotaTest {
    private ControladorMascota controladorMascota;
    private ServicioMascota servicioMascota;

    @Before
    public void init(){
        this.servicioMascota = mock(ServicioMascota.class);
        this.controladorMascota = new ControladorMascota(this.servicioMascota);
    }

    @Test
    public void buscarMascotaPorIdUsuario(){
        Long idUsuario = 1l;
        dadoQueExisteMascota(idUsuario);
        ModelAndView mav = this.controladorMascota.getMascotaPorIdUsuario(idUsuario);
        assertThat(mav.getModel().get("mascotas")).isNotNull();
    }

    @Test
    public void buscarMascotaPorIdUsuarioIncorrecto(){
        Long idUsuario = 1l;
        Long idUsuarioIncorrecto = 2l;
        dadoQueExisteMascota(idUsuario);
        ModelAndView mav = this.controladorMascota.getMascotaPorIdUsuario(idUsuarioIncorrecto);
        assertThat(mav.getModel().get("mascotas").toString()).isEqualTo("[]");
    }

    private void dadoQueExisteMascota(Long idUsuario) {
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota();
        mascota.setIdUsuario(idUsuario);
        mascotas.add(mascota);
        when(this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario)).thenReturn(mascotas);
    }


}
