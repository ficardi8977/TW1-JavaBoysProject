package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.delivery.DatosUbicacion;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirRaza;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirTipo;
import ar.edu.unlam.tallerweb1.domain.excepciones.NombreInvalido;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServicioMascotaImpl implements ServicioMascota {

    private RepositorioMascota repositorioMascota;
    @Autowired
    private ServletContext servletContext;

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
    public Boolean registrarMascota(DatosMascotas datosMascotas) {
        validarDatos(datosMascotas);
        return this.repositorioMascota.registrarMascota(datosMascotas);
    }
/*
    @Override
    public String registrarImagen(MultipartFile img) throws IOException {
        // String rutaImagen = "C:\\Taller Web\\TW1-JavaBoysProject\\src\\main\\resources\\imgs\\" + img.getOriginalFilename();
        String directorioImagenes = servletContext.getRealPath("/img/");
        String rutaImagen = directorioImagenes + img.getOriginalFilename();
        byte[] bytes = img.getBytes();
        Path path = Paths.get(rutaImagen);
        Files.write(path, bytes);

        return img.getOriginalFilename();
    }

 */

    @Override
    public String registrarImagen(MultipartFile img) throws IOException {
        String directorioImagenes = servletContext.getRealPath("/img/");
        String nombreOriginal = img.getOriginalFilename();
        String extension = obtenerExtension(nombreOriginal);
        String nombreUnico = generarNombreUnico(nombreOriginal, extension);
        String rutaImagen = directorioImagenes + nombreUnico;
        byte[] bytes = img.getBytes();
        Path path = Paths.get(rutaImagen);
        Files.write(path, bytes);

        return nombreUnico;
    }

    private String obtenerExtension(String nombreArchivo) {
        int ultimoPunto = nombreArchivo.lastIndexOf(".");
        if (ultimoPunto != -1) {
            return nombreArchivo.substring(ultimoPunto);
        }
        return "";
    }

    private String generarNombreUnico(String nombreOriginal, String extension) {
        String uuid = UUID.randomUUID().toString();
        return nombreOriginal + "_" + uuid + extension;
    }

    @Override
    public Boolean validarDatos(DatosMascotas datosMascotas){
        Boolean datosValidos = false;
        String nombre = datosMascotas.getNombre();
        Long tipo = datosMascotas.getTipo();
        String raza = datosMascotas.getRaza();
        Boolean nombreValido = !nombre.equals("");
        Boolean tipoValido = tipo != 0 && tipo != null;
        Boolean razaValida = raza != null && !raza.equals("0");
        if(!nombreValido)
            throw new NombreInvalido();
        if(!tipoValido)
            throw new ElegirTipo();
        if(!razaValida)
            throw new ElegirRaza();
        if(nombreValido && tipoValido && razaValida)
            datosValidos = true;
        return datosValidos;
    }

    @Override
    public void registrarVacuna(String nuevaVacuna, Long idMascota) {
        this.repositorioMascota.registrarVacuna(nuevaVacuna, idMascota);
    }

    @Override
    public void eliminarVacuna(Long idVacuna, Long idMascota) {
        this.repositorioMascota.eliminarVacuna(idVacuna, idMascota);
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

        for (Mascota mascota : mascotasFiltradas) {
            mascota.setComentarios(null);
        }



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