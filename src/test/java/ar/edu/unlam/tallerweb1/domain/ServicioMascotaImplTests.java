package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
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
        when(this.repositorio.TodasLasMascotas()).thenReturn(mascotasList);
        when(this.repositorio.BuscarDetalle(1)).thenReturn(mascotasList.get(0));
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

    @Test
    public void ObtenerTodasLasMascota(){
        List<Mascota> resultList = this.servicioMascota.ObtenerTodasLasMascotas();
        assertThat(resultList.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void ObtenerMascotaDetalleEncontrado(){

        long id = 1;
        Mascota result  = this.servicioMascota.ObtenerDetalle(id);
        assertThat(result).isNotNull();

    }
    @Test
    public void ObtenerMascotaDetalleNoEncontrado(){
        long id = 2;
        Mascota result = this.servicioMascota.ObtenerDetalle(id);
        assertThat(result).isNull();
    }

    @Test
    public void ListarMascotasFiltradas(){
        //preparacion
        var request = new DatosMascotasFiltradas();
        this.dadoQueExistemascotasFiltradas(request);
        // ejecucion
        var result = this.servicioMascota.ObtenerMascotasFiltradas(request);
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }
    @Test
    public void NoExisteMascotaFiltrada(){
        //preparacion
        var request = new DatosMascotasFiltradas();
        this.dadoQueNoExistemascotasFiltradas(request);
        // ejecucion
        var result = this.servicioMascota.ObtenerMascotasFiltradas(request);
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    private void dadoQueExistemascotasFiltradas(DatosMascotasFiltradas request)
    {
        var lista = new ArrayList<Mascota>();
        var mascota = new Mascota();

        /*long idTipo = 1;
        var tipoMascota = new TipoMascota();
        tipoMascota.setId(idTipo);

        tipoMascota.setId(idTipo);
        var tipoRaza = new TipoRaza();
        tipoRaza.setTipoMascota(tipoMascota);

        mascota.setTipoRaza(tipoRaza);*/

        lista.add(mascota);
        when(this.repositorio.ObtenerMascotasFiltradas(request)).thenReturn(lista);
    }
    private void dadoQueNoExistemascotasFiltradas(DatosMascotasFiltradas request)
    {
        when(this.repositorio.ObtenerMascotasFiltradas(request)).thenReturn(new ArrayList<Mascota>());
    }

}
