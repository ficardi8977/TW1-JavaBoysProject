package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

	private ServicioUsuario servicioUsuario;

	@Autowired
	public ControladorLogin(ServicioUsuario servicioUsuario){
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping(path = "/login")
	public ModelAndView irALogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		try{
			Usuario usuarioBuscado = servicioUsuario.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
			request.getSession().setAttribute("ROL", usuarioBuscado.getTipoUsuario().getNombre());
			request.getSession().setAttribute("NOMBRE", usuarioBuscado.getNombre());
			request.getSession().setAttribute("IDUSUARIO", usuarioBuscado.getId());
			request.getSession().setAttribute("TELEFONO", usuarioBuscado.getTelefono());
			request.getSession().setAttribute("IMAGEN", usuarioBuscado.getImagen());
			return new ModelAndView("redirect:/home");
		} catch (Exception e){
			model.put("error", e.getMessage());
		}

		return new ModelAndView("login", model);
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/home";
	}


	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/mascotas");
	}

}
