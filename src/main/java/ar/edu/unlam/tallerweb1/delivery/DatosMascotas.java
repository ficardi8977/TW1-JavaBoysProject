package ar.edu.unlam.tallerweb1.delivery;

public class DatosMascotas {

    private String nombre;
    private String raza;
    private String descripcion;
    private String latitud;
    private String longitud;
    private Long estado;

    private String imagen;

    private Long tipo;
    private int idUsuario;

    public DatosMascotas(String nombre, String descripcion, String imagen, String raza, Long estado, Long tipo, int idUsuario){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.raza = raza;
        this.estado = estado;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
    }

    public DatosMascotas(){}

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

