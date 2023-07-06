package ar.edu.unlam.tallerweb1.domain.usuarios;


// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario consultarUsuario(String email, String password);

	String encriptarClave(String clave);

	Usuario consultarUsuario(int id);
}
