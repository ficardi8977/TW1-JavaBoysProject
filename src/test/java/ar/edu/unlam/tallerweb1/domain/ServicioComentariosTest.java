package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.ServicioComentarioImpl;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.*;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.tipoUsuario.TipoUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ServicioComentariosTest {
    private ServicioCuidado servicioCuidado;
    private ServicioUsuario servicioUsuario;
    private RepositorioComentario repositorioComentario;
    private ServicioComentario servicioComentario;

    private ServicioMascota servicioMascota;


    @Before
    public void init() {
        this.servicioCuidado = mock(ServicioCuidado.class);
        this.servicioUsuario = mock(ServicioUsuario.class);
        this.repositorioComentario = mock(RepositorioComentario.class);
        this.servicioMascota = mock(ServicioMascota.class);

        this.servicioComentario = new ServicioComentarioImpl(this.repositorioComentario,this.servicioMascota,this.servicioCuidado, this.servicioUsuario);

    }
    @Test
    public void guardarComentario(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(1l);
        request.setIdUsuario(1l);
        this.dadoQueExisteCuidadoyUsuario();

        var result = servicioComentario.guardar(request);

        verify(repositorioComentario, atLeastOnce()).guardar(any(Comentario.class));
        verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
        verify(servicioUsuario, atLeastOnce()).consultarUsuario(anyLong());
        assertThat(result).isNotNull();
    }
    @Test(expected = UsuarioNoExistenteExcepcion.class)
    public void guardarComentarioExcepcionUsuario(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(1l);
        request.setIdUsuario(2l);
        this.dadoQueNoExisteUsuario();

        var result = servicioComentario.guardar(request);

        verify(repositorioComentario, never()).guardar(any(Comentario.class));
        verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
        verify(servicioUsuario, atLeastOnce()).consultarUsuario(anyLong());
    }
    @Test(expected = CuidadoNoExistenteExcepcion.class)
    public void guardarComentarioExcepcionCuidado(){
        DatosComentario request = new DatosComentario();
        request.setIdCuidado(2l);
        request.setIdUsuario(1l);
        this.dadoQueNoExisteCuidado();
        var result = this.servicioComentario.guardar(request);
        verify(this.repositorioComentario, never()).guardar(any(Comentario.class));
        verify(servicioCuidado, atLeastOnce()).ObtenerDetalle(anyLong());
        verify(servicioUsuario, never()).consultarUsuario(anyLong());
    }

    @Test
    public void eliminar_DebeEliminarComentarioCuandoUsuarioEsValido() {
        long id = 1;
        long idUsuario = 2;

        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuarioAdministrador = new TipoUsuario();
        tipoUsuarioAdministrador.setNombre("Administrador");
        usuario.setTipoUsuario(tipoUsuarioAdministrador);

        Comentario comentario = new Comentario();

        when(servicioUsuario.consultarUsuario(idUsuario)).thenReturn(usuario);
        when(repositorioComentario.obtener(id)).thenReturn(comentario);

        this.servicioComentario.eliminar(id, idUsuario);


        verify(repositorioComentario, atLeastOnce()).eliminar(comentario);
    }

    @Test(expected = UsuarioNoExistenteExcepcion.class)
    public void eliminar_DebeLanzarUsuarioNoExistenteExcepcionCuandoUsuarioEsNulo() {
        long id = 1;
        long idUsuario = 2;

        this.dadoUnUsuarioInexistente(idUsuario);

        servicioComentario.eliminar(id, idUsuario);
    }


    @Test
    public void obtenerSubcomentario_OK() {
        long id = 1;

        Comentario comentario = new Comentario();
        comentario.setUsuario(new Usuario());

        var subComentarios = new ArrayList<Comentario>();
        subComentarios.add(comentario);
        subComentarios.add(comentario);
        subComentarios.add(comentario);

        when(repositorioComentario.obtener(id)).thenReturn(comentario);
        when(repositorioComentario.obtenerSubcomentarios(id)).thenReturn(subComentarios);

        var result = this.servicioComentario.obtenerSubcomentarios(id);

        verify(repositorioComentario, atLeastOnce()).obtener(id);
        verify(repositorioComentario, atLeastOnce()).obtenerSubcomentarios(id);
        assertThat(result.size()).isEqualTo(3);
    }

    @Test(expected = ComentarioInexistenteExcepcion.class)
    public void obtenerSubcomentario_NoExisteComentario() {
        long id = 1;

        var result = this.servicioComentario.obtenerSubcomentarios(id);

        verify(repositorioComentario, atLeastOnce()).obtener(id);
        verify(repositorioComentario, never()).obtenerSubcomentarios(id);
        assertThat(result.size()).isEqualTo(0);
    }

    private void dadoUnUsuarioInexistente(long idUsuario) {
        when(servicioUsuario.consultarUsuario(idUsuario)).thenReturn(null);
    }

    @Test(expected = ComentarioInexistenteExcepcion.class)
    public void eliminar_DebeLanzarComentarioInexistenteExcepcionCuandoComentarioEsNulo() {
        long id = 1;
        long idUsuario = 2;

        this.dadoUnUsuarioAdministrador(idUsuario);
        this.dadoUnComentarioInexistente(id);

        this.servicioComentario.eliminar(id, idUsuario);
    }



    private void dadoUnComentarioInexistente(long id) {
        when(repositorioComentario.obtener(id)).thenReturn(null);
    }

    @Test(expected = PermisosExcepcion.class)
    public void eliminar_DebeLanzarPermisosExcepcionCuandoUsuarioNoEsAdministrador() {
        long id = 1;
        long idUsuario = 2;

        this.dadoUnUsuarioMasivo(idUsuario);
        this.dadoUnComentarioExistente(id);

        this.servicioComentario.eliminar(id, idUsuario);
    }

    private void dadoUnComentarioExistente(long id) {
        Comentario comentario = new Comentario();
        when(repositorioComentario.obtener(id)).thenReturn(comentario);
    }

    private void dadoUnUsuarioMasivo(long idUsuario) {
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuarioMasivo = new TipoUsuario();
        tipoUsuarioMasivo.setNombre("Masivo");
        usuario.setTipoUsuario(tipoUsuarioMasivo);
        when(servicioUsuario.consultarUsuario(idUsuario)).thenReturn(usuario);
    }

    private void dadoQueExisteCuidadoyUsuario() {
        when(this.servicioCuidado.ObtenerDetalle(1)).thenReturn(new Cuidado());
        when(this.servicioUsuario.consultarUsuario(1l)).thenReturn(new Usuario());
    }
    private void dadoQueNoExisteCuidado() {
        when(this.servicioCuidado.ObtenerDetalle(anyLong())).thenReturn(null);
        when(this.servicioUsuario.consultarUsuario(1l)).thenReturn(new Usuario());
    }

    private void dadoQueNoExisteUsuario() {
        when(this.servicioCuidado.ObtenerDetalle(1)).thenReturn(new Cuidado());
        when(this.servicioUsuario.consultarUsuario(anyLong())).thenReturn(null);
    }

    private void dadoUnUsuarioAdministrador(long idUsuario) {
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuarioAdministrador = new TipoUsuario();
        tipoUsuarioAdministrador.setNombre("Administrador");
        usuario.setTipoUsuario(tipoUsuarioAdministrador);

        when(servicioUsuario.consultarUsuario(idUsuario)).thenReturn(usuario);
    }

}
