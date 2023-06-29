package ar.edu.unlam.tallerweb1.domain.excepciones;

public class UsuarioNoEncontrado extends RuntimeException{
    public UsuarioNoEncontrado(){
        super("No se encontró al usuario");
    }
}
