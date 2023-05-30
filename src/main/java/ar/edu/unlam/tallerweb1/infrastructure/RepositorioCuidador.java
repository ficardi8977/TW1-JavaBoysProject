package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.TipoCuidado;

import java.util.List;

public interface RepositorioCuidador {

    List<Cuidado> TodosLosCuidadores();

    void Guardar(Cuidado cuidado);

    void GuardarTipoCuidado(TipoCuidado tipoCuidado);
}
