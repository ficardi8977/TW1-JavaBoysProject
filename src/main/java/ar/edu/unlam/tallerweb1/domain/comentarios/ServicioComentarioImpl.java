package ar.edu.unlam.tallerweb1.domain.comentarios;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.UsuarioExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.EncontrarMascotaExcepcion;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ServicioComentarioImpl implements ServicioComentario
{

    private RepositorioComentario repositorioComentario;
    private ServicioCuidado servicioCuidado;

    private ServicioMascota servicioMascota;
    private ServicioUsuario servicioUsuario;
    @Autowired
    public ServicioComentarioImpl(RepositorioComentario repositorioComentario, ServicioMascota servicioMascota,ServicioCuidado servicioCuidado, ServicioUsuario servicioUsuario)
    {
        this.repositorioComentario = repositorioComentario;
        this.servicioCuidado = servicioCuidado;
        this.servicioMascota= servicioMascota;
        this.servicioUsuario = servicioUsuario;
    }

    @Override
    public int guardar(DatosComentario request) {
        Comentario comentario = new Comentario();

        comentario.setClasificacion(request.getClasificacion());
        comentario.setMensaje(request.getMensaje());
        comentario.setFecha(Date.from(Instant.now()));

        //obtener cuidador
        var cuidado = this.servicioCuidado.ObtenerDetalle(request.getIdCuidado());
        if(cuidado == null)
        {
            // excepcion al no encontrar cuidado
        }
        comentario.setCuidado(cuidado);

        //obtener cuidador
        var usuario = this.servicioUsuario.consultarUsuario(request.getIdUsuario());
        if(usuario == null)
        {
            // excepcion al no encontrar usuario
        }
        comentario.setUsuario(usuario);
        var idComentario = this.repositorioComentario.guardar(comentario);

        return idComentario;
    }

    @Override
    public int guardarMascotas(DatosComentario request) {
        Comentario comentario = new Comentario();

        comentario.setClasificacion(request.getClasificacion());
        comentario.setMensaje(request.getMensaje());
        comentario.setFecha(Date.from(Instant.now()));

        //obtener mascota
        var mascota = this.servicioMascota.ObtenerDetalle(request.getIdMascota());
        if(mascota == null)
        {
            throw new EncontrarMascotaExcepcion();
        }
        comentario.setMascota(mascota);

        //obtener usuario
        var usuario = this.servicioUsuario.consultarUsuario(request.getIdUsuario());
        if(usuario == null)
        {
            throw new UsuarioExcepcion();
        }
        comentario.setUsuario(usuario);
        var idComentario = this.repositorioComentario.guardar(comentario);

        return idComentario;
    }
}
