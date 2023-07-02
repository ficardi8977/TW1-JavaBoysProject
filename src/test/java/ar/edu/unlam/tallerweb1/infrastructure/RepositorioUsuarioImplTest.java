package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.EmailYaRegistrado;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoEncontrado;
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

    public static final String CORREO_NO_REGISTRADO = "magliano2@gmail.com";
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

    @Test (expected = UsuarioNoEncontrado.class)
    @Transactional
    @Rollback
    public void seBuscaUnUsuarioNoRegistrado() {
        Usuario noRegistrado = new Usuario();
        noRegistrado.setEmail(CORREO_NO_REGISTRADO);

        Usuario encontrado = loBusco(noRegistrado);

        entoncesNoLoEncuentro(encontrado);
    }

    @Test
    @Transactional
    @Rollback
    public void seBuscaUnUsuarioRegistrado() {
        DatosRegistracion datos = seLlenaUnFormulario();
        Usuario usuarioRegistrado = new Usuario();
        usuarioRegistrado.setEmail(datos.getEmail());

        registroAlUsuario(datos);
        Usuario encontrado = loBusco(usuarioRegistrado);

        entoncesLoEncuentro(encontrado);
    }

    private void entoncesLoEncuentro(Usuario encontrado) {
        assertThat(encontrado).isNotNull();
    }

    private void entoncesNoLoEncuentro(Usuario encontrado) {
        assertThat(encontrado).isNull();
    }

    private Usuario loBusco(Usuario noRegistrado) {
        return this.repositorioUsuario.buscarGuardado(noRegistrado.getEmail());
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

}
