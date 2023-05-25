package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<TipoMascota> List() {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(TipoMascota.class)
                .list();
    }
}
