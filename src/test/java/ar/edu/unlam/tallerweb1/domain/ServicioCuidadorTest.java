package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidadoImpl;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidadoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioCuidadorTest {

    private RepositorioCuidadoImpl repositorioCuidador;
    private ServicioCuidado servicioCuidador;
    private List<Cuidado> cuidadores;

    @Before
    public void init(){
        this.repositorioCuidador = mock(RepositorioCuidadoImpl.class);
        this.servicioCuidador = new ServicioCuidadoImpl(repositorioCuidador);
        this.cuidadores = new ArrayList<>();
        cuidadores.add(new Cuidado());
        cuidadores.add(new Cuidado());
        when(this.repositorioCuidador.obtenerTodosLosCuidadores()).thenReturn(cuidadores);
        when(this.repositorioCuidador.getDetalle(2)).thenReturn(cuidadores.get(1));
    }

    @Test
    public void queTraigaATodosLosCuidadores(){

        List<Cuidado> lista = servicioCuidador.ObtenerTodosLosCuidadores();

        obtieneLosCuidadores(lista);
    }

    @Test
    public void queTraigaLosDetalles(){

        Cuidado detalle = servicioCuidador.ObtenerDetalle(2);

        meTraeElDetalle(detalle);

    }

    @Test
    public void queNoTraigaLosDetalles(){

        Cuidado detalle = servicioCuidador.ObtenerDetalle(3);

        noMeTraeElDetalle(detalle);

    }

    private void noMeTraeElDetalle(Cuidado detalle) {
        assertThat(detalle).isNull();
    }


    private void meTraeElDetalle(Cuidado detalle) {
        assertThat(detalle).isNotNull();
    }


    private void obtieneLosCuidadores(List<Cuidado> lista) {
        assertThat(lista.size()).isEqualTo(2);
    }


}
