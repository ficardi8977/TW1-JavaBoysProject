package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirRaza;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirTipo;
import ar.edu.unlam.tallerweb1.domain.excepciones.NombreInvalido;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascotaImpl;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascotaImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioMascotaTest {

    private ServicioMascota servicioMascota;
    private RepositorioMascota repositorioMascota;

    private List<Mascota> mascotas;
    private DatosMascotas formulario;

    private int idUsuarioExiste = 1;
    private int idUsuarioNoExiste = 2;


    @Before
    public void init(){
        this.repositorioMascota = mock(RepositorioMascotaImpl.class);
        this.servicioMascota = new ServicioMascotaImpl(this.repositorioMascota);
        this.mascotas = new ArrayList<>();
        Mascota mascota = new Mascota();
        mascota.setVacunas(new Vacunacion());
        Mascota mascota2 = new Mascota();
        mascotas.add(mascota);
        mascotas.add(mascota2);
        formulario = seLlenaUnForm();
        when(this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuarioExiste)).thenReturn(mascotas);
        when(this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuarioNoExiste)).thenReturn(null);
        when(this.repositorioMascota.registrarMascota(formulario)).thenReturn(true);
        int idUsuario = 1;

    }

    @Test
    public void buscarMascotasPorIdUsuario(){
        int idUsuario = 1;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuarioExiste);
        assertThat(mascotas.size()).isEqualTo(2);
    }


    @Test
    public void noMeTraeMascotasSiElUsuarioNoTieneMascotas(){
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuarioNoExiste);
        assertThat(mascotas).isNull();
    }

    @Test
    public void NoBuscarMascotasPorIdUsuario(){
        int idUsuario = 5;
        List<Mascota> mascotas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario);
        assertThat(mascotas.size()).isEqualTo(0);
    }

    @Test
    public void queMeTraigaLasVacunas(){
        int idUsuario = 1;
        List <Vacunacion> vacunas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario).get(0).getVacunas();
        assertThat(vacunas).isNotNull();
        assertThat(vacunas.size()).isEqualTo(1);
    }
    @Test
    public void queMeRegistreUnaMascota(){
        Boolean registroLaMascota = this.servicioMascota.registrarMascota(formulario);
        entoncesSeRegistra(registroLaMascota);
    }

    @Test
    public void queNoMeTraigaLasVacunas(){
        int idUsuario = 1;
        List <Vacunacion> vacunas = this.servicioMascota.obtenerMascotaPorIdUsuario(idUsuario).get(1).getVacunas();
        assertThat(vacunas.size()).isEqualTo(0);
    }
    @Test (expected = NombreInvalido.class)
    public void queNoMeRegistreUnaMascotaNombreInvalido(){
        formulario.setNombre("");
        Boolean registroLaMascota = this.servicioMascota.registrarMascota(formulario);
        entoncesSeRegistra(registroLaMascota);
    }
    private void entoncesSeRegistra(Boolean registroLaMascota) {
        assertThat(registroLaMascota).isTrue();
    }

    @Test (expected = ElegirTipo.class)
    public void queNoMeRegistreUnaMascotaTipoInvalido(){
        formulario.setTipo(0l); // En el formulario de registrar mascota es el "seleccionar tipo"
        Boolean registroLaMascota = this.servicioMascota.registrarMascota(formulario);
        entoncesSeRegistra(registroLaMascota);
    }

    @Test (expected = ElegirRaza.class)
    public void queNoMeRegistreUnaMascotaRazaInvalida(){
        formulario.setRaza(null);
        Boolean registroLaMascota = this.servicioMascota.registrarMascota(formulario);
        entoncesSeRegistra(registroLaMascota);
    }

    private DatosMascotas seLlenaUnForm() {
        DatosMascotas datos = new DatosMascotas();
        datos.setNombre("Pancho");
        datos.setRaza("Labrador");
        datos.setDescripcion("");
        datos.setTipo(1l);
        datos.setEstado(1l);
        datos.setIdUsuario(1);
        datos.setLatitud("0");
        datos.setLongitud("0");

        return datos;
    }
}
