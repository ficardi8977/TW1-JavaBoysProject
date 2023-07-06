package ar.edu.unlam.tallerweb1.domain.excepciones;

public class CodificacionNoDisponible extends RuntimeException{
    public CodificacionNoDisponible(){
        super("Codificación de caracteres no compatible");
    }
}
