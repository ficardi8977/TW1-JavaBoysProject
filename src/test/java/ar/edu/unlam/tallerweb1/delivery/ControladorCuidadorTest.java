package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidador;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidadorImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorCuidadorTest {

    private ServicioCuidadorImpl servicioCuidador;
    private ControladorCuidador controladorCuidador;
    private List<Cuidado> cuidadores;

    @Before
    public void init(){
        this.servicioCuidador = mock(ServicioCuidadorImpl.class);
        this.controladorCuidador = new ControladorCuidador(servicioCuidador);
        this.cuidadores = new ArrayList<>();
        when(this.servicioCuidador.ObtenerTodosLosCuidadores()).thenReturn(cuidadores);
    }

    @Test
    public void queMeTraigaLosCuidadores(){

        this.cuidadores.add(new Cuidado());

        ModelAndView mav = this.controladorCuidador.getTodosLosCuidadores();

        entoncesMeLosMuestra(mav);
    }

    @Test
    public void queNoMeTraigaLosCuidadores(){
        ModelAndView mav = this.controladorCuidador.getTodosLosCuidadores();

        entoncesNoMeLosMuestra(mav);
    }

    private void entoncesNoMeLosMuestra(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidadores")).isNotNull();
        assertThat(mav.getModel().get("cuidadores").toString()).isEqualTo("[]");
    }

    private void entoncesMeLosMuestra(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidadores")).isNotNull();
    }
}
