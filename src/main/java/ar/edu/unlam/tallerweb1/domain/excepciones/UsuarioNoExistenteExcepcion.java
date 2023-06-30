package ar.edu.unlam.tallerweb1.domain.excepciones;

public class UsuarioNoExistenteExcepcion extends RuntimeException {

    public UsuarioNoExistenteExcepcion() {
        super("Usuario ingresado inexistente");
    }
}
