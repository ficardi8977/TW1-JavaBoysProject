package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidador;
import ar.edu.unlam.tallerweb1.domain.cuidadores.ServicioCuidadorImpl;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidador;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidadorImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioCuidadorTest {

    private RepositorioCuidadorImpl repositorioCuidador;
    private ServicioCuidador servicioCuidador;
    private List<Cuidado> cuidadores;

    @Before
    public void init(){
        this.repositorioCuidador = mock(RepositorioCuidadorImpl.class);
        this.servicioCuidador = new ServicioCuidadorImpl(repositorioCuidador);
        this.cuidadores = new ArrayList<>();
        cuidadores.add(new Cuidado());
        cuidadores.add(new Cuidado());
        when(this.repositorioCuidador.TodosLosCuidadores()).thenReturn(cuidadores);
    }

    @Test
    public void queTraigaATodosLosCuidadores(){

        List<Cuidado> lista = servicioCuidador.ObtenerTodosLosCuidadores();

        obtieneLosCuidadores(lista);
    }

    private void obtieneLosCuidadores(List<Cuidado> lista) {
        assertThat(lista.size()).isEqualTo(2);
    }


}
