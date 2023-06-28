package ar.edu.unlam.tallerweb1.domain.excepciones;

public class PasswordInvalida extends RuntimeException{
    public PasswordInvalida(){
        super("Formato de clave invalida");
    }
}
