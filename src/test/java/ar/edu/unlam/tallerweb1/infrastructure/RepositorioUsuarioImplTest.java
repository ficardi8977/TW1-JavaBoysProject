package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.EmailYaRegistrado;
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
    public static final String CORREO = "magliano@gmail.com";
    public static final String CLAVE = "Admin123";

    @Test
    @Transactional
    @Rollback
    public void queSePuedaRegistrarUnUsuario() {

        DatosRegistracion datos = seLlenaUnFormulario();

        registroAlUsuario(datos);
        Usuario nuevoUsuario = buscoElUsuario(datos.getEmail(), datos.getPassword());

        entoncesSeRegistro(nuevoUsuario);
    }

    @Test (expected = EmailYaRegistrado.class)
    @Transactional
    @Rollback
    public void queNoPuedaRegistrarUnCorreoYaExistenteExcepcion() {

        dadoQueYaExisteUnEmail();
        DatosRegistracion datos = seLlenaUnFormularioRepetido();

        registroAlUsuario(datos);
    }

    private DatosRegistracion seLlenaUnFormularioRepetido() {
        DatosRegistracion datosForm = new DatosRegistracion();
        datosForm.setNombre("Tomas");
        datosForm.setApellido("Magliano");
        datosForm.setEmail("tomas@gmail.com");
        datosForm.setPassword(CLAVE);
        datosForm.setTelefono("112");
        datosForm.setLatitud("-34.6157959");
        datosForm.setLongitud("-58.5158707");

        return datosForm;
    }

    private void registroAlUsuario(DatosRegistracion datos) {
        this.repositorioUsuario.registroUsuario(datos);
    }

    private void entoncesSeRegistro(Usuario nuevoUsuario) {
        assertThat(nuevoUsuario).isNotNull();
        assertThat(nuevoUsuario.getNombre()).isEqualTo("Tomas");
        assertThat(nuevoUsuario.getEmail()).isEqualTo("magliano@gmail.com");
    }

    private Usuario buscoElUsuario(String email, String password) {
        return repositorioUsuario.buscarUsuario(email, password);
    }

    private DatosRegistracion seLlenaUnFormulario() {
        DatosRegistracion datosForm = new DatosRegistracion();
        datosForm.setNombre("Tomas");
        datosForm.setApellido("Magliano");
        datosForm.setEmail(CORREO);
        datosForm.setPassword(CLAVE);
        datosForm.setTelefono("112");
        datosForm.setLatitud("-34.6157959");
        datosForm.setLongitud("-58.5158707");

        return datosForm;
    }

    private void dadoQueYaExisteUnEmail() {
        DatosRegistracion datos = seLlenaUnFormularioRepetido();
        registroAlUsuario(datos);
    }

}
