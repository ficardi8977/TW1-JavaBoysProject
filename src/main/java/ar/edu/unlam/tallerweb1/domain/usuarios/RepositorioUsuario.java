package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.delivery.DatosRegistracion;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);

	Usuario buscarUsuario(Long id);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);
    Boolean registroUsuario(DatosRegistracion datosRegistracion);

	Usuario buscarGuardado(String email);
}
