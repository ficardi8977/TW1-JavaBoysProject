package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.hibernate.SessionFactory;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RepositorioMascotaImpl implements  RepositorioMascota{
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Mascota> BuscarMascotasPorTipo(Long idTipoMascota) {
        return (List<Mascota>)this.sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .createAlias("tipoRaza", "tipoRaza")
                .createAlias("tipoRaza.tipoMascota", "tipoMascota")
                .add(Restrictions.eq("tipoMascota.id", idTipoMascota)).list();
    }

    @Override
    public Mascota BuscarDetalle(long id) {
        return (Mascota)this.sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void Guardar(Mascota mascota) {
        this.sessionFactory.getCurrentSession().save(mascota);
    }

    @Override
    public List<Mascota> TodasLasMascotas() {
        return (List<Mascota>) this.sessionFactory.getCurrentSession().createCriteria(Mascota.class).list();
    }

    @Override
    public void guardar(Mascota mascota) {
        this.sessionFactory.getCurrentSession().save(mascota);
    }

    @Override
    public List buscarMascotasPorIdUsuario(Long idUsuario) {
        return this.sessionFactory.getCurrentSession().createCriteria(Mascota.class)
                .add(Restrictions.eq("idUsuario", idUsuario)).list();
    }
}
