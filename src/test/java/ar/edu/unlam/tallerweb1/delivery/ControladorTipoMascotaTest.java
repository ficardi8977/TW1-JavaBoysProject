package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.ServicioTipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ControladorTipoMascotaTest {

    private ControladorTipoMascota controladorTipoMascota;
    private ServicioTipoMascota servicioTipoMascota;

    @Before
    public void init(){
        this.servicioTipoMascota = mock(ServicioTipoMascota.class);
        controladorTipoMascota = new ControladorTipoMascota(this.servicioTipoMascota);
    }
    @Test
    public void Listar_Ok()
    {
        // configurar
        int idTipoMascota = 1;
        this.datoQueExisteTipoMacota(idTipoMascota);
        // ejecutar
        var result = this.controladorTipoMascota.Listar();
        // verificar
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().size()).isEqualTo(1);
    }
    @Test
    public void Listar_SinDatos()
    {
        // configurar
        int idTipoMascota = 2;
        this.datoQueNoExisteTipoMacota(idTipoMascota);
        // ejecutar
        var result = this.controladorTipoMascota.Listar();
        // verificar
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().size()).isEqualTo(0);
    }

    private void datoQueExisteTipoMacota(long idTipoMascota) {

        List<TipoMascota> listTipoMascota = new ArrayList<>();
        TipoMascota tipoMascota = new TipoMascota();
        tipoMascota.setId(idTipoMascota);
        listTipoMascota.add(tipoMascota);
        when(this.servicioTipoMascota.Listar()).thenReturn(listTipoMascota);
    }

    private void datoQueNoExisteTipoMacota(long idTipoMascota) {

        List<TipoMascota> listTipoMascota = new ArrayList<>();
        when(this.servicioTipoMascota.Listar()).thenReturn(listTipoMascota);
    }
}
