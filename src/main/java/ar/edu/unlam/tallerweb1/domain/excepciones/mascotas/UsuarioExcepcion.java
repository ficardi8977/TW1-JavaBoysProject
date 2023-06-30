package ar.edu.unlam.tallerweb1.domain.excepciones.mascotas;

public class UsuarioExcepcion extends RuntimeException {

    public UsuarioExcepcion() {
        super("usuario no existente");
    }
}
