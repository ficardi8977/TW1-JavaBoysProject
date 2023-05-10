package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
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
        mascotas.add(mascota);
        Long idUsuario = 1l;
        when(this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuario)).thenReturn(mascotas);
    }

    @Test
    public void buscarMascotasPorIdUsuario(){
        Long idUsuario = 1l;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario);
        assertThat(mascotas.size()).isEqualTo(1);
    }

    @Test
    public void NoBuscarMascotasPorIdUsuario(){
        Long idUsuario = 2l;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario);
        assertThat(mascotas.size()).isEqualTo(0);
    }



}
