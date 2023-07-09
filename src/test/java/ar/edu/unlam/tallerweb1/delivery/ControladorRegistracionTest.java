package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.excepciones.EmailInvalido;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class
ControladorRegistracionTest {
    public static final String CORREO = "magliano@gmail.com";
    public static final String CORREO_INVALIDO = "magliano@gmail";
    public static final String CLAVE = "Admin1";
    private ServicioRegistracion servicioRegistracion;
    private ControladorRegistracion controladorRegistracion;
    private DatosRegistracion formulario;
    private DatosRegistracion formularioInvalido;

    private RedirectAttributes redirectAttributes;
    private MultipartFile img;

    @Before
    public void init() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.servicioRegistracion = mock(ServicioRegistracionImpl.class);
        this.controladorRegistracion = new ControladorRegistracion(servicioRegistracion);
        this.redirectAttributes = new RedirectAttributesModelMap();
        this.img = mock(MultipartFile.class);
        formulario = new DatosRegistracion();
        formulario.setEmail(CORREO);
        formulario.setPassword(CLAVE);
        formularioInvalido = new DatosRegistracion();
        formularioInvalido.setEmail(CORREO_INVALIDO);
        formularioInvalido.setPassword(CLAVE);
        when(this.servicioRegistracion.registroUsuario(formulario)).thenReturn(true);
        when(this.servicioRegistracion.registroUsuario(formularioInvalido)).thenThrow(new EmailInvalido());
    }

    @Test
    public void alIngresarARegistrarmeMeMuestraLaPantallaDeRegistro() {
        ModelAndView mav = cuandoMeQuieroRegistrar();
        assertThat(mav.getViewName()).isEqualTo("registrar-usuario");

    }

    @Test
    public void alIngresarCredencialesCorrectasMeRegistroYMeDevuelveAlLogin() {
        ModelAndView mav = meRegistro(formulario, img, redirectAttributes);
        Object view = mav.getView();
        String url = ((RedirectView) view).getUrl();

        assertThat(view.getClass()).isEqualTo(RedirectView.class);
        assertThat(url).isEqualTo("/login");
    }

    @Test
    public void alIngresarCredencialesIncorrectasNoMeRegistro() {
        ModelAndView mav = meRegistro(formularioInvalido, img, redirectAttributes);
        assertThat(mav.getViewName()).isEqualTo("registrar-usuario");
    }

    @Test
    public void alIngresarCredencialesIncorrectasNoMeRegistroYMeDevuelveUnMensajeDeError() {
        ModelAndView mav = meRegistro(formularioInvalido, img, redirectAttributes);
        assertThat(mav.getModel().get("error")).isEqualTo("Formato de email invalido");
    }

    @Test
    public void alRegistrarmeMeDevuelveUnMensajeDeExito(){
        ModelAndView mav = meRegistro(formulario, img,  redirectAttributes);
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        String mensaje = (String) flashAttributes.get("error");

        assertThat(mensaje).isEqualTo("Usuario registrado");
    }

    private ModelAndView meRegistro(DatosRegistracion datos, MultipartFile img,RedirectAttributes redirectAttributes) {
        return controladorRegistracion.registrarUsuario(datos, img, redirectAttributes);
    }

    private ModelAndView cuandoMeQuieroRegistrar() {
        return controladorRegistracion.registrarme();
    }

}
