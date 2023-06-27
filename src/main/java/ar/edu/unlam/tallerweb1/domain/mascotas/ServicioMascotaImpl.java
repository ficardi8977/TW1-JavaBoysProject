package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.delivery.DatosUbicacion;
import ar.edu.unlam.tallerweb1.domain.estado.ServicioEstado;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioMascotaImpl implements ServicioMascota {

    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImpl(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota) {
        return repositorioMascota.BuscarMascotasPorTipo(idTipoMascota);
    }

    @Override
    public List<Mascota> ObtenerTodasLasMascotas() {
        return repositorioMascota.TodasLasMascotas();
    }


    @Override
    public Mascota ObtenerDetalle(long id) {
        return repositorioMascota.BuscarDetalle(id);
    }

    public List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario) {
        return this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuario);
    }

    @Override
    public List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request) {
        return this.repositorioMascota.ObtenerMascotasFiltradas(request);
    }

    @Override
    public List<Mascota> obtenerMascotasPorEstados(String[] estados){
        return this.repositorioMascota.buscarMascotasPorEstados(estados);
    }

    @Override
    public List<Mascota> obtenerMascotasCercanas(DatosMascotasFiltradas request) {
        EstadoMascotasEnum perdido = EstadoMascotasEnum.Perdido;
        EstadoMascotasEnum adopcion = EstadoMascotasEnum.EnAdopcion;
        DatosUbicacion ubicacion;
        List<Mascota> mascotasCercanas = new ArrayList<>();
        String[] estados = {perdido.name(), adopcion.name()};

        ubicacion = request.getUbicacion();
        List<Mascota> mascotasFiltradas = obtenerMascotasPorEstados(estados);

        if (ubicacion != null) {
            double latitud = Double.parseDouble(ubicacion.getLatitud());
            double longitud = Double.parseDouble(ubicacion.getLongitud());
            double radio = ubicacion.getRadio();

            mascotasCercanas = mascotasFiltradas.stream()
                    .filter(mascota -> esCercana(mascota, latitud, longitud, radio))
                    .collect(Collectors.toList());
        }

        return mascotasCercanas;
    }

    private boolean esCercana(Mascota mascota, double latitud, double longitud, double radio) {
        double distancia = calcularDistancia(latitud, longitud, Double.parseDouble(mascota.getLatitud()), Double.parseDouble(mascota.getLongitud()));
        return distancia <= radio;
    }

    private double calcularDistancia(double latitudOrigen, double longitudOrigen, double latitudDestino, double longitudDestino) {
        // Radio de la Tierra en kilómetros
        double radioTierra = 6371.0;

        // Convertir las coordenadas a radianes
        double latitudOrigenRad = Math.toRadians(latitudOrigen);
        double longitudOrigenRad = Math.toRadians(longitudOrigen);
        double latitudDestinoRad = Math.toRadians(latitudDestino);
        double longitudDestinoRad = Math.toRadians(longitudDestino);

        // Diferencia de latitudes y longitudes
        double diferenciaLatitudes = latitudDestinoRad - latitudOrigenRad;
        double diferenciaLongitudes = longitudDestinoRad - longitudOrigenRad;

        // Fórmula del haversine
        double a = Math.sin(diferenciaLatitudes / 2) * Math.sin(diferenciaLatitudes / 2) +
                Math.cos(latitudOrigenRad) * Math.cos(latitudDestinoRad) *
                        Math.sin(diferenciaLongitudes / 2) * Math.sin(diferenciaLongitudes / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;

        return distancia;
    }

}