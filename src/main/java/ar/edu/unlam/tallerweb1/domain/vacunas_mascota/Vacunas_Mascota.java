package ar.edu.unlam.tallerweb1.domain.vacunas_mascota;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Vacunas_Mascota implements Serializable {
    @Id
    private Long idVacuna;
    @Id
    private Long idMascota;

    public Long getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(Long idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }
}
