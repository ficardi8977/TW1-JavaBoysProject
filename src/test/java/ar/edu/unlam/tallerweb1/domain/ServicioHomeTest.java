package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DTOHome;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.home.ServicioHome;
import ar.edu.unlam.tallerweb1.domain.home.ServicioHomeImpl;
import ar.edu.unlam.tallerweb1.domain.mascotas.EstadoMascotasEnum;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidado;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioHomeTest {
    private RepositorioMascota repositorioMascota;
    private RepositorioCuidado repositorioCuidado;
    private ServicioHome servicioHome;

    @Before
    public void Init()
    {
        this.repositorioCuidado = mock(RepositorioCuidado.class);
        this.repositorioMascota = mock(RepositorioMascota.class);
        this.servicioHome = new ServicioHomeImpl(this.repositorioMascota,this.repositorioCuidado);
    }
    @Test
    public void ObtenerTodosLosCarruseles()
    {
        this.dadoExistenTodosLosCarruseles();
        var result = this.servicioHome.ListCarruseles();
        this.validarExistenTodosLosCarruseles(result);
    }

    private void validarExistenTodosLosCarruseles(DTOHome result) {
        Assertions.assertThat(result.getCuidadores()).isNotNull();
        Assertions.assertThat(result.getPerdidos()).isNotNull();
        Assertions.assertThat(result.getRefugios()).isNotNull();
    }

    private void dadoExistenTodosLosCarruseles() {
        var filtrosPerdidas = new DatosMascotasFiltradas();
        filtrosPerdidas.setIdEstado(EstadoMascotasEnum.Perdido.ordinal());
        when(this.repositorioMascota.ObtenerMascotasFiltradas(filtrosPerdidas)).thenReturn(new ArrayList<Mascota>());
        when(this.repositorioCuidado.TodosLosRefugios()).thenReturn(new ArrayList<Cuidado>());
        when(this.repositorioCuidado.obtenerTodosLosCuidadores()).thenReturn(new ArrayList<Cuidado>());
    }

    @Test
    public void ObtenerCarruselesSinMascotas()
    {
        this.dadoExistenTodosLosCarruselesSinMascotas();
        var result  = this.servicioHome.ListCarruseles();
        this.validarExistenTodosLosCarruselesSinMascotas(result);
    }

    private void validarExistenTodosLosCarruselesSinMascotas(DTOHome result) {
        Assertions.assertThat(result.getCuidadores()).isNotNull();
        Assertions.assertThat(result.getPerdidos()).isNull();
        Assertions.assertThat(result.getRefugios()).isNotNull();
    }

    private void dadoExistenTodosLosCarruselesSinMascotas() {
        when(this.repositorioMascota.ObtenerMascotasFiltradas(any())).thenReturn(null);
        when(this.repositorioCuidado.TodosLosRefugios()).thenReturn(new ArrayList<Cuidado>());
        when(this.repositorioCuidado.obtenerTodosLosCuidadores()).thenReturn(new ArrayList<Cuidado>());

    }

    @Test
    public void ObtenerCarruselesSinRefugios()
    {
        this.dadoExistenTodosLosCarruselesSinRefugios();
        var result = this.servicioHome.ListCarruseles();
        this.validarExistenTodosLosCarruselesSinRefugios(result);
    }

    private void validarExistenTodosLosCarruselesSinRefugios(DTOHome result) {
        Assertions.assertThat(result.getCuidadores()).isNotNull();
        Assertions.assertThat(result.getPerdidos()).isNotNull();
        Assertions.assertThat(result.getRefugios()).isNull();
    }

    private void dadoExistenTodosLosCarruselesSinRefugios() {
        var filtrosPerdidas = new DatosMascotasFiltradas();
        filtrosPerdidas.setIdEstado(EstadoMascotasEnum.Perdido.ordinal());
        when(this.repositorioMascota.ObtenerMascotasFiltradas(filtrosPerdidas)).thenReturn(new ArrayList<Mascota>());
        when(this.repositorioCuidado.TodosLosRefugios()).thenReturn(null);
        when(this.repositorioCuidado.obtenerTodosLosCuidadores()).thenReturn(new ArrayList<Cuidado>());

    }

    @Test
    public void ObtenerCarruselesSinCuidadores()
    {
        this.dadoExistenTodosLosCarruselesSinCuidador();
        var result = this.servicioHome.ListCarruseles();
        this.validarExistenTodosLosCarruselesSinCuidador(result);
    }

    private void validarExistenTodosLosCarruselesSinCuidador(DTOHome result) {
        Assertions.assertThat(result.getCuidadores()).isNull();
        Assertions.assertThat(result.getPerdidos()).isNotNull();
        Assertions.assertThat(result.getRefugios()).isNotNull();
    }

    private void dadoExistenTodosLosCarruselesSinCuidador() {
        var filtrosPerdidas = new DatosMascotasFiltradas();
        filtrosPerdidas.setIdEstado(EstadoMascotasEnum.Perdido.ordinal());
        when(this.repositorioMascota.ObtenerMascotasFiltradas(filtrosPerdidas)).thenReturn(new ArrayList<Mascota>());
        when(this.repositorioCuidado.TodosLosRefugios()).thenReturn(new ArrayList<Cuidado>());
        when(this.repositorioCuidado.obtenerTodosLosCuidadores()).thenReturn(null);

    }
}
