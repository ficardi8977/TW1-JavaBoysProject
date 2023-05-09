package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioMascotaImplTests extends SpringTest {

    @Autowired
    private RepositorioMascota repositorioMascota;
    @Autowired
    private RepositorioTipoRaza repositorioTipoRaza;
    @Autowired
    private RepositorioTipoMascota repositorioTipoMascota;

    private TipoMascota tipoMascotaResult;
    private TipoRaza tipoRazaResult;

    @Before
    public void Init()
    {
        this.tipoMascotaResult = this.existeUnaMascotaDeTipoMascota();
        this.tipoRazaResult =this.existeUnTipoRaza(tipoMascotaResult);
        this.datoQueExisteUnaMascota(tipoRazaResult);
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerMascotasPorTipo_Encontrado()
    {
        List<Mascota> mascotas = this.repositorioMascota.BuscarMascotasPorTipo(this.tipoMascotaResult.getId());
        assertThat(mascotas.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerMascotasPorTipo_NoEncontrado()
    {
        long idTipoMascotaNoExistente = 3;
        TipoMascota tipoMascotaNoExistente = new TipoMascota();
        tipoMascotaNoExistente.setId(idTipoMascotaNoExistente);

        List<Mascota> mascotas = this.repositorioMascota.BuscarMascotasPorTipo(tipoMascotaNoExistente.getId());
        assertThat(mascotas.size()).isEqualTo(0);
    }

    private TipoMascota existeUnaMascotaDeTipoMascota()
    {
        TipoMascota tipoMascota = new TipoMascota();
        tipoMascota.setNombre("Perro");
        this.repositorioTipoMascota.Guardar(tipoMascota);
        return tipoMascota;
    }

    private TipoRaza existeUnTipoRaza(TipoMascota tipoMascota)
    {
        TipoRaza tipoRaza = new TipoRaza();
        tipoRaza.setNombre("Mestizo");
        tipoRaza.setTipoMascota(tipoMascota);
        this.repositorioTipoRaza.Guardar(tipoRaza);
        return tipoRaza;
    }
    private Mascota datoQueExisteUnaMascota(TipoRaza tipoRaza)
    {
        Mascota mascota = new Mascota();
        mascota.setTipoRaza(tipoRaza);
        mascota.setNombre("Zeus");
        repositorioMascota.Guardar(mascota);
        return mascota;
    }
}

