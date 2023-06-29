package ar.edu.unlam.tallerweb1.delivery;

public class DatosComentario {
    private int clasificacion;
    private String mensaje;
    private int idUsuario;
    private Integer idCuidado;
    private Integer idMascota;

    private Integer idComentarioPadre;

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCuidado(Integer idCuidado) {
        this.idCuidado = idCuidado;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdComentarioPadre() {
        return idComentarioPadre;
    }

    public void setIdComentarioPadre(Integer idComentarioPadre) {
        this.idComentarioPadre = idComentarioPadre;
    }
}
