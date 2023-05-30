package ar.edu.unlam.tallerweb1.domain.cuidadores;

import javax.persistence.*;

@Entity
public class TipoCuidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(length = 100,nullable = false)
    private String nombre;

    public TipoCuidado (){}


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
