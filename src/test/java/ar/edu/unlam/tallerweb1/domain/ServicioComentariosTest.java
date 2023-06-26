package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.ControladorComentarios;
import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentarioImpl;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidado;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ServicioComentariosTest {
    private ServicioCuidado servicioCuidado;
    private ServicioUsuario servicioUsuario;
    private RepositorioComentario repositorioComentario;
    private ServicioComentario servicioComentario;


    @Before
    public void init() {
        this.servicioCuidado = mock(ServicioCuidado.class);
        this.servicioUsuario = mock(ServicioUsuario.class);
        this.repositorioComentario = mock(RepositorioComentario.class);

        this.servicioComentario = new ServicioComentarioImpl(this.repositorioComentario,this.servicioCuidado, this.servicioUsuario);

    }
    @Test
    public void guardarComentario(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(1);
        this.dadoQueExisteCuidadoyUsuario();

        var result = servicioComentario.guardar(request);

        verify(servicioComentario, atLeastOnce()).guardar(request);

    }
    @Test
    public void guardarComentarioExcepcionUsuario(){
    }
    @Test
    public void guardarComentarioExcepcionCuidado(){
    }


    private void dadoQueExisteCuidadoyUsuario() {
        when(this.servicioCuidado.ObtenerDetalle(any())).thenReturn(new Cuidado());
        //when(this.servicioUsuario.consultarUsuario(any())).thenReturn(new Cuidado());
    }
    private void dadoQueNoExisteCuidado() {
        when(this.servicioCuidado.ObtenerDetalle(any())).thenReturn(null);
    }

    private void dadoQueNoExisteUsuario() {
        when(this.servicioUsuario.consultarUsuario(any())).thenReturn(null);
    }

}
