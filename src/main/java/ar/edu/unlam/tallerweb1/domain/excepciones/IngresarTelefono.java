package ar.edu.unlam.tallerweb1.domain.excepciones;

public class IngresarTelefono extends RuntimeException{
    public IngresarTelefono(){
        super("Por favor, ingrese un telefono valido");
    }
}
