package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RepositorioEstadoImpl implements  RepositorioEstado{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEstadoImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(Estado estado) {
        this.sessionFactory.getCurrentSession().save(estado);
    }

    @Override
    public List<Estado> list() {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Estado.class)
                .list();
    }

}

