package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.EstadoMascotasEnum;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public Long getIdEstadoPorNombre(String nombre) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(Estado.class)
                .add(Restrictions.eq("nombre", nombre))
                .setProjection(Projections.property("id"));

        return (Long) criteria.uniqueResult();
    }



}

