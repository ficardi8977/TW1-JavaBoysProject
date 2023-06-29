package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.springframework.web.servlet.ModelAndView;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class
ControladorRegistracionTest {

    public static final String CORREO = "fittipaldi.h@gmail.com";
    public static final String CORREO_INVALIDO = "Fitti";
    public static final String CLAVE = "asdfg";
    private ServicioRegistracion servicioRegistracion;
    private ControladorRegistracion controladorRegistracion;
    private DatosRegistracion datosRegistracion;
    private DatosRegistracion datosRegistracionInvalido;
/*
    @Before
    public void init(){
        this.datosRegistracion = new DatosRegistracion(CORREO, CLAVE);
        this.datosRegistracionInvalido = new DatosRegistracion(CORREO_INVALIDO, CLAVE);
        this.servicioRegistracion = mock(ServicioRegistracionImpl.class);
        this.controladorRegistracion = new ControladorRegistracion(this.servicioRegistracion);
    }

    @Test
    public void alIngresarARegistrarmeMeMuestraLaPantallaDeRegistro(){
        dadoQueNoExisteElUsuario(this.datosRegistracion, null);
        ModelAndView mav = cuandoMeQuieroRegistrar();
        entoncesMeLlevaALaPantallaDeRegistracion(mav);
    }

    @Test
    public void alIngresarCredencialesCorrectasDeUnUsuarioQueNoExisteMeRegistraYLlevaAlLogin(){
        // Correo y una contraseña valida

        // Preparacion
        dadoQueNoExisteElUsuario(this.datosRegistracion, true);

        // Ejecucion
        ModelAndView mav = cuandoMeRegistro(this.datosRegistracion);

        // Verificacion
        entoncesElRegistroEsExitoso(mav);
    }

    @Test
    public void alIngresarCredencialesInvalidasNoMePermiteRegistrarme(){
        dadoQueNoExisteElUsuario(this.datosRegistracionInvalido, false);
        ModelAndView mav = cuandoMeRegistro(this.datosRegistracionInvalido);
        entoncesElRegistroFalla(mav);
    }

    private ModelAndView cuandoMeQuieroRegistrar() {
        return controladorRegistracion.registrarme();
    }

    private void entoncesMeLlevaALaPantallaDeRegistracion(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
    }

    private void entoncesElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido");
    }

    private void dadoQueLasCredencialesSonInvalidas(){
        when(this.servicioRegistracion.esValido(datosRegistracion.getCorreo())).thenReturn(false);
        when(this.servicioRegistracion.registrarUsuario(datosRegistracion.getCorreo(), datosRegistracion.getClave())).thenReturn(false);
    }

    private void dadoQueNoExisteElUsuario(DatosRegistracion datosRegistracion, Boolean retorno) {
        when(this.servicioRegistracion.esValido(datosRegistracion.getEmail())).thenReturn(retorno);
        when(this.servicioRegistracion.registrarUsuario(datosRegistracion.getEmail(), datosRegistracion.getPassword())).thenReturn(retorno);
    }

    private ModelAndView cuandoMeRegistro(DatosRegistracion datosRegistracion) {
        return controladorRegistracion.registrarUsuario(datosRegistracion);
    }

    private void entoncesElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro exitoso");
    }
*/
}
