package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/nuevo")
	public String getNuevoUsuarioPage(Model model) {
		boolean edicion=false;
		model.addAttribute("usuario", usuarioService.obtenerUsuario());
		model.addAttribute("edicion", edicion);
		return "nuevo_usuario";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarUsuarioPage(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult) {

	    if (bindingResult.hasErrors()) {
	    	ModelAndView mav = new ModelAndView("nuevo_usuario");
	    	mav.addObject("usuario", usuario);
	        mav.addObject("edicion", false);
	        return mav;
	    }


	    ModelAndView mav = new ModelAndView("agradecimiento");
	    usuarioService.guardarUsuario(usuario);
	    mav.addObject("idUsuario", usuario.getId());
	  

	    return mav;
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
	public String modificarUsuario(@Valid @ModelAttribute("usuario") Usuario usuarioModificado, BindingResult bindingResult) {
		if ( bindingResult.hasErrors() ) {
			return "nuevo_usuario";
		}
		
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
