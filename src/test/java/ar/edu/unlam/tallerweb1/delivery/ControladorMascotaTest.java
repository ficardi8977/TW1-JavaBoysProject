package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ControladorMascotaTest {

    private ControladorMascotas controladorMascotas;
    private ServicioMascota servicioMascota;

    @Before
    public void init(){
        this.servicioMascota = mock(ServicioMascota.class);
        controladorMascotas = new ControladorMascotas(this.servicioMascota);
    }
    @Test
    public void getMascotaPorTipo_Ok()
    {
        Integer tipo = 1;
        this.dadoQueExisteMascotasDeTipo(tipo);
        ModelAndView result = this.controladorMascotas.getMascotaPorTipo(tipo);
        assertThat(result.getModel().get("mascotas")).isNotNull();
        assertThat(result.getModel().get("mascotas").toString()).isNotEqualTo("[]");
    }
    @Test
    public void getMascotaPorTipo_NotOk()
    {
        Integer tipo = 2;
        this.dadoQueNoExisteMascotasDeTipo(tipo);
        ModelAndView result = this.controladorMascotas.getMascotaPorTipo(tipo);
        assertThat(result.getModel().get("mascotas")).isNotNull();
        //pregunto por lista vacia
        assertThat(result.getModel().get("mascotas").toString()).isEqualTo("[]");
    }

    @Test
    public void getMascotaDetalle_Ok()
    {
        long id = 1;
        this.dadoQueExisteMascotas(id);
        ModelAndView result = this.controladorMascotas.getDetalle(id);
        assertThat(result.getModel().get("mascota")).isNotNull();
    }

    @Test
    public void getMascotaDetalle_NoEncontrado()
    {
        long id = 2;
        this.dadoQueNoExisteMascotas(id);
        ModelAndView result = this.controladorMascotas.getDetalle(id);
        assertThat(result.getModel().get("mascotas")).isNull();
    }
    @Test
    public void buscarMascotaPorIdUsuarioIncorrecto(){
        Long idUsuario = 1l;
        Long idUsuarioIncorrecto = 2l;
        dadoQueExisteMascota(idUsuario);
        ModelAndView mav = this.controladorMascotas.getMascotaPorIdUsuario(idUsuarioIncorrecto);
        assertThat(mav.getModel().get("mascotas").toString()).isEqualTo("[]");
    }
    @Test
    public void getMascotasFiltradas_Ok()
    {
        var request = new DatosMascotasFiltradas();
        request.setIdTipoMascota(1);
        request.setIdEstado(1);
        this.dadoQueExisteMascotasFiltradas(request);

        var result = this.controladorMascotas.getMascotasFiltradas(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().size()).isEqualTo(1);
    }

    @Test
    public void getMascotasFiltradas_NoEncontradas()
    {
        var request = new DatosMascotasFiltradas();
        request.setIdTipoMascota(1);
        request.setIdEstado(1);
        this.dadoQueNoExisteMascotasFiltradas(request);

        var result = this.controladorMascotas.getMascotasFiltradas(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNull();
    }

    private void dadoQueExisteMascota(Long idUsuario) {
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota();
        mascota.setIdUsuario(idUsuario);
        mascotas.add(mascota);
        when(this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario)).thenReturn(mascotas);
    }

    private void dadoQueExisteMascotasFiltradas(DatosMascotasFiltradas request) {
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota();
        mascotas.add(mascota);
        when(this.servicioMascota.ObtenerMascotasFiltradas(request)).thenReturn(mascotas);
    }
    private void dadoQueNoExisteMascotasFiltradas(DatosMascotasFiltradas request) {
        when(this.servicioMascota.ObtenerMascotasFiltradas(request)).thenReturn(null);
    }

    private void dadoQueExisteMascotasDeTipo(Integer tipo) {
        List<Mascota> listaDeMascotas = new ArrayList<Mascota>();
        listaDeMascotas.add(new Mascota());
        when(this.servicioMascota.ObtenerMascotasPorTipo(tipo)).thenReturn(listaDeMascotas);
    }
    private void dadoQueNoExisteMascotasDeTipo(Integer tipo) {
        List<Mascota> listaDeMascotas = new ArrayList<Mascota>();
        when(this.servicioMascota.ObtenerMascotasPorTipo(tipo)).thenReturn(listaDeMascotas);
    }

    @Test
    public void alIngresarATodasLasMascotasMeMuestraLaPantallaDeTodasLasMascotas(){
        ModelAndView mav = cuandoMeVoyATodasLasMascotas();
        entoncesMeLlevaALaPantallaDeTodasLasMascotas(mav);

    }

    private void entoncesMeLlevaALaPantallaDeTodasLasMascotas(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("todas-las-mascotas");
    }

    private ModelAndView cuandoMeVoyATodasLasMascotas() {
        return controladorMascotas.getTodasLasMascotas();
    }

    private void dadoQueExisteMascotas(long id) {
        Mascota mascota = new Mascota();
        when(this.servicioMascota.ObtenerDetalle(id)).thenReturn(mascota);
    }
    private void dadoQueNoExisteMascotas(long id) {
        when(this.servicioMascota.ObtenerDetalle(id)).thenReturn(null);
    }
    @Test
    public void buscarMascotaPorIdUsuario(){
        Long idUsuario = 1l;
        dadoQueExisteMascota(idUsuario);
        ModelAndView mav = this.controladorMascotas.getMascotaPorIdUsuario(idUsuario);
        assertThat(mav.getModel().get("mascotas")).isNotNull();
    }
}
