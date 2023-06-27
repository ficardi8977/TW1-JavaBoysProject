package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        var  mascota=  (Mascota)this.sessionFactory.getCurrentSession().createCriteria(Mascota.class)
                .add(Restrictions.eq("id", id)).uniqueResult();

        if(mascota != null) {
            var comentarios = mascota.getComentarios();
            Hibernate.initialize(comentarios); // inicializa los comentarios , al mantener la seccion
            mascota.setComentarios(comentarios);
        }
        return mascota;
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
    public List<Mascota> buscarMascotasPorIdUsuario(int idUsuario) {
        return this.sessionFactory.getCurrentSession().createCriteria(Mascota.class)
                .add(Restrictions.eq("idUsuario", idUsuario)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();


    }
    @Override
    public List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request)
    {
        var session =  this.sessionFactory.getCurrentSession().createCriteria(Mascota.class);
        if(request.getIdEstado() != 0)
        {
            session.add(Restrictions.eq("estado.id", request.getIdEstado()));
        }
        if(request.getIdTipoMascota() != 0)
        {
            session.createAlias("tipoRaza", "tipoRaza")
                .createAlias("tipoRaza.tipoMascota", "tipoMascota")
                .add(Restrictions.eq("tipoMascota.id", request.getIdTipoMascota()));
        }
        var result =  session.list();

        return result;
    }

    public void guardarVacuna(Vacunacion vacuna) {
        this.sessionFactory.getCurrentSession().save(vacuna);
    }
}
