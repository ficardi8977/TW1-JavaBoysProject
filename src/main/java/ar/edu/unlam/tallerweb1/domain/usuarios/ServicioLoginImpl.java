package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.excepciones.AlgoritmoNoDisponible;
import ar.edu.unlam.tallerweb1.domain.excepciones.CodificacionNoDisponible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario servicioLoginDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
		this.servicioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario consultarUsuario (String email, String password) {
		String nuevaPassword = encriptarClave(password);
		return servicioLoginDao.buscarUsuario(email, nuevaPassword);
	}

	@Override
	public String encriptarClave(String clave) {
		MessageDigest md;
		byte[] digest;
		// Se encripta la contraseña ingresada con el algoritmo MD5
		try{
			md = MessageDigest.getInstance("MD5");
			digest = clave.getBytes("UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new AlgoritmoNoDisponible();
		} catch (UnsupportedEncodingException e){
			throw new CodificacionNoDisponible();
		}

		byte[] convertPass = md.digest(digest);

		StringBuffer sb = new StringBuffer();
		for (final byte b : convertPass) {
			sb.append(String.format("%02x", b));
		}
		String passwordCifrada = sb.toString().toUpperCase();
		return passwordCifrada;
	}

}
