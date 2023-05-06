package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ServicioRegistracionImplTest {
    private ServicioRegistracionImpl servicioRegistracion = new ServicioRegistracionImpl();
    private String correo;
    private String clave;

    @Test
    public void alIngresarUnCorreoValidoMeDevuelveVerdadero(){
        Boolean esValido = servicioRegistracion.esValido("fittipaldi.h@gmail.com");
        entoncesMiCorreo(esValido);
    }

    @Test
    public void alIngresarCredencialesValidasMePuedoRegistrarExitosamente(){
        dadoQueTengoCredencialesValidas();
        Boolean registroExitoso = cuandoMeRegistro(this.correo, this.clave);
        entoncesEs(registroExitoso);
    }

    private void entoncesEs(Boolean registroExitoso) {
        assertThat(registroExitoso).isTrue();
    }

    private Boolean cuandoMeRegistro(String correo, String clave) {
        return this.servicioRegistracion.registrarUsuario(correo, clave);
    }

    private void dadoQueTengoCredencialesValidas() {
        this.correo = "fittipaldi.h@gmail.com";
        this.clave = "asdfg";
    }

    private void entoncesMiCorreo(Boolean esValido) {
        assertThat(esValido).isTrue();
    }
}
