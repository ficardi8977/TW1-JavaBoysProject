package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.excepciones.*;
import ar.edu.unlam.tallerweb1.domain.tipoUsuario.TipoUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
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

		if(user==null)
			throw new UsuarioNoEncontrado();

		return user;
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario buscar(String email) {
		Usuario user = null;
		try{
			user = (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
					.add(Restrictions.eq("email", email))
					.uniqueResult();
		} catch (Exception e) {
			throw new EmailYaRegistrado();
		}

		return user;
	}

	@Override
	public void modificar(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

	@Override
	public Boolean registroUsuario(DatosRegistracion datosRegistracion) {

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

		// El tipo de usuario es de tipo masivo
		TipoUsuario tu = new TipoUsuario(1l, "Masivo");
		this.sessionFactory.getCurrentSession().save(tu);
		user.setTipoUsuario(tu);

		// Se guarda el objeto en la sesión
		try {
			guardar(user);
		} catch (Exception e) {
			throw new NoSeRegistroAlUsuario(e);
		}

		// Se busca si el usuario guardado ahora existe en la base de datos
		if (buscar(user.getEmail()) != null)
			registrado = true;

		return registrado;


	}
}
