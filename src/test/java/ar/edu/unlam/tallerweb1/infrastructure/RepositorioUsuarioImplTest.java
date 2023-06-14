package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioUsuarioImplTest extends SpringTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void obtenerUsuarioLogueado(){
        var email = "miUsuario@gmail.com";
        var password = "1234";

        this.dadoQueExisteUsuario(email,password);
        var usuario = this.repositorioUsuario.buscarUsuario(email,password);
        this.validarUsuario(usuario);
    }

    @Test
    @Transactional
    @Rollback
    public void obtenerUsuarioNoLogueado(){
        var email = "miUsuario2@gmail.com";
        var password = "1234";
        var usuario = this.repositorioUsuario.buscarUsuario(email,password);
        this.validarUsuarioNoExistente(usuario);
    }

    private void validarUsuarioNoExistente(Usuario usuario) {
        assertThat(usuario).isNull();
    }

    private void validarUsuario(Usuario usuario) {
        assertThat(usuario).isNotNull();
        assertThat(usuario.getId()).isNotNull();
    }

    private void dadoQueExisteUsuario(String email, String password) {
        var usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);
        this.repositorioUsuario.guardar(usuario);
    }
}
