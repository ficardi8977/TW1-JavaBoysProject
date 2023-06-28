package ar.edu.unlam.tallerweb1.domain.excepciones;

public class AlgoritmoNoDisponible extends RuntimeException{
    public AlgoritmoNoDisponible(){
        super("Algoritmo de cifrado no disponible");
    }
}
