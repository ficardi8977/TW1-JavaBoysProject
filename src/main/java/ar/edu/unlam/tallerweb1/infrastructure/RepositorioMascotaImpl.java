package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.excepciones.NoSeRegistroLaMascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.domain.tipoRaza.TipoRaza;
import ar.edu.unlam.tallerweb1.delivery.DatosUbicacion;
import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public List<Mascota> buscarMascotasPorIdUsuario(long idUsuario) {
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
    public List<Mascota> buscarMascotasPorEstados(String[] estados) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class);

        // Crear un alias para acceder al estado de la mascota
        criteria.createAlias("estado", "e");

        // Crear una lista de restricciones para los estados
        Disjunction disjunction = Restrictions.disjunction();
        for (String estado : estados) {
            disjunction.add(Restrictions.eq("e.nombre", estado));
        }
        criteria.add(disjunction);

        // Obtener el resultado de la búsqueda
        return criteria.list();
    }


    @Override
    public Boolean registrarMascota(DatosMascotas datosMascotas) {

        Boolean registrado = false;

        Estado e = (Estado) this.sessionFactory.getCurrentSession().createCriteria(Estado.class)
                .add(Restrictions.eq("id", datosMascotas.getEstado())).uniqueResult();
        TipoRaza razaExistente = (TipoRaza) this.sessionFactory.getCurrentSession().createCriteria(TipoRaza.class)
                .add(Restrictions.eq("nombre", datosMascotas.getRaza())).uniqueResult();

        Mascota mascota = new Mascota();
        mascota.setNombre(datosMascotas.getNombre());
        if(datosMascotas.getDescripcion()==""){
            mascota.setDescripcion("Sin descripción");
        } else {
            mascota.setDescripcion(datosMascotas.getDescripcion());
        }
        mascota.setEstado(e);
        mascota.setIdUsuario(datosMascotas.getIdUsuario());
        mascota.setLatitud(datosMascotas.getLatitud());
        mascota.setLongitud(datosMascotas.getLongitud());
        mascota.setTipoRaza(razaExistente);

        if(datosMascotas.getImagen()!=null){
            mascota.setImagen(datosMascotas.getImagen());
        } else {
            mascota.setImagen("huellita.jpg");
        }

        this.sessionFactory.getCurrentSession().save(mascota);

        Mascota buscarMascota = (Mascota) this.sessionFactory.getCurrentSession().createCriteria(Mascota.class)
                .add(Restrictions.eq("id", mascota.getId())).uniqueResult();

        if(buscarMascota!=null){
            registrado = true;
        } else {
            throw new NoSeRegistroLaMascota();
        }

        return registrado;

    }
}
