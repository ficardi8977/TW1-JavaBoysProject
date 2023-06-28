package ar.edu.unlam.tallerweb1.domain.excepciones;

public class EmailYaRegistrado extends RuntimeException{
    public EmailYaRegistrado(){
        super("El email ya esta registrado");
    }
}
