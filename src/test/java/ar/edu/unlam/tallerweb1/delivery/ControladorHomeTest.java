package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.home.ServicioHome;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.ldap.Control;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorHomeTest {

    private ControladorHome controladorHome;
    private ServicioHome servicioHome;

    @Before
    public void init()
    {
        this.servicioHome = mock(ServicioHome.class);
        this.controladorHome = new ControladorHome(this.servicioHome);
    }

    @Test
    public void obtenerListadoCarruseles()
    {
        this.dadoQueExisteUnListadoDeCarruseles();
        var result =this.controladorHome.irAHome();
        this.validarExistenciaDeCarruseles(result);
    }
    @Test
    public void noObtenerListadoCarruseles()
    {
        this.dadoQueNoExisteUnListadoDeCarruseles();
        var result = this.controladorHome.irAHome();
        this.validarqueNoExisteDeCarruseles(result);
    }

    private void validarExistenciaDeCarruseles(ModelAndView result) {
        assertThat(result.getViewName()).isEqualTo("home-mdq");
        assertThat(result.getModelMap().get("carruseles")).isNotNull();
    }

    private void dadoQueExisteUnListadoDeCarruseles() {
        var dtoHome = new DTOHome();
        when(this.servicioHome.ListCarruseles()).thenReturn(dtoHome);
    }
    private void validarqueNoExisteDeCarruseles(ModelAndView result) {
        assertThat(result.getViewName()).isEqualTo("home-mdq");
        assertThat(result.getModelMap().get("carruseles")).isNull();
    }

    private void dadoQueNoExisteUnListadoDeCarruseles() {
        when(this.servicioHome.ListCarruseles()).thenReturn(null);
    }
}
