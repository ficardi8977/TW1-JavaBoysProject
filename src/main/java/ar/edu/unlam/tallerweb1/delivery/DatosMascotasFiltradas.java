package ar.edu.unlam.tallerweb1.delivery;

public class DatosMascotasFiltradas {
    private long idTipoMascota, idEstado;
    private DatosUbicacion ubicacion;

    public DatosUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(DatosUbicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public DatosMascotasFiltradas(){}
    public DatosMascotasFiltradas(long idTipoMascota, long idEstado){

        this.idTipoMascota = idTipoMascota;
        this.idEstado = idEstado;
    }


    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdTipoMascota(long idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public long getIdTipoMascota() {
        return idTipoMascota;
    }


}
