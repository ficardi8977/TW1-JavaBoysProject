package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;

import java.util.List;

public interface RepositorioCuidado {

    List<Cuidado> TodosLosRefugios();

    Cuidado BuscarDetalleRefugio(long id);
}
