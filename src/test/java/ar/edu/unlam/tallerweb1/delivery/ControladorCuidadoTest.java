package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.web.servlet.ModelAndView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorCuidadoTest {
        private ControladorCuidado controladorCuidado;
        private ServicioCuidado servicioCuidado;

    @Before
    public void init(){
        this.servicioCuidado = mock(ServicioCuidado.class);
        controladorCuidado = new ControladorCuidado(this.servicioCuidado);
    }


    @Test
    public void alIngresarATodosLosRefugiosMeMuestraLaPantallaDeTodosLosRefugios(){
        ModelAndView mav = cuandoMeVoyATodosLosRefugios();
        entoncesMeLlevaALaPantallaDeTodosLosRefugios(mav);

    }
    private ModelAndView cuandoMeVoyATodosLosRefugios()  {
        return controladorCuidado.getTodosLosRefugios();
    }

    private void entoncesMeLlevaALaPantallaDeTodosLosRefugios(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("todos-los-refugios");
    }

    @Test
    public void getRefugioDetalle_Ok(){
        long id = 1;
        this.dadoQueExisteRefugio(id);
        ModelAndView result = this.controladorCuidado.getDetalleRefugio(id);
        assertThat(result.getViewName()).isEqualTo("refugios-detalle");
    }

    private void dadoQueExisteRefugio(long idRefugio) {
        List<Cuidado> cuidados = new ArrayList<>();
        Cuidado cuidado = new Cuidado();
        cuidado.setId(idRefugio);
        cuidados.add(cuidado);
        when(this.servicioCuidado.ObtenerDetalleRefugio(idRefugio)).thenReturn(cuidado);
    }


}
