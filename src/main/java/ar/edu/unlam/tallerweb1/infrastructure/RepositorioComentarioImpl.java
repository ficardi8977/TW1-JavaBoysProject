package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RepositorioComentarioImpl implements RepositorioComentario{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioComentarioImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public int guardar(Comentario comentario) {
        this.sessionFactory.getCurrentSession().save(comentario);
        return  Math.toIntExact(comentario.getId());
    }
    @Override
    public Comentario obtener(long id){
        return  (Comentario) this.sessionFactory.getCurrentSession().createCriteria(Comentario.class)
                .add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void eliminar(Comentario comentario){
        this.sessionFactory.getCurrentSession().delete(comentario);
    }

    @Override
    public List<Comentario> obtenerPorIdCuidado(long idCuidado) {
        return  (List<Comentario>) this.sessionFactory.getCurrentSession().createCriteria(Comentario.class)
                .add(Restrictions.eq("cuidado.id",idCuidado)).list();
    }
}
