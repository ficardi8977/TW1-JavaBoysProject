package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstadoImpl;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioEstado;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioEstadoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioEstadoTest {
    ServicioEstado servicioEstado;
    RepositorioEstado repositorioEstado;

    @Before
    public void Init() {
        this.repositorioEstado = mock(RepositorioEstadoImpl.class);
        this.servicioEstado = new ServicioEstadoImpl(this.repositorioEstado);
    }

    @Test
    public void ListarTodos(){
        //preparacion
        this.dadoQueExisteTiposDeMascota(1);
        // ejecucion
        var result = this.servicioEstado.listar();
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }
    @Test
    public void NoExistenTipoMascota(){
        //preparacion
        this.dadoQueExisteNoTiposDeMascota();
        // ejecucion
        var result = this.servicioEstado.listar();
        // verificacion
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    private void dadoQueExisteTiposDeMascota(long id)
    {
        var lista = new ArrayList<Estado>();
        var estadoMascota = new Estado();
        estadoMascota.setId(id);
        lista.add(estadoMascota);
        when(this.repositorioEstado.list()).thenReturn(lista);
    }
    private void dadoQueExisteNoTiposDeMascota()
    {
        when(this.repositorioEstado.list()).thenReturn(new ArrayList<Estado>());
    }
}
