package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTOComentario {

    private long id;

    private int clasificacion;

    private String mensaje;

    private Long idUsuario;

    private Long idCuidado;

    private Long idMascota;

    private String nombreUsuario;

    private String imagenUsuario;

    private ArrayList<DTOComentario> subComentarios;

    private Date fecha;

    public void setId(long id) {
        this.id = id;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public void setIdCuidado(Long idCuidado) {
        this.idCuidado = idCuidado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setSubComentarios(ArrayList<DTOComentario> subComentarios) {
        this.subComentarios = subComentarios;
    }

    public ArrayList<DTOComentario> getSubComentarios() {
        return subComentarios;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getIdCuidado() {
        return idCuidado;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        imagenUsuario = imagenUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        nombreUsuario = nombreUsuario;
    }

    public DTOComentario(Comentario comentario){
        this.id = comentario.getId();
        if(comentario.getCuidado() != null) {
            this.idCuidado = comentario.getCuidado().getId();
        }
        if(comentario.getMascota() != null) {
            this.idMascota = comentario.getMascota().getId();
        }
        this.clasificacion = comentario.getClasificacion();
        this.idUsuario = comentario.getUsuario().getId();
        this.nombreUsuario = comentario.getUsuario().getNombre();
        this.imagenUsuario = comentario.getUsuario().getImagen();
        this.mensaje = comentario.getMensaje();
        this.fecha = comentario.getFecha();
    }
}
