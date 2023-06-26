package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
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
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario buscarUsuario(String email, String password) {

		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.uniqueResult();
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario buscar(String email) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	@Override
	public void modificar(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

	@Override
	public Boolean validarDatos(DatosRegistracion datosRegistracion) {
		String email = datosRegistracion.getEmail();
		String password = datosRegistracion.getPassword();
		String telefono = datosRegistracion.getTelefono();
		Boolean esValido = false;
		Boolean emailValido = email.endsWith(".com") && email.contains("@");
		Boolean telefonoValido = !telefono.equals("") && telefono!=null;
		Boolean pwValida = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$");
		// Que tenga al menos una mayúscula, al menos una minúscula, al menos un digito y
		// que sea de entre 4 y 8 caracteres de largo

		if(buscar(email)==null && emailValido && pwValida && telefonoValido)
			esValido = true;

		return esValido;
	}
	@Override
	public Boolean registroUsuario(DatosRegistracion datosRegistracion) {
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

		TipoUsuario tu = new TipoUsuario(1l, "Masivo"); // mejorar
		user.setTipoUsuario(tu);

		this.sessionFactory.getCurrentSession().save(user);

		if(buscar(user.getEmail())!=null)
			registrado = true;

		return registrado;
	}


}
