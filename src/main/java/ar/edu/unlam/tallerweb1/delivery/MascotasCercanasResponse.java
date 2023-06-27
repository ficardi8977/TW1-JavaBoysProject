package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

import java.util.List;

public class MascotasCercanasResponse {
    private List<Mascota> mascotasPerdidas;
    private List<Mascota> mascotasAdopcion;

    public MascotasCercanasResponse(List<Mascota> mascotasPerdidas, List<Mascota> mascotasAdopcion) {
        this.mascotasPerdidas = mascotasPerdidas;
        this.mascotasAdopcion = mascotasAdopcion;
    }

    public MascotasCercanasResponse() {

    }

    public List<Mascota> getMascotasPerdidas() {
        return mascotasPerdidas;
    }

    public List<Mascota> getMascotasAdopcion() {
        return mascotasAdopcion;
    }

    public void setMascotasPerdidas(List<Mascota> mascotasPerdidas) {
        this.mascotasPerdidas = mascotasPerdidas;
    }

    public void setMascotasAdopcion(List<Mascota> mascotasAdopcion){
        this.mascotasAdopcion = mascotasAdopcion;
    }
}
