package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorEstadoTest {

    private ControladorEstado controladorEstado;
    private ServicioEstado servicioEstado;

    @Before
    public void init(){
        this.servicioEstado = mock(ServicioEstado.class);
        controladorEstado = new ControladorEstado(this.servicioEstado);
    }

    @Test
    public void listar_ok(){
        //Configurar
        int idEstadoMascota = 2;
        this.dadoQueExisteEstadoMascota(idEstadoMascota);

        //Ejecutar
        var result = this.controladorEstado.listar();

        //Verificar
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().size()).isEqualTo(1);
    }

    @Test
    public void listar_SinDatos(){
        //Configurar
        int idEstadoMascota = 9;
        this.dadoQueNoExisteEstadoMascota(idEstadoMascota);

        //Ejecutar
        var result = this.controladorEstado.listar();

        //Verificar
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().size()).isEqualTo(0);
    }

    private void dadoQueExisteEstadoMascota(long idEstadoMascota) {
        List<Estado> listEstadoMascota = new ArrayList<>();
        Estado estadoMascota = new Estado();
        estadoMascota.setId(idEstadoMascota);
        listEstadoMascota.add(estadoMascota);
        when(this.servicioEstado.listar()).thenReturn(listEstadoMascota);
    }

    private void dadoQueNoExisteEstadoMascota(long idEstadoMascota){
        List<Estado> listEstadoMascota = new ArrayList<>();
        when(this.servicioEstado.listar()).thenReturn(listEstadoMascota);
    }
}
