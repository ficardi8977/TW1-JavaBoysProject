package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioTipoRazaImpl implements RepositorioTipoRaza{
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioTipoRazaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void Guardar(TipoRaza tipoRaza) {
        this.sessionFactory.getCurrentSession().save(tipoRaza);
    }
}
