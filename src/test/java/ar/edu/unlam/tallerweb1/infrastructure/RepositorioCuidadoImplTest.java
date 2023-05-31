package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioCuidadoImplTest  extends SpringTest {
    @Autowired
    private RepositorioCuidado repositorioCuidado;

    @Before
    public void Init(){}

    @Test
    @Transactional
    @Rollback
    public void ObtenerTodosLosRefugios(){
        List<Cuidado> cuidadoList = repositorioCuidado.TodosLosRefugios();
        verificoQueLaListaNoEsteVacia(cuidadoList);
    }

    private void verificoQueLaListaNoEsteVacia(List<Cuidado> cuidadoList) {
        assertThat(cuidadoList.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerRefugioDetalle_Encontrado() {
        long idRefugio = 1;
        Cuidado refugio = this.repositorioCuidado.BuscarDetalleRefugio(idRefugio);
        assertThat(refugio).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerRefugioDetalle_NoEncontrado() {
        long idRefugio = 4;
        Cuidado refugio = this.repositorioCuidado.BuscarDetalleRefugio(idRefugio);
        assertThat(refugio).isNull();
    }

}
