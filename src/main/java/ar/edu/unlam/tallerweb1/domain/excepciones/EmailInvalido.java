package ar.edu.unlam.tallerweb1.domain.excepciones;

public class EmailInvalido extends RuntimeException{
    public EmailInvalido(){
        super("Formato de email invalido");
    }
}
