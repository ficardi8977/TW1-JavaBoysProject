package ar.edu.unlam.tallerweb1.domain.excepciones.mascotas;

public class EncontrarMascotaExcepcion extends  RuntimeException{

    public EncontrarMascotaExcepcion(){
        super("No se encontro la mascota");
    }
}
