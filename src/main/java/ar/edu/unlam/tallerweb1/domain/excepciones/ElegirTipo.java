package ar.edu.unlam.tallerweb1.domain.excepciones;

public class ElegirTipo extends RuntimeException{
    public ElegirTipo(){
        super("Por favor, elegir un tipo de mascota");
    }
}
