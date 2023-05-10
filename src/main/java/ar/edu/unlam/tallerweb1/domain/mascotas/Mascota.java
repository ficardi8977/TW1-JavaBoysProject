package ar.edu.unlam.tallerweb1.domain.mascotas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, latitud, longitud, descripcion, imagen;
    private Integer idRaza, idEstado;

    private Long idUsuario;
    private Date fechaAdopcion;

    public Mascota(Long id, String nombre, String latitud, String longitud, Integer idRaza, Integer idEstado, Long idUsuario, Date fechaAdopcion) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idRaza = idRaza;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
        this.fechaAdopcion = fechaAdopcion;
    }

    public Mascota() {

    }

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
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
}
