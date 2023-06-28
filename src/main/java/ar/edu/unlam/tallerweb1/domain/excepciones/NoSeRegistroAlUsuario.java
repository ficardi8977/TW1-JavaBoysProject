package ar.edu.unlam.tallerweb1.domain.excepciones;

public class NoSeRegistroAlUsuario extends RuntimeException{
    public NoSeRegistroAlUsuario(Throwable causa){
        super("No se pudo registrar al usuario", causa);
    }
}
