package ar.edu.unlam.tallerweb1.delivery;

public class DatosRegistracion {
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String latitud;
    private String longitud;
    private String base64Image;

    public String getBase64Image() {
        return base64Image;
    }

    public void setImagenBase64(String base64Image) {
        this.base64Image = base64Image;
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

    public DatosRegistracion(){}
    public DatosRegistracion(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public DatosRegistracion(String nombre, String apellido, String email, String password, String telefono, String latitud, String longitud) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
