package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import org.springframework.web.servlet.ModelAndView;
import org.junit.Before;
import org.junit.Test;
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



}
