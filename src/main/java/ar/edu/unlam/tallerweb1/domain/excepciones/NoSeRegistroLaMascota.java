package ar.edu.unlam.tallerweb1.domain.excepciones;

public class NoSeRegistroLaMascota extends RuntimeException{
    public NoSeRegistroLaMascota(){
        super("No se pudo registrar a la mascota");
    }
}
