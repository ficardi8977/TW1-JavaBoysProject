package ar.edu.unlam.tallerweb1.domain.usuarios;

public class ServicioRegistracionGoogleImpl implements  ServicioRegistracion{
    @Override
    public Boolean esValido(String correo) {
        return null;
    }

    @Override
    public Boolean registrarUsuario(String correo, String clave) {
        return null;
    }

    public Boolean registroUsuario(String nombre, String apellido, String email, String password, String telefono){
        return true;
    }

    @Override
    public Boolean datosValidos(String correo, String password) {
        return null;
    }
}
