package ar.edu.unlam.tallerweb1.domain.excepciones;

public class CuidadoNoExistenteExcepcion extends RuntimeException {

    public CuidadoNoExistenteExcepcion() {
        super("Cuidado ingresado inexistente");
    }
}
