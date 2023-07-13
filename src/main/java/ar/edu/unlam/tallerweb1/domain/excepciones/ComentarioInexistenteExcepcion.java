package ar.edu.unlam.tallerweb1.domain.excepciones;

public class ComentarioInexistenteExcepcion extends RuntimeException{
    public ComentarioInexistenteExcepcion(){
        super("Comentario inexistente");
    }
}
