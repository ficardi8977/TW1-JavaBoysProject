package ar.edu.unlam.tallerweb1.domain.cuidadores;

import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidador;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidadorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCuidadorImpl implements ServicioCuidador {

    private RepositorioCuidador repositorioCuidador;

    @Autowired
    public ServicioCuidadorImpl (RepositorioCuidador repositorioCuidador){
        this.repositorioCuidador = repositorioCuidador;
    }
    public List<Cuidado> ObtenerTodosLosCuidadores() {
        return this.repositorioCuidador.TodosLosCuidadores();
    }
}
