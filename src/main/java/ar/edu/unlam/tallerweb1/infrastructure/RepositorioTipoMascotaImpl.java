package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositorioTipoMascotaImpl implements  RepositorioTipoMascota{
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioTipoMascotaImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void Guardar(TipoMascota tipoMascota) {

        this.sessionFactory.getCurrentSession().save(tipoMascota);
    }
}
