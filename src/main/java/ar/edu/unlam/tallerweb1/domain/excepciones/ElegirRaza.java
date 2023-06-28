package ar.edu.unlam.tallerweb1.domain.excepciones;

public class ElegirRaza extends RuntimeException{
    public ElegirRaza(){
        super("Selecciona la raza de tu mascota");
    }
}
