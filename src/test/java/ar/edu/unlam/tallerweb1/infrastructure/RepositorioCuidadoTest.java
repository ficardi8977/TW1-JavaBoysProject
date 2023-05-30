package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.TipoCuidado;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

public class RepositorioCuidadoTest extends SpringTest {

    @Autowired
    private RepositorioCuidador repositorioCuidador;
    private Cuidado cuidado;
    private Cuidado cuidado2;

    @Before
    public void init(){
        this.cuidado = dadoQueExisteUnCuidador();
        this.cuidado2 = dadoQueExisteUnCuidador();
    }

    private Cuidado dadoQueExisteUnCuidador() {
        TipoCuidado tc = new TipoCuidado();
        tc.setNombre("Cuidador");
        this.repositorioCuidador.GuardarTipoCuidado(tc);
        Cuidado c = new Cuidado("Tomas", "tomas@gmail.com", "UNLaM", "12345", "0", "0", tc);
        this.repositorioCuidador.Guardar(c);
        return c;
    }


    @Test
    @Transactional
    @Rollback
    public void meTraeTodosLosCuidadores(){

        List<Cuidado> cuidadores = this.repositorioCuidador.TodosLosCuidadores();

        entoncesMeTraeLosCuidadores(cuidadores);
    }


    private void entoncesMeTraeLosCuidadores(List<Cuidado> cuidadores) {
        assertThat(cuidadores.size()).isEqualTo(2);
    }


}
