package ar.edu.unlam.tallerweb1.domain.tipoRaza;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;

import javax.persistence.*;

@Entity
public class TipoRaza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoMascota")
    private TipoMascota tipoMascota;

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
