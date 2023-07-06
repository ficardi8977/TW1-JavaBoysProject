package ar.edu.unlam.tallerweb1.delivery;

public class DatosComentario {
    private int clasificacion;
    private String mensaje;
    private Long idUsuario;
    private Long idCuidado;
    private Long idMascota;

    private Long idComentarioPadre;

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCuidado(Long idCuidado) {
        this.idCuidado = idCuidado;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public Long getIdComentarioPadre() {
        return idComentarioPadre;
    }

    public void setIdComentarioPadre(Long idComentarioPadre) {
        this.idComentarioPadre = idComentarioPadre;
    }

    public Long getIdCuidado() {
        return idCuidado;
    }

    public Long getIdMascota() {
        return idMascota;
    }
}
