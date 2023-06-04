package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.ServicioTipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.ServicioTipoMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioTipoMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioTipoMascotaImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ServicioTipoMascotaImplTest{

    ServicioTipoMascota servicioTipoMascota;
    RepositorioTipoMascota repositorioTipoMascota;

    @Before
    public void Init() {
        this.repositorioTipoMascota = mock(RepositorioTipoMascotaImpl.class);
        this.servicioTipoMascota = new ServicioTipoMascotaImpl(this.repositorioTipoMascota);
    }

    @Test
    public void ListarTodos(){
        //preparacion
        this.dadoQueExisteTiposDeMascota(1);
        // ejecucion
        var result = this.servicioTipoMascota.Listar();
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }
    @Test
    public void NoExistenTipoMascota(){
        //preparacion
        this.dadoQueExisteNoTiposDeMascota();
        // ejecucion
        var result = this.servicioTipoMascota.Listar();
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    private void dadoQueExisteTiposDeMascota(long id)
    {
        var lista = new ArrayList<TipoMascota>();
        var tipoMascota = new TipoMascota();
        tipoMascota.setId(id);
        lista.add(tipoMascota);
        when(this.repositorioTipoMascota.List()).thenReturn(lista);
    }
    private void dadoQueExisteNoTiposDeMascota()
    {
        when(this.repositorioTipoMascota.List()).thenReturn(new ArrayList<TipoMascota>());
    }
}
