package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

public class RepositorioCuidadoTest extends SpringTest {

    @Autowired
    private RepositorioCuidado repositorioCuidador;
    private Cuidado cuidado;
    private Cuidado cuidado2;
    private Cuidado cuidado3;

    @Before
    public void init(){
        this.cuidado = dadoQueExisteUnCuidador();
        this.cuidado2 = dadoQueExisteUnCuidador();
        this.cuidado3 = dadoQueExisteUnCuidador();
    }

    private Cuidado dadoQueExisteUnCuidador() {
        Tipocuidado tc = new Tipocuidado();
        tc.setNombre("Cuidador");
        this.repositorioCuidador.GuardarTipoCuidado(tc);
        Cuidado c = new Cuidado();
        c.setNombre("Tomas");
        c.setEmail("tomas@gmail.com");
        c.setTipocuidado(tc);
        c.setComentarios(Collections.emptyList());
        this.repositorioCuidador.Guardar(c);
        return c;
    }


    @Test
    @Transactional
    @Rollback
    public void meTraeTodosLosCuidadores(){

        List<Cuidado> cuidadores = this.repositorioCuidador.obtenerTodosLosCuidadores();

        entoncesMeTraeLosCuidadores(cuidadores);
    }

    @Test
    @Transactional
    @Rollback
    public void meMuestraLosDetalles(){
        Cuidado detalles = this.repositorioCuidador.getDetalle(cuidado.getId());

        meTraeElDetalle(detalles);
    }

    @Test
    @Transactional
    @Rollback
    public void noMeMuestraLosDetalles(){
        Cuidado detalles = this.repositorioCuidador.getDetalle(22);

        noMeTraeElDetalle(detalles);
    }

    private void noMeTraeElDetalle(Cuidado detalles) {
        assertThat(detalles).isNull();
    }

    private void meTraeElDetalle(Cuidado detalles) {
        assertThat(detalles).isNotNull();
    }


    private void entoncesMeTraeLosCuidadores(List<Cuidado> cuidadores) {
        assertThat(cuidadores.size()).isEqualTo(3);
    }


}
