package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

import java.util.List;

public class DTOHome {
    private List<Mascota> perdidos;
    private List<Cuidado> refugios;
    private List<Cuidado> cuidadores;

    public List<Cuidado> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidado> cuidadores) {
        this.cuidadores = cuidadores;
    }

    public List<Cuidado> getRefugios() {
        return refugios;
    }

    public void setRefugios(List<Cuidado> refugios) {
        this.refugios = refugios;
    }

    public List<Mascota> getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(List<Mascota> perdidos) {
        this.perdidos = perdidos;
    }
}
