package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Usuario usuario;
	
	@GetMapping("/nuevo")
	public String getNuevoUsuarioPage(Model model) {
		boolean edicion=false;
		model.addAttribute("usuario", usuario);
		model.addAttribute("edicion", edicion);
		return "nuevo_usuario";
	}
	
	@PostMapping("/guardar")
	public String postGuardarUsuarioPage(@ModelAttribute("usuario") Usuario usuario, Model model) {
		usuarioService.guardarUsuario(usuario);
		model.addAttribute("idUsuario", usuario.getId());
		return "agradecimiento";
	}

	@GetMapping("/modificar/{id}")
	public String getModificarUsuarioPage(Model model, @PathVariable(value="id")Long id) {
		boolean edicion=true;
		Usuario usuarioEncontrado = usuarioService.buscarUsuario(id);
		
		model.addAttribute("usuario", usuarioEncontrado);
		model.addAttribute("edicion", edicion);
		return "nuevo_usuario";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarUsuario(@ModelAttribute("usuario") Usuario usuarioModificado) {
		usuarioService.modificarUsuario(usuarioModificado);
		return "redirect:/usuario/listado";
	}
	
	@GetMapping("/eliminar{id}")
	public String eliminarUsuario(@PathVariable(value="id")Long id) {
		Usuario usuarioEncontrado = usuarioService.buscarUsuario(id);
		usuarioService.eliminarUsuario(usuarioEncontrado);
		
		return "";
	}

	@GetMapping("/listado")
	public String getUsuariosPage(Model model) {
		model.addAttribute("usuarios", usuarioService.obtenerLista());
		return "usuarios";
	}


}
