package ar.edu.unlam.tallerweb1.domain.comentarios;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ServicioComentarioImpl implements ServicioComentario
{

    private RepositorioComentario repositorioComentario;
    private ServicioCuidado servicioCuidado;
    private ServicioUsuario servicioUsuario;
    @Autowired
    public ServicioComentarioImpl(RepositorioComentario repositorioComentario, ServicioCuidado servicioCuidado, ServicioUsuario servicioUsuario)
    {
        this.repositorioComentario = repositorioComentario;
        this.servicioCuidado = servicioCuidado;
        this.servicioUsuario = servicioUsuario;
    }

    @Override
    public int guardar(DatosComentario request) {
        Comentario comentario = new Comentario();

        comentario.setClasificacion(request.getClasificacion());
        comentario.setMensaje(request.getMensaje());
        comentario.setFecha(Date.from(Instant.now()));

        if(request.getIdComentarioPadre() != null)
        {
            comentario.setComentarioPadre(this.repositorioComentario.obtener(request.getIdComentarioPadre()));
        }

        var cuidado = this.servicioCuidado.ObtenerDetalle(request.getIdCuidado());
        if(cuidado == null)
        {
            throw new CuidadoNoExistenteExcepcion();
        }
        comentario.setCuidado(cuidado);
        //obtener cuidador
        var usuario = this.servicioUsuario.consultarUsuario(request.getIdUsuario());
        if(usuario == null)
        {
            throw new UsuarioNoExistenteExcepcion();
        }
        comentario.setUsuario(usuario);
        var idComentario = this.repositorioComentario.guardar(comentario);

        return idComentario;
    }
}
