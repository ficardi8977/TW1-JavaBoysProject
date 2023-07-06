package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, latitud, longitud, imagen, descripcion;
    private Date fechaAdopcion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="vacunas_mascota",
            joinColumns = {@JoinColumn(name = "idMascota")},
            inverseJoinColumns = {@JoinColumn(name = "idVacuna")} )
    private List<Vacunacion> vacunas = new ArrayList<Vacunacion>();

    public List<Vacunacion> getVacunas() {
        return vacunas;
    }

    public void setVacunas(Vacunacion vacunas) {
        this.vacunas.add(vacunas);
    }

    // Propiedad de la FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdTipoRaza")
    private TipoRaza tipoRaza;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado")
    private Estado estado;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comentario> comentarios;

    public void setVacunas(List<Vacunacion> vacunas) {
        this.vacunas = vacunas;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoRaza getTipoRaza() {
        return tipoRaza;
    }

    public void setTipoRaza(TipoRaza tipoRaza) {
        this.tipoRaza = tipoRaza;
    }
    private int idUsuario;


    public Mascota() {

    }
    public Long getId() {
        return id;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public Date getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(Date fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion( String descripcion) {
        this.descripcion = descripcion;
    }
}
