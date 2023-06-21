package ar.edu.unlam.tallerweb1.delivery;

public class DatosComentario {
    private int clasificacion;
    private String mensaje;
    private int idUsuario;

    private Integer idCuidado;

    private Integer idMascota;

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getIdCuidado() {
        return idCuidado;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setIdCuidado(int idCuidado) {
        this.idCuidado = idCuidado;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
