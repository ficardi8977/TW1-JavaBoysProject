package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class RepositorioCuidadoImpl implements  RepositorioCuidado{
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioCuidadoImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void Guardar(Cuidado cuidado) {
        this.sessionFactory.getCurrentSession().save(cuidado);
    }

    @Override
    public List<Cuidado> TodosLosRefugios() {
        return (List<Cuidado>)this.sessionFactory.getCurrentSession()
                .createCriteria(Cuidado.class)
                .createAlias("tipocuidado","tipoCuidado")
                .add(Restrictions.eq("tipoCuidado.nombre","Refugio")).list();
    }

    @Override
    public Cuidado BuscarDetalleRefugio(long id) {
        return (Cuidado)this.sessionFactory.getCurrentSession()
                .createCriteria(Cuidado.class)
                .createAlias("tipocuidado","tipoCuidado")
                .add(Restrictions.eq("tipoCuidado.nombre","Refugio"))
                .add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public List<Cuidado> obtenerTodosLosCuidadores() {
        return (List<Cuidado>)this.sessionFactory.getCurrentSession().createCriteria(Cuidado.class)
                .createAlias("tipocuidado", "tc")
                .add(Restrictions.eq("tc.nombre", "Cuidador")).list();
    }

    @Override
    public void GuardarTipoCuidado(Tipocuidado tc) {
        this.sessionFactory.getCurrentSession().save(tc);
    }

    @Override
    public void Guardar(Cuidado c) {
        this.sessionFactory.getCurrentSession().save(c);
    }

    @Override
    public Cuidado getDetalle(long id) {
        return (Cuidado) this.sessionFactory.getCurrentSession().createCriteria(Cuidado.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
}
