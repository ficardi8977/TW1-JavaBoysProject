package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioEstadoImpl implements  RepositorioEstado{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEstadoImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void Guardar(Estado estado) {
        this.sessionFactory.getCurrentSession().save(estado);
    }
}

