package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.EmailInvalido;
import ar.edu.unlam.tallerweb1.domain.excepciones.EmailYaRegistrado;
import ar.edu.unlam.tallerweb1.domain.excepciones.IngresarTelefono;
import ar.edu.unlam.tallerweb1.domain.excepciones.PasswordInvalida;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioRegistracionImplTest {
    private ServicioRegistracionImpl servicioRegistracion;
    private RepositorioUsuario repositorioUsuario;
    private final String CORREO = "correo@gmail.com";
    private final String CORREO_EXISTENTE = "amipets@gmail.com";
    private final String CORREO_INVALIDO = "correo@gmail";
    private final String CLAVE = "Admin1";
    private final String CLAVE_INVALIDA = "admin1";
    private DatosRegistracion datos;


    @Before
    public void init() {
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioRegistracion = new ServicioRegistracionImpl(repositorioUsuario);
        Usuario usuarioExistente = new Usuario();
        datos = dadoQueSeEnviaUnForm();
        usuarioExistente.setEmail(CORREO_EXISTENTE);
        when(this.repositorioUsuario.buscar(CORREO_EXISTENTE)).thenReturn(usuarioExistente);
        when(this.repositorioUsuario.registroUsuario(datos)).thenReturn(true);
    }

    @Test
    public void queValideLosDatosDelForm(){
        DatosRegistracion datos = dadoQueSeEnviaUnForm();

        Boolean datosValidos = seValidanLosDatos(datos);

        entoncesSonValidos(datosValidos);
    }

    @Test
    public void queSePuedaRegistrarUnUsuario() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        seValidanLosDatos(datos);
        Boolean registrado = loRegistro(datos);

        entoncesSeRegistro(registrado);
    }

    @Test (expected = EmailInvalido.class)
    public void queNoPermitaDatosInvalidosExcepcionEmail(){
        datos.setEmail(CORREO_INVALIDO);
        seValidanLosDatos(datos);
    }

    @Test (expected = PasswordInvalida.class)
    public void queNoPermitaDatosInvalidosExcepcionPassword(){
        datos.setPassword(CLAVE_INVALIDA);
        seValidanLosDatos(datos);
    }

    @Test (expected = IngresarTelefono.class)
    public void queNoPermitaDatosInvalidosExcepcionTelefono(){
        datos.setTelefono("");
        seValidanLosDatos(datos);
    }

    @Test (expected = EmailYaRegistrado.class)
    public void queNoPermitaDatosInvalidosExcepcionEmailExistente() {
        datos.setEmail(CORREO_EXISTENTE);
        seValidanLosDatos(datos);
    }

    private void entoncesSeRegistro(Boolean registrado) {
        assertThat(registrado).isTrue();
    }

    private Boolean loRegistro(DatosRegistracion datos) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return this.servicioRegistracion.registroUsuario(datos);
    }

    private void entoncesSonValidos(Boolean datosValidos) {
        assertThat(datosValidos).isTrue();
    }

    private Boolean seValidanLosDatos(DatosRegistracion datos) {
        return this.servicioRegistracion.datosValidos(datos);
    }

    private DatosRegistracion dadoQueSeEnviaUnForm() {
        DatosRegistracion datosForm = new DatosRegistracion();
        datosForm.setNombre("Tomas");
        datosForm.setApellido("Magliano");
        datosForm.setEmail(CORREO);
        datosForm.setPassword(CLAVE);
        datosForm.setTelefono("1122334455");
        datosForm.setLatitud("-34.6157959");
        datosForm.setLongitud("-58.5158707");

        return datosForm;
    }

}
