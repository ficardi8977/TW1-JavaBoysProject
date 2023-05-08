package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Maneja las solicitudes HTTP
@RestController
@RequestMapping("/mascotas")
public class ControladorMascota {
        @Autowired
        private RepositorioMascotas petRepository;

        @GetMapping
        public List<Mascota> getMascotasPorEstado(@RequestParam Integer idEstado) {
            return petRepository.mascotasPorEstado(idEstado);
        }
    }

