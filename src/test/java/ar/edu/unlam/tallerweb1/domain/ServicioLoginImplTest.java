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
        this.servicioLogin = new ServicioLoginImpl(this.repositorioUsuario);
        String claveNueva = servicioLogin.encriptarClave(CLAVE);
        Usuario usuarioClave = new Usuario();
        usuarioClave.setPassword(claveNueva);
        when(this.repositorioUsuario.buscarUsuario(CORREO, claveNueva)).thenReturn(usuarioClave);
        when(this.repositorioUsuario.buscarUsuario(CORREO_INEXISTENTE,claveNueva)).thenReturn(null);
    }
    @Test
    public void obtenerUsuarioLogin() {
        Usuario user = consultoElUsuario(CORREO, CLAVE);
        this.validarUsuarioExiste(user);
    }

    @Test
    public void noObtenerUsuarioLogin(){
        Usuario user = consultoElUsuario(CORREO_INEXISTENTE, CLAVE);
        this.validarUsuarioNoExiste(user);
    }

    @Test
    public void seEncriptaLaClave(){
        Usuario user = consultoElUsuario(CORREO, CLAVE);
        validoQueLaClaveHayaCambiado(user.getPassword());
    }

    private void validoQueLaClaveHayaCambiado(String password) {
        assertThat(password).isNotEqualTo(CLAVE);
    }

    private Usuario consultoElUsuario(String correo, String clave) {
        return this.servicioLogin.consultarUsuario(correo, clave);
    }

    private void validarUsuarioExiste(Usuario result) {
        assertThat(result).isNotNull();
    }

    private void validarUsuarioNoExiste(Usuario result) {
        assertThat(result).isNull();
    }
}
