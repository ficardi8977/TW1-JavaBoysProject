package ar.edu.unlam.tallerweb1.domain.cuidado;

import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioCuidadoImpl implements ServicioCuidado {
        private RepositorioCuidado repositorioCuidado;
    @Autowired
    public ServicioCuidadoImpl(RepositorioCuidado repositorioCuidado) {
        this.repositorioCuidado = repositorioCuidado;
    }

    @Override
    public List<Cuidado> ObtenerTodosLosRefugios() {
        return repositorioCuidado.TodosLosRefugios();
    }
}
