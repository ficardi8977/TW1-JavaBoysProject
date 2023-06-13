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

    public Vacunacion(String nombre) {
        this.nombre = nombre;
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


}
