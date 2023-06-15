package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ControladorLoginTest {

    private ControladorLogin controladorLogin;
    private ServicioLogin servicioLogin;

    @Before
    public void Init()
    {
        this.servicioLogin = mock(ServicioLoginImpl.class);
        this.controladorLogin = new ControladorLogin(this.servicioLogin);
    }

    @Test
    public void LoginExitoso(){
        //preparacion
        this.dadoQueExisteUnUsuario();

        //ejecucion

        var datosLogin = new DatosLogin();
        datosLogin.setEmail("miMail@gmail.com");
        datosLogin.setPassword("1234");

        HttpServletRequest requestE = mock(HttpServletRequest.class);
        HttpSession sessionMock = Mockito.mock(HttpSession.class);

        Mockito.when(requestE.getSession()).thenReturn(sessionMock);
        Mockito.when(requestE.getSession().getAttribute("ROL")).thenReturn("admin");
        Mockito.when(requestE.getSession().getAttribute("NOMBRE")).thenReturn("John Doe");
        Mockito.when(requestE.getSession().getAttribute("IDUSUARIO")).thenReturn(1L);

        var modelAndView = this.controladorLogin.validarLogin(datosLogin,requestE);
        //validacion
        this.ValidoLoginExitoso(modelAndView);
    }
    @Test
    public void LoginNoExitoso(){
        //preparacion
        this.dadoQueNoExisteUnUsuario();

        //ejecucion

        var datosLogin = new DatosLogin();
        datosLogin.setEmail("miMail@gmail.com");
        datosLogin.setPassword("1234");
        HttpServletRequest request = mock(HttpServletRequest.class);

        var modelAndView = this.controladorLogin.validarLogin(datosLogin,request);
        //validacion
        this.ValidoLoginNoExitoso(modelAndView);
    }

    private void ValidoLoginNoExitoso(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("login");
        assertThat(modelAndView.getModel().values().stream().findFirst().get()).isEqualTo("Usuario o clave incorrecta");
        verify(servicioLogin, atLeastOnce()).consultarUsuario("miMail@gmail.com","1234");
    }

    private void dadoQueNoExisteUnUsuario() {
        when(this.servicioLogin.consultarUsuario(any(),any())).thenReturn(null);
    }

    private void ValidoLoginExitoso(ModelAndView result) {
        assertThat(result.getViewName()).isEqualTo("redirect:/home");
        assertThat(result.getModel().size()).isEqualTo(0); // porque hace una redireccion a lahome
        verify(servicioLogin, atLeastOnce()).consultarUsuario("miMail@gmail.com","1234");
    }

    private void dadoQueExisteUnUsuario() {
        when(this.servicioLogin.consultarUsuario(any(),any())).thenReturn(new Usuario());
    }


}