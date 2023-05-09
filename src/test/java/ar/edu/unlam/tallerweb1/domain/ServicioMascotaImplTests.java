package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioTipoMascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMascotaImplTests {
    private RepositorioMascota repositorio;
    private ServicioMascota servicioMascota;
    private List<Mascota> mascotasList;

    @Before
    public void Init(){
        this.repositorio = mock(RepositorioMascota.class);
        this.mascotasList = new ArrayList<Mascota>();
        this.mascotasList.add(new Mascota());
        long idTipoMascota = 1;
        when(this.repositorio.BuscarMascotasPorTipo(idTipoMascota)).thenReturn(mascotasList);
        this.servicioMascota = new ServicioMascotaImpl(this.repositorio);

    }
    @Test
    public void ObtenerMascotaPorTipoEncontrado(){

        long idTipoMascota = 1;
        List<Mascota> resultList = this.servicioMascota.ObtenerMascotasPorTipo(idTipoMascota);
        assertThat(resultList.size()).isEqualTo(1);

    }
    @Test
    public void ObtenerMascotaPorTipoNoEncontrado(){
        long idTipoMascota = 2;
        List<Mascota> resultList = this.servicioMascota.ObtenerMascotasPorTipo(idTipoMascota);
        assertThat(resultList.size()).isEqualTo(0);
    }

}
