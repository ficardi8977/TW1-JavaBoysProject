package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidadoImpl;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidado;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCuidadoImplTests {

    private RepositorioCuidado repositorioCuidado;
    private ServicioCuidado servicioCuidado;
    List<Cuidado>  cuidadoList;
    @Before
    public void init(){
        this.repositorioCuidado = mock(RepositorioCuidado.class);
        this.cuidadoList= new ArrayList<>();
        this.cuidadoList.add(new Cuidado());
        when(this.repositorioCuidado.TodosLosRefugios()).thenReturn(cuidadoList);
        when(this.repositorioCuidado.BuscarDetalleRefugio(1)).thenReturn(cuidadoList.get(0));
        this.servicioCuidado = new ServicioCuidadoImpl(this.repositorioCuidado);
    }

    @Test
    public void ObtenerTodosLosRefugios(){
        List<Cuidado> resultList = this.servicioCuidado.ObtenerTodosLosRefugios();
        assertThat(resultList.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void ObtenerRefugioDetalleEncontrado(){
        long id = 1;
        Cuidado refugio  = this.servicioCuidado.ObtenerDetalleRefugio(id);
        assertThat(refugio).isNotNull();
    }
    @Test
    public void ObtenerRefugioDetalleNoEncontrado(){
        long id = 3;
        Cuidado refugio  = this.servicioCuidado.ObtenerDetalleRefugio(id);
        assertThat(refugio).isNull();
    }


}
