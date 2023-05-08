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
    private  Integer idTipoRaza, idEstado;
    private String nombre, latitud, longitud;
    private Date fechaAdopcion;

    public Mascota(){

    }

    public Mascota(String nombre, Integer idTipoRaza, String latitud, String longitud, Date fechaAdopcion, Integer idEstado){
        this.nombre=nombre;
        this.idTipoRaza=idTipoRaza;
        this.latitud=latitud;
        this.longitud=longitud;
        this.fechaAdopcion=fechaAdopcion;
        this.idEstado=idEstado;
    }

}
