package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirRaza;
import ar.edu.unlam.tallerweb1.domain.excepciones.ElegirTipo;
import ar.edu.unlam.tallerweb1.domain.excepciones.NombreInvalido;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioMascotaImpl implements ServicioMascota {

    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImpl(RepositorioMascota repositorioMascota){
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
    public Boolean registrarMascota(DatosMascotas datosMascotas) {
        validarDatos(datosMascotas);
        return this.repositorioMascota.registrarMascota(datosMascotas);
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


}
