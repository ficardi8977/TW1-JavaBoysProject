package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioTipoCuidadoImpl implements  RepositorioTipoCuidado{
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioTipoCuidadoImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void Guardar(Tipocuidado tipocuidado) {
     this.sessionFactory.getCurrentSession().save(tipocuidado);
    }
}
