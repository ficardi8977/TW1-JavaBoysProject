package ar.edu.unlam.tallerweb1.domain.vacunas;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Vacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long idMascota;
    private Date fecha;

    public Vacunacion(String nombre, Long idMascota, Date fecha) {
        this.nombre = nombre;
        this.idMascota = idMascota;
        this.fecha = fecha;
    }

    public Vacunacion(){}

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

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
