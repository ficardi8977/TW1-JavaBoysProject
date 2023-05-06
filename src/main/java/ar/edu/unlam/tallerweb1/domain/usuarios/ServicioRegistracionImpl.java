package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.stereotype.Service;

@Service
public class ServicioRegistracionImpl implements ServicioRegistracion {
    @Override
    public Boolean esValido(String correo) {
        return correo.endsWith(".com") && correo.contains("@");
    }

    @Override
    public Boolean registrarUsuario(String correo, String clave) {
        return true;
    }
}
