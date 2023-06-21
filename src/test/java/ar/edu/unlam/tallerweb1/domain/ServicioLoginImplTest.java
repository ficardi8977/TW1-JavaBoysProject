package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuarioImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioLoginImplTest {

    private ServicioUsuario servicioLogin;

    private RepositorioUsuario repositorioUsuario;
    @Before
    public void init(){
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioLogin = new ServicioUsuarioImpl(this.repositorioUsuario);
    }
    @Test
    public void obtenerUsuarioLogin(){
        this.dadoUnUsuarioExiste();
        var result = this.servicioLogin.consultarUsuario("miMail@gmail.com","1234");
        this.validarUsuarioExiste(result);
    }

    @Test
    public void noObtenerUsuarioLogin(){
        this.dadoUnUsuarioExiste();
        var result = this.servicioLogin.consultarUsuario("miMail@gmail.com","1234");
        this.validarUsuarioExiste(result);
    }

    private void validarUsuarioExiste(Usuario result) {
        assertThat(result).isNotNull();
    }

    private void dadoUnUsuarioExiste() {
        when(this.servicioLogin.consultarUsuario("miMail@gmail.com","1234"))
                .thenReturn(new Usuario());
    }
    private void dadoUnUsuarioNoExiste() {
        when(this.servicioLogin.consultarUsuario("miMail2@gmail.com","1234"))
                .thenReturn(null);
    }
    private void validarUsuarioNoExiste(Usuario result) {
        assertThat(result).isNull();
    }
}
