package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.*;
import ar.edu.unlam.tallerweb1.domain.tipoUsuario.TipoUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate
	// el mismo esta difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario buscarUsuario(String email, String password) {

		// Se busca al usuario por email y contraseña
		final Session session = sessionFactory.getCurrentSession();
		Usuario user = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.uniqueResult();

		if (user == null)
			throw new UsuarioNoEncontrado();

		return user;
	}

	@Override
	public Usuario buscarUsuario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		var usuario = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return usuario;

	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario buscar(String email) {
		Usuario user = null;

		user = (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email)).uniqueResult();

		return user;
	}

	@Override
	public void modificar(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

	@Override
	public Boolean registroUsuario(DatosRegistracion datosRegistracion) {

		TipoUsuario tu = (TipoUsuario) this.sessionFactory.getCurrentSession().createCriteria(TipoUsuario.class)
				.add(Restrictions.eq("id", 1l)).uniqueResult();

		// Se registra el usuario con los datos ingresados
		Boolean registrado = false;

		Usuario user = new Usuario(
				datosRegistracion.getNombre(),
				datosRegistracion.getApellido(),
				datosRegistracion.getEmail(),
				datosRegistracion.getPassword(),
				datosRegistracion.getTelefono(),
				datosRegistracion.getLatitud(),
				datosRegistracion.getLongitud()
		);

		if(datosRegistracion.getImagen()!=null){
			user.setImagen(datosRegistracion.getImagen());
		} else {
			user.setImagen("defaultUser.png");
		}

		user.setTipoUsuario(tu);

		// Se guarda el objeto en la sesión
		try {
			guardar(user);
		} catch (Exception e) {
			throw new NoSeRegistroAlUsuario(e);
		}

		// Se busca si el usuario guardado ahora existe en la base de datos
		if (buscarGuardado(user.getEmail()) != null)
			registrado = true;

		return registrado;


	}

	@Override
	public Usuario buscarGuardado(String email) {
		Usuario user = null;

		user = (Usuario) this.sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email)).uniqueResult();

		if (user == null)
			throw new UsuarioNoEncontrado();

		return user;
	}
}
