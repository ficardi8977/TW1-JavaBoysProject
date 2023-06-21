package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascotaImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioMascotaTest {

    private ServicioMascota servicioMascota;
    private RepositorioMascota repositorioMascota;

    private List<Mascota> mascotas;


    @Before
    public void init(){
        this.repositorioMascota = mock(RepositorioMascotaImpl.class);
        this.servicioMascota = new ServicioMascotaImpl(this.repositorioMascota);
        this.mascotas = new ArrayList<>();
        Mascota mascota = new Mascota();
        Mascota mascota2 = new Mascota();
        Vacunacion vacuna = new Vacunacion();
        mascota.setVacunas(vacuna);
        mascotas.add(mascota);
        mascotas.add(mascota2);
        int idUsuario = 1;
        when(this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuario)).thenReturn(mascotas);

    }

    @Test
    public void buscarMascotasPorIdUsuario(){
        int idUsuario = 1;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario);
        assertThat(mascotas.size()).isEqualTo(2);
    }

    @Test
    public void NoBuscarMascotasPorIdUsuario(){
        int idUsuario = 2;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario);
        assertThat(mascotas.size()).isEqualTo(0);
    }

    @Test
    public void queMeTraigaLasVacunas(){
        int idUsuario = 1;
        List <Vacunacion> vacunas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario).get(0).getVacunas();
        assertThat(vacunas).isNotNull();
        assertThat(vacunas.size()).isEqualTo(1);
    }

    @Test
    public void queNoMeTraigaLasVacunas(){
        int idUsuario = 1;
        List <Vacunacion> vacunas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario).get(1).getVacunas();
        assertThat(vacunas.size()).isEqualTo(0);
    }









}
