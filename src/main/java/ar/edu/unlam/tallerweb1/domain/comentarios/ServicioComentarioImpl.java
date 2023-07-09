package ar.edu.unlam.tallerweb1.domain.comentarios;

import ar.edu.unlam.tallerweb1.delivery.DTOComentario;
import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.ServicioCuidado;
import ar.edu.unlam.tallerweb1.domain.excepciones.ComentarioInexistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.CuidadoNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.PermisosExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.UsuarioNoExistenteExcepcion;
import ar.edu.unlam.tallerweb1.domain.excepciones.mascotas.EncontrarMascotaExcepcion;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        if(request.getIdComentarioPadre() != null)
        {
            comentario.setComentarioPadre(this.repositorioComentario.obtener(request.getIdComentarioPadre()));
        }

        var cuidado = this.servicioCuidado.ObtenerDetalle(request.getIdCuidado());
        existeCuidado(cuidado);
        comentario.setCuidado(cuidado);

        var usuario = this.servicioUsuario.consultarUsuario(request.getIdUsuario());
        esUsuarioValidoParaComentario(usuario);
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

        var mascota = this.servicioMascota.ObtenerDetalle(request.getIdMascota());
        existeMascota(mascota);
        comentario.setMascota(mascota);

        var usuario = this.servicioUsuario.consultarUsuario(request.getIdUsuario());
        esUsuarioValidoParaComentario(usuario);
        comentario.setUsuario(usuario);

        var idComentario = this.repositorioComentario.guardar(comentario);

        return idComentario;
    }

    @Override
    public void eliminar(long id, long idUsuario) {

        var usuario = this.servicioUsuario.consultarUsuario(idUsuario);
        esUsuarioValidoParaComentario(usuario);
        esUsuarioValidoParaBorrarComentario(usuario);

        var comentario = this.repositorioComentario.obtener(id);
        existeComentario(comentario);

        this.repositorioComentario.eliminar(comentario);
    }

    @Override
    public List<DTOComentario> obtenerPorIdCuidado(long idCuidado) {

        var comentarios = this.repositorioComentario.obtenerPorIdCuidado(idCuidado);

        List<DTOComentario> listaDTO = new ArrayList<>();

        if (!comentarios.isEmpty())
        {
             listaDTO = this.construirComentariosDTO(comentarios);
        }
        return listaDTO;
    }

    private void existeComentario(Comentario comentario) {
        if(comentario == null)
        {
            throw new ComentarioInexistenteExcepcion();
        }
    }

    private void esUsuarioValidoParaComentario(Usuario usuario)
    {
        if(usuario == null)
        {
            throw new UsuarioNoExistenteExcepcion();
        }
    }

    private void esUsuarioValidoParaBorrarComentario(Usuario usuario)
    {
        if(!usuario.getTipoUsuario().getNombre().equals("Administrador"))
        {
            throw new PermisosExcepcion();
        }
    }
    private void existeCuidado(Cuidado cuidado)
    {
        if(cuidado == null)
        {
            throw new CuidadoNoExistenteExcepcion();
        }
    }
    private void existeMascota(Mascota mascota)
    {
        if(mascota == null)
        {
            throw new EncontrarMascotaExcepcion();
        }
    }

    private List<DTOComentario> construirComentariosDTO(List<Comentario> comentarios) {
        List<DTOComentario> comentariosDTO = new ArrayList<>();

        for (Comentario comentario : comentarios) {
            DTOComentario comentarioDTO = new DTOComentario();
            comentarioDTO.setId(comentario.getId());
            comentarioDTO.setClasificacion(comentario.getClasificacion());
            comentarioDTO.setMensaje(comentario.getMensaje());
            comentarioDTO.setIdUsuario(comentario.getUsuario().getId());
            if(comentario.getMascota() != null){
                comentarioDTO.setIdMascota(comentario.getMascota().getId());
            }
            if(comentario.getCuidado() != null){
                comentarioDTO.setIdCuidado(comentario.getCuidado().getId());
            }
            comentarioDTO.setFecha(comentario.getFecha());

            Comentario comentarioPadre = comentario.getComentarioPadre();
            if (comentarioPadre != null) {
                for (DTOComentario elemento : comentariosDTO) {
                    if (elemento.getId() == comentarioPadre.getId()) {
                        var subcomentarios = elemento.getSubComentarios();
                        if(subcomentarios == null) {
                            subcomentarios = new ArrayList<DTOComentario>();
                        }
                        subcomentarios.add(comentarioDTO);
                        elemento.setSubComentarios(subcomentarios);
                        break;
                    }
                }
            }else {
                comentariosDTO.add(comentarioDTO);
            }
        }

        return comentariosDTO;
    }
}
