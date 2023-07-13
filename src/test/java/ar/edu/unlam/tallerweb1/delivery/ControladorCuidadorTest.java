package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidadoImpl;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidadoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorCuidadorTest {

    private ServicioCuidadoImpl servicioCuidador;
    private ControladorCuidador controladorCuidador;
    private List<Cuidado> cuidadores;

    @Before
    public void init(){
        this.servicioCuidador = mock(ServicioCuidadoImpl.class);
        this.controladorCuidador = new ControladorCuidador(servicioCuidador);
        this.cuidadores = new ArrayList<>();
        cuidadores.add(new Cuidado());
        when(this.servicioCuidador.ObtenerTodosLosCuidadores()).thenReturn(cuidadores);
        when(this.servicioCuidador.ObtenerDetalle(1)).thenReturn(cuidadores.get(0));
    }

    @Test
    public void queMeTraigaLosCuidadores(){

        this.cuidadores.add(new Cuidado());

        ModelAndView mav = this.controladorCuidador.getTodosLosCuidadores();

        entoncesMeLosMuestra(mav);
    }

    @Test
    public void queNoMeTraigaLosCuidadores(){

        this.cuidadores.remove(0);


        ModelAndView mav = this.controladorCuidador.getTodosLosCuidadores();

        entoncesNoMeLosMuestra(mav);
    }

    @Test
    public void queMeMuestreLosDetalles(){
        ModelAndView mav = this.controladorCuidador.getDetalle(1);

        entoncesMeMuestraLosDetalles(mav);
    }

    @Test
    public void queNoMeMuestreLosDetalles(){
        ModelAndView mav = this.controladorCuidador.getDetalle(2);

        entoncesNoMeMuestraLosDetalles(mav);
    }

    private void entoncesNoMeMuestraLosDetalles(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidado")).isNull();
    }

    private void entoncesMeMuestraLosDetalles(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidado")).isNotNull();
        assertThat(mav.getViewName().toString()).isEqualTo("detalle-cuidador-mdq");
    }

    private void entoncesNoMeLosMuestra(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidadores")).isNotNull();
        assertThat(mav.getModel().get("cuidadores").toString()).isEqualTo("[]");
    }

    private void entoncesMeLosMuestra(ModelAndView mav) {
        assertThat(mav.getModel().get("cuidadores")).isNotNull();
    }
}
