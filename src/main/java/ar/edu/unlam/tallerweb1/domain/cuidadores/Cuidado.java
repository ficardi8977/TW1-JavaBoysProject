package ar.edu.unlam.tallerweb1.domain.cuidadores;

import javax.persistence.*;

@Entity
public class Cuidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private  String  nombre;
    @Column(length = 50,nullable = false)
    private  String email;
    private String direccion;
    @Column(length = 50)
    private String telefono;
    @Column(length = 50)
    private String latitud;
    @Column(length = 50)
    private String longitud;
    private String imagen;
    @Column(length = 100)
    private String cbu;

    public Cuidado (){}

    public Cuidado (String nombre, String email, String direccion, String telefono, String latitud, String longitud, TipoCuidado tipocuidado){
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipocuidado = tipocuidado;
    }


            // ('Tomas', 'tomas@gmail.com', 'UNLaM', '12345', '0', '0', 2)


    // Propiedad de la FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoCuidado")
    private TipoCuidado tipocuidado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public TipoCuidado getTipocuidado() {
        return tipocuidado;
    }

    public void setTipocuidado(TipoCuidado tipocuidado) {
        this.tipocuidado = tipocuidado;
    }
}
