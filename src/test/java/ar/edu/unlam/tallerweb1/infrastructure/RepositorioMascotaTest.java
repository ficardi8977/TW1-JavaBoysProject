package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;
import java.util.List;

public class RepositorioMascotaTest extends SpringTest {

    @Autowired
    private RepositorioMascota repositorioM;

    @Autowired
    private RepositorioUsuario repositorioU;

    private Long idUsuario;
    private Mascota mascota;


    @Before
    public void init(){
        this.idUsuario = dadoQueExisteUsuario();
        this.mascota = dadoQueExisteMascota(idUsuario);
    }


    @Test
    @Transactional
    @Rollback
    public void obtenerMascotasPorIdUsuario(){

        List<Mascota> mascotasEncontradas = buscoLasMascotasPorIdUsuario(idUsuario);

        entoncesLasEncuentro(mascotasEncontradas);

    }

    @Test
    @Transactional
    @Rollback
    public void ObtenerMasMascotasPorIdUsuario(){

        Mascota mascota2 = dadoQueExisteMascota(idUsuario);

        List<Mascota> mascotasEncontradas = buscoLasMascotasPorIdUsuario(idUsuario);

        entoncesEncuentroLasDos(mascotasEncontradas);
    }

    @Test
    @Transactional
    @Rollback
    public void NoObtenerNingunaMascota(){

        Long usuarioSinMascotas = dadoQueExisteUsuario();

        List<Mascota> mascotasEncontradas = buscoLasMascotasPorIdUsuario(usuarioSinMascotas);

        entoncesNoEncuentroNinguna(mascotasEncontradas);

    }

    private void entoncesNoEncuentroNinguna(List<Mascota> mascotasEncontradas) {
        assertThat(mascotasEncontradas).isNotNull();
        assertThat(mascotasEncontradas.size()).isEqualTo(0);
    }

    private void entoncesEncuentroLasDos(List<Mascota> mascotasEncontradas) {
        assertThat(mascotasEncontradas).isNotNull();
        assertThat(mascotasEncontradas.size()).isEqualTo(2);
    }

    private void entoncesLasEncuentro(List<Mascota> mascotasEncontradas) {
        assertThat(mascotasEncontradas).isNotNull();
        assertThat(mascotasEncontradas.size()).isEqualTo(1);
    }

    private List<Mascota> buscoLasMascotasPorIdUsuario(Long idUsuario) {
        return this.repositorioM.buscarMascotasPorIdUsuario(idUsuario);
    }

    private Mascota dadoQueExisteMascota(Long idUsuario) {
        Mascota mascota = new Mascota();
        mascota.setIdUsuario(idUsuario);
        this.repositorioM.guardar(mascota);
        return mascota;
    }

    private Long dadoQueExisteUsuario() {
        Usuario user = new Usuario();
        this.repositorioU.guardar(user);
        return user.getId();
    }
}
