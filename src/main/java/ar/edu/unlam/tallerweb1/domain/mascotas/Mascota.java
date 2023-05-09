package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer idEstado;
    private String nombre, latitud, longitud, imagen;
    private Date fechaAdopcion;


    // Propiedad de la FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoRaza")
    private TipoRaza tipoRaza;

    public TipoRaza getTipoRaza() {
        return tipoRaza;
    }

    public void setTipoRaza(TipoRaza tipoRaza) {
        this.tipoRaza = tipoRaza;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String id) {
        this.nombre = nombre;
    }
}
