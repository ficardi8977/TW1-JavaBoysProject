package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuarioImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioLoginImplTest {

    private ServicioUsuario servicioLogin;

    private RepositorioUsuario repositorioUsuario;
    private final String CORREO = "miMail@gmail.com";
    private final String CORREO_INEXISTENTE = "miMail2@gmail.com";
    private final String CLAVE = "1234";
    @Before
    public void init() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioLogin = new ServicioUsuarioImpl(this.repositorioUsuario);
        String claveNueva = servicioLogin.encriptarClave(CLAVE);
        Usuario usuarioClave = new Usuario();
        usuarioClave.setPassword(claveNueva);
        when(this.repositorioUsuario.buscarUsuario(CORREO, claveNueva)).thenReturn(usuarioClave);
        when(this.repositorioUsuario.buscarUsuario(CORREO_INEXISTENTE,claveNueva)).thenReturn(null);
    }
    @Test
    public void obtenerUsuarioLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario user = consultoElUsuario(CORREO, CLAVE);
        this.validarUsuarioExiste(user);
    }

    @Test
    public void noObtenerUsuarioLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario user = consultoElUsuario(CORREO_INEXISTENTE, CLAVE);
        this.validarUsuarioNoExiste(user);
    }

    @Test
    public void seEncriptaLaClave() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario user = consultoElUsuario(CORREO, CLAVE);
        validoQueLaClaveHayaCambiado(user.getPassword());
    }

    private void validoQueLaClaveHayaCambiado(String password) {
        assertThat(password).isNotEqualTo(CLAVE);
    }

    private Usuario consultoElUsuario(String correo, String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return this.servicioLogin.consultarUsuario(correo, clave);
    }

    private void validarUsuarioExiste(Usuario result) {
        assertThat(result).isNotNull();
    }

    private void validarUsuarioNoExiste(Usuario result) {
        assertThat(result).isNull();
    }
}