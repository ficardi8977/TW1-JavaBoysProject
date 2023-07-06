package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentarioImpl;
import ar.edu.unlam.tallerweb1.domain.excepciones.ComentarioInexistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.PermisosExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

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

    @Test
    public void BorradoComentario_OK(){

        var idComentario = 1;
        var idUsuario = 2;
        var result = this.controladorComentarios.eliminar(idComentario, idUsuario);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void BorradoComentario_NoExisteUsuarioExcepcion(){
        long idComentario = 1;
        long idUsuario = 1;
        this.dadoQueNoExisteUsuario(idUsuario);

        var result = this.controladorComentarios.eliminar(idComentario, idUsuario);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isEqualTo("Usuario ingresado inexistente");
    }
    @Test
    public void BorradoComentario_ComentarioInexistenteExcepcion(){
        long idComentario = 1;
        long idUsuario = 1;

        this.dadoQueNoExisteComentario(idComentario);

        var result = this.controladorComentarios.eliminar(idComentario, idUsuario);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isEqualTo("Comentario inexistente");

    }
    @Test
    public void BorradoComentario_PermisosExcepcion(){
        long idComentario = 1;
        long idUsuario = 1;

        this.dadoQueUsuarioNoTienePermiso(idUsuario);

        var result = this.controladorComentarios.eliminar(idComentario, idUsuario);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(result.getBody()).isEqualTo("No tienes permisos necesarios para realizar esta operación");
    }
    @Test
    public void BorradoComentario_ExcepcionInternalServer(){
        long idComentario = 1;
        long idUsuario = 1;
        this.dadoQueFallaServicio();

        var result = this.controladorComentarios.eliminar(idComentario,idUsuario);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getBody()).isEqualTo("Error al procesar la baja");
    }

    private void dadoQueNoExisteComentario(long idComentarioQueNoExiste) {
        Mockito.doThrow(new ComentarioInexistenteExcepcion()).when(servicioComentario).eliminar(Mockito.eq(idComentarioQueNoExiste),Mockito.anyLong());

            }
    private void dadoQueNoExisteUsuario(long idUsuarioQueNoExiste) {
        Mockito.doThrow(new UsuarioNoExistenteExcepcion()).when(servicioComentario).eliminar(Mockito.anyLong(), Mockito.eq(idUsuarioQueNoExiste));
    }
    private void dadoQueUsuarioNoTienePermiso(long idUsuarioNoAdministrador) {
        Mockito.doThrow(new PermisosExcepcion()).when(servicioComentario).eliminar(Mockito.anyLong(), Mockito.eq(idUsuarioNoAdministrador));
    }
    private void dadoQueFallaServicio() {
        doAnswer(invocation -> {throw new Exception("Error al eliminar");}).when(this.servicioComentario).eliminar(anyLong(), anyLong());
    }

}
