package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentarioImpl;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class ControladorComentariosTest{
    private ServicioComentario servicioComentario;
    private ControladorComentarios controladorComentarios;
    @Before
    public void init() {
        this.servicioComentario = mock(ServicioComentarioImpl.class);
        this.controladorComentarios = new ControladorComentarios(this.servicioComentario);
    }
    @Test
    public void guardarComentario(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(1L);
        this.dadoQueExisteCuidadoyUsuario();
        var result = controladorComentarios.getComentariosDeCuidador(request);
        assertThat(result.getViewName()).isEqualTo("redirect:../cuidador/detalle?id=" + Long.toString(request.getIdCuidado()));
        verify(servicioComentario, atLeastOnce()).guardar(request);

    }
    @Test
    public void guardarComentarioExcepcionUsuario(){
        DatosComentario request = new DatosComentario();
        this.dadoQueNoExisteUsuario();
        request.setIdCuidado(1L);
        var result = controladorComentarios.getComentariosDeCuidador(request);
        assertThat(result.getViewName()).isEqualTo("error");
        verify(servicioComentario, atLeastOnce()).guardar(request);
    }
    @Test
    public void guardarComentarioExcepcionCuidado(){
        DatosComentario request = new DatosComentario();
        this.dadoQueNoExisteCuidado();
        request.setIdCuidado(1l);
        var result = controladorComentarios.getComentariosDeCuidador(request);
        assertThat(result.getViewName()).isEqualTo("error");
        verify(servicioComentario, atLeastOnce()).guardar(request);
    }


    private void dadoQueExisteCuidadoyUsuario() {
        when(this.servicioComentario.guardar(any())).thenReturn(1);
    }
    private void dadoQueNoExisteCuidado() {
        when(this.servicioComentario.guardar(any())).thenThrow(new CuidadoNoExistenteExcepcion());
    }

    private void dadoQueNoExisteUsuario() {
        when(this.servicioComentario.guardar(any())).thenThrow(new UsuarioNoExistenteExcepcion());
    }
}
