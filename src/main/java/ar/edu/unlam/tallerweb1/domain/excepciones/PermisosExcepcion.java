package ar.edu.unlam.tallerweb1.domain.excepciones;

public class PermisosExcepcion extends RuntimeException {

    public PermisosExcepcion() {
        super("No tienes permisos necesarios para realizar esta operación");
    }
}
