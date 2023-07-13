package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class RepositorioComentarioTest extends SpringTest {
    @Autowired
    private RepositorioComentario repositorioComentario;
    @Autowired
    private RepositorioCuidado repositorioCuidado;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    private Comentario comentario;

    @Before
    public void init()
    {
        this.comentario = new Comentario();
    }
    @Test
    @Transactional
    @Rollback
    public void guardar(){

        this.dadoUnCuidadoExistente();
        this.dadoUnComentarioQueQuieroGuardar(
                this.dadoUnUsuarioExistente(),
                this.dadoUnCuidadoExistente()
        );
        this.repositorioComentario.guardar(this.comentario);
    }

    private Cuidado dadoUnCuidadoExistente() {
        var cuidado = new Cuidado();
        cuidado.setEmail("mimailcito@gmail.com");
        cuidado.setNombre("Cuidado");
        this.repositorioCuidado.Guardar(cuidado);
        return cuidado;
    }

    private Usuario dadoUnUsuarioExistente() {
        var usuario = new Usuario();
        usuario.setEmail("mimailcito@gmail.com");
        usuario.setNombre("nombre");
        usuario.setApellido("apellido");
        this.repositorioUsuario.guardar(usuario);
        return usuario;
    }

    private void dadoUnComentarioQueQuieroGuardar(Usuario usuario, Cuidado cuidado) {
        this.comentario.setMensaje("es buenisimo!");
        this.comentario.setClasificacion(5);
        this.comentario.setUsuario(usuario);
        this.comentario.setCuidado(cuidado);
    }

    @Test
    @Transactional
    @Rollback
    public void eliminar() {

        Cuidado cuidado = dadoUnCuidadoExistente();
        Usuario usuario = dadoUnUsuarioExistente();

        dadoUnComentarioQueQuieroGuardar(usuario, cuidado);

        var idComentarioExistente = this.dadoQueExisteUnComentario();
        Comentario comentarioObtenidoAntes = repositorioComentario.obtener(idComentarioExistente);

        repositorioComentario.eliminar(comentarioObtenidoAntes);
        Comentario comentarioObtenidoDespues = repositorioComentario.obtener(idComentarioExistente);

        assertNotNull(comentarioObtenidoAntes);
        assertNull(comentarioObtenidoDespues);
    }

    @Test
    @Transactional
    @Rollback
    public void obtenerSubComentario() {

        Cuidado cuidado = dadoUnCuidadoExistente();
        Usuario usuario = dadoUnUsuarioExistente();

        dadoUnComentarioQueQuieroGuardar(usuario, cuidado);
        dadoQueExisteUnComentario();
        dadoQueExisteUnSubComentario(this.comentario);


        var result = repositorioComentario.obtenerSubcomentarios(this.comentario.getId());

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void NoObtenerSubComentario() {

        Cuidado cuidado = dadoUnCuidadoExistente();
        Usuario usuario = dadoUnUsuarioExistente();

        dadoUnComentarioQueQuieroGuardar(usuario, cuidado);
        dadoQueExisteUnComentario();

        var result = repositorioComentario.obtenerSubcomentarios(this.comentario.getId());

        assertThat(result.size()).isEqualTo(0);
    }
    private int dadoQueExisteUnComentario()
    {
        return repositorioComentario.guardar(comentario);
    }

    private int dadoQueExisteUnSubComentario(Comentario comentario){

        var subcomentario = new Comentario();
        subcomentario.setComentarioPadre(comentario);
        return this.repositorioComentario.guardar(subcomentario);
    }


}
