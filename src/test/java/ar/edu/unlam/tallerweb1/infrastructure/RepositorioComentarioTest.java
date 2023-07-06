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
}
