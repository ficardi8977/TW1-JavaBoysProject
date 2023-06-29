package ar.edu.unlam.tallerweb1.domain.excepciones;

public class NombreInvalido extends RuntimeException{
    public NombreInvalido(){
        super("Por favor, ingrese un nombre");
    }
}
