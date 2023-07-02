package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);

	Usuario buscarUsuario(int id);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);
    Boolean registroUsuario(DatosRegistracion datosRegistracion);
}
