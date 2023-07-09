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
	
	
	/**
	 * Metodo que retorna  formulario para registrar usuarios
	 * @param model representa la clase model para enviar las varibles edicion y usuario
	 * @return la pagina nuevo_usuario.html
	 */
	@GetMapping("/nuevo")
	public String getNuevoUsuarioPage(Model model) {
		boolean edicion=false;
		model.addAttribute("usuario", usuarioService.obtenerUsuario());
		model.addAttribute("edicion", edicion);
		return "nuevo_usuario";
	}
	
	/**
	 * Metodo que guarda al usuario en la base de datos
	 * @param representa al usuario que se envio para guardar los datos
	 * @param bindingResult representa la clase que recibe los errores del formulario
	 * @return la pagina agradecimiento en caso de no tener errores al cargar el formulario,
	 * si captura errores mostrara nuevamente el formulario de registro
	 */
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


	/**
	 * Metodo que retorna el formulario de usuarios para modificar los datos
	 * @param model representa la clase model para enviar usuario, edicion
	 * @param id representa el id de usuario que se va a modificar
	 * @return el formulario para modificar nuevo_usuario.html
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarUsuarioPage(Model model, @PathVariable(value="id")Long id) {
		boolean edicion=true;
		Usuario usuarioEncontrado = usuarioService.buscarUsuario(id);
		
		model.addAttribute("usuario", usuarioEncontrado);
		model.addAttribute("edicion", edicion);
		return "nuevo_usuario";
	}
	
	/**
	 * Metodo que modifica los datos del usuario
	 * @param usuarioModificado representa el usuario modificado
	 * @param bindingResult representa la clase que recibe los errores del formulario
	 * @return la pagina con todos los usuarios registrados, si encuentra algun error
	 *  vuelve a retornar el formulario para modificar
	 */
	@PostMapping("/modificar/{id}")
	public String modificarUsuario(@Valid @ModelAttribute("usuario") Usuario usuarioModificado, BindingResult bindingResult) {
		if ( bindingResult.hasErrors() ) {
			return "nuevo_usuario";
		}
		
		usuarioService.modificarUsuario(usuarioModificado);
		return "redirect:/usuario/listado";
	}
	
	/**
	 * Metodo que elimina el usuario de la base de datos
	 * @param id representa el id del usuario a eliminar
	 * @return la pagina en donde se encuentra el listado de usuarios
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(value="id")Long id) {
		Usuario usuarioEncontrado = usuarioService.buscarUsuario(id);
		usuarioService.eliminarUsuario(usuarioEncontrado);
		
		return "redirect:/usuario/listado";
	}

	/**
	 * Metodo que devuelve el listado de usuarios para gestionar sus datos(modificar/eliminar)
	 * @param model representa la clase que envia los usuarios
	 * @return la pagina usuarios.html
	 */
	@GetMapping("/listado")
	public String getUsuariosPage(Model model) {
		model.addAttribute("usuarios", usuarioService.obtenerLista());
		return "usuarios";
	}


}
