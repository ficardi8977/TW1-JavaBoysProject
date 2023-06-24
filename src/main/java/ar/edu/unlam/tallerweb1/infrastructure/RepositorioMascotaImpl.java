package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import org.hibernate.Criteria;
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
    public List<Mascota> buscarMascotasPorIdUsuario(Long idUsuario) {
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

    @Override
    public Boolean registrarMascota(DatosMascotas datosMascotas) {

        Boolean registrado = false;

        TipoMascota tm = (TipoMascota) this.sessionFactory.getCurrentSession().createCriteria(TipoMascota.class)
                .add(Restrictions.eq("id", datosMascotas.getTipo())).uniqueResult();
        Estado e = (Estado) this.sessionFactory.getCurrentSession().createCriteria(Estado.class)
                .add(Restrictions.eq("id", datosMascotas.getEstado())).uniqueResult();
        TipoRaza razaExistente = (TipoRaza) this.sessionFactory.getCurrentSession().createCriteria(TipoRaza.class)
                .add(Restrictions.eq("nombre", datosMascotas.getRaza())).uniqueResult();

        Mascota mascota = new Mascota();
        mascota.setNombre(datosMascotas.getNombre());
        mascota.setDescripcion(datosMascotas.getDescripcion());
        mascota.setImagen(datosMascotas.getImagen());
        mascota.setEstado(e);
        mascota.setIdUsuario(datosMascotas.getIdUsuario());
        mascota.setLatitud(datosMascotas.getLatitud());
        mascota.setLongitud(datosMascotas.getLongitud());

        if (razaExistente!=null){
            mascota.setTipoRaza(razaExistente);
        } else {
            TipoRaza tr = new TipoRaza(datosMascotas.getRaza(), tm);
            this.sessionFactory.getCurrentSession().save(tr);
            mascota.setTipoRaza(tr);
        }

        this.sessionFactory.getCurrentSession().save(mascota);

        Mascota buscarMascota = (Mascota) this.sessionFactory.getCurrentSession().createCriteria(Mascota.class)
                .add(Restrictions.eq("id", mascota.getId())).uniqueResult();

        if(buscarMascota!=null){
            registrado = true;
        }

        return registrado;

    }
}
