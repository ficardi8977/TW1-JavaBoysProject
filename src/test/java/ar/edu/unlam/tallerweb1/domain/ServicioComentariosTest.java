package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentarioImpl;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
        request.setIdUsuario(1);
        this.dadoQueExisteCuidadoyUsuario();

        var result = servicioComentario.guardar(request);

        verify(repositorioComentario, atLeastOnce()).guardar(any(Comentario.class));
        verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
        verify(servicioUsuario, atLeastOnce()).consultarUsuario(anyInt());
        assertThat(result).isNotNull();
    }
    @Test
    public void guardarComentarioExcepcionUsuario(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(1);
        request.setIdUsuario(2);
        this.dadoQueNoExisteUsuario();

        try {
            var result = servicioComentario.guardar(request);
        }catch (UsuarioNoExistenteExcepcion e)
        {
            assertThat(e.getMessage()).isEqualTo("Usuario ingresado inexistente");
        }finally {
            verify(repositorioComentario, never()).guardar(any(Comentario.class));
            verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
            verify(servicioUsuario, atLeastOnce()).consultarUsuario(anyInt());
        }


    }
    @Test
    public void guardarComentarioExcepcionCuidado(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(2);
        request.setIdUsuario(1);
        this.dadoQueNoExisteCuidado();
        try {
            var result = this.servicioComentario.guardar(request);
        }catch(CuidadoNoExistenteExcepcion e)
        {
            assertThat(e.getMessage()).isEqualTo("Cuidado ingresado inexistente");
        }finally {
            verify(this.repositorioComentario, never()).guardar(any(Comentario.class));
            verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
            verify(servicioUsuario, never()).consultarUsuario(anyInt());
        }
    }


    private void dadoQueExisteCuidadoyUsuario() {
        when(this.servicioCuidado.ObtenerDetalle(1)).thenReturn(new Cuidado());
        when(this.servicioUsuario.consultarUsuario(1)).thenReturn(new Usuario());
    }
    private void dadoQueNoExisteCuidado() {
        when(this.servicioCuidado.ObtenerDetalle(anyLong())).thenReturn(null);
        when(this.servicioUsuario.consultarUsuario(1)).thenReturn(new Usuario());
    }

    private void dadoQueNoExisteUsuario() {
        when(this.servicioCuidado.ObtenerDetalle(1)).thenReturn(new Cuidado());
        when(this.servicioUsuario.consultarUsuario(anyInt())).thenReturn(null);
    }

}
