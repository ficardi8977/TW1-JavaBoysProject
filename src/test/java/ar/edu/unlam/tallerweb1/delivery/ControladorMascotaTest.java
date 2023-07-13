package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.excepciones.NoSeRegistroLaMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ControladorMascotaTest {

    private ControladorMascotas controladorMascotas;
    private ServicioMascota servicioMascota;
    private DatosMascotas datos;
    private DatosMascotas datosInvalidos;
    private RedirectAttributes redirectAttributes;
    private MultipartFile img;

    @Before
    public void init(){
        this.servicioMascota = mock(ServicioMascota.class);
        controladorMascotas = new ControladorMascotas(this.servicioMascota);
        this.datosInvalidos = new DatosMascotas();
        this.redirectAttributes = new RedirectAttributesModelMap();
        this.img = mock(MultipartFile.class);
        this.datos = new DatosMascotas();
        datos.setIdUsuario(1l);
        when(this.servicioMascota.validarDatos(datos)).thenReturn(true);
        when(this.servicioMascota.registrarMascota(datos)).thenReturn(true);
        when(this.servicioMascota.validarDatos(datosInvalidos)).thenReturn(false);
        when(this.servicioMascota.registrarMascota(datosInvalidos)).thenThrow(new NoSeRegistroLaMascota());
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

    @Test
    public void cuandoQuieroRegistrarUnaMascotaQueMeLleveAlFormulario() {
        ModelAndView mav = quieroRegistrarMiMascota();
        assertThat(mav.getViewName()).isEqualTo("registrar-mascota-mdq");
    }

    @Test
    public void cuandoRegistroMiMascotaMeDevuelveALaVistaMisMascotas() {
        ModelAndView mav = registroMiMascota(datos, img, redirectAttributes);
        Object view = mav.getView();
        String url = ((RedirectView) view).getUrl();

        assertThat(view.getClass()).isEqualTo(RedirectView.class);
        assertThat(url).isEqualTo("/mascotas/mis-mascotas?idUsuario=1");
    }

    @Test
    public void cuandoRegistroMiMascotaMeDevuelveUnMensajeExitoso() {
        ModelAndView mav = registroMiMascota(datos, img, redirectAttributes);
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        String mensaje = (String) flashAttributes.get("error");

        assertThat(mensaje).isEqualTo("Mascota registrada!");
    }

    @Test
    public void cuandoNoLogroRegistrarMiMascotaMeDevuelveAlFormConMensaje() {
        ModelAndView mav = registroMiMascota(datosInvalidos, img, redirectAttributes);

        assertThat(mav.getViewName()).isEqualTo("registrar-mascota-mdq");
        assertThat(mav.getModel().get("error")).isEqualTo("No se pudo registrar a la mascota");
    }

    private ModelAndView registroMiMascota(DatosMascotas datos, MultipartFile img, RedirectAttributes redirectAttributes) {
        return this.controladorMascotas.altaMascota(datos, img, redirectAttributes);
    }


    private ModelAndView quieroRegistrarMiMascota() {
        return this.controladorMascotas.registrarMascota();
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
        assertThat(mav.getViewName()).isEqualTo("todas-las-mascotas-mdq");
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
