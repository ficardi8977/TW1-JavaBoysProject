package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;
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

    @Autowired
    private RepositorioTipoCuidado repositorioTipoCuidado;

    private Tipocuidado tipocuidadoResult;
    private Cuidado cuidadoResult;
    @Before
    public void Init(){
        this.tipocuidadoResult = existeUnTipoRefugio();
        this.cuidadoResult = existeUnCuidadoDelTipoRefugio(tipocuidadoResult);

    }

    private Cuidado existeUnCuidadoDelTipoRefugio(Tipocuidado tipocuidadoResult) {
        Cuidado cuidado= new Cuidado();
        cuidado.setNombre("Refugio-1");
        cuidado.setEmail("refu1@refu1.com");
        cuidado.setTipocuidado(tipocuidadoResult);
        this.repositorioCuidado.Guardar(cuidado);
        return cuidado;
    }

    private Tipocuidado existeUnTipoRefugio() {
        Tipocuidado tipocuidado = new Tipocuidado();
        tipocuidado.setNombre("Refugio");
        this.repositorioTipoCuidado.Guardar(tipocuidado);
        return tipocuidado;
    }


    @Test
    @Transactional
    @Rollback
    public void ObtenerTodosLosRefugios(){
        List<Cuidado> cuidadoList = repositorioCuidado.TodosLosRefugios();
        verificoQueLaListaNoEsteVacia(cuidadoList);
    }

    private void verificoQueLaListaNoEsteVacia(List<Cuidado> cuidadoList) {
        assertThat(cuidadoList.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerRefugioDetalle_Encontrado() {
        Cuidado refugio = this.repositorioCuidado.BuscarDetalleRefugio(cuidadoResult.getId());
        assertThat(refugio).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerRefugioDetalle_NoEncontrado() {
        long idRefugio = 55;
        Cuidado refugio = this.repositorioCuidado.BuscarDetalleRefugio(idRefugio);
        assertThat(refugio).isNull();
    }

}
