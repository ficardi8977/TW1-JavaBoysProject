package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidadores.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidadores.TipoCuidado;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioCuidadorImpl implements RepositorioCuidador{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCuidadorImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Cuidado> TodosLosCuidadores() {
        return (List<Cuidado>)this.sessionFactory.getCurrentSession()
                .createCriteria(Cuidado.class)
                .createAlias("tipocuidado","tc")
                .add(Restrictions.eq("tc.nombre","Cuidador")).list();
    }



    @Override
    public void Guardar(Cuidado cuidado) {
        this.sessionFactory.getCurrentSession().save(cuidado);
    }

    @Override
    public void GuardarTipoCuidado(TipoCuidado tipoCuidado) {
        this.sessionFactory.getCurrentSession().save(tipoCuidado);
    }
}
