package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
@Controller
@RequestMapping("/gestion")
public class GestionDatosController {

	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	
	/**
	 * Metodo que retorna la pagina de autenticacion de usuario para poder acceder a la gestion de datos
	 * @param model representa la clase que envia usuario
	 * @return la pagina autenticacion.html
	 */
    @GetMapping("/autenticacion")
    public String getAutenticacionPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "autenticacion";
    }
    
    /**
     * Metodo que autentica el id del usuario para acceder a la gestion de datos
     * @param id representa el id del usuario que se envia desde el formulario atraves del atributo name
     * @param model representa la clase que envia el error
     * @return la pagina gestion_datos.html en caso de que el usuario sea administrador 
     * en caso contrario autenticacion.html
     */
    @GetMapping("/autenticar")
    public String postAutenticacion(Long id,Model model) {
        if (usuarioService.existeUsuario(id) && usuarioService.buscarUsuario(id).isAdministrador()==true) {
        	
            return "gestion_datos";
        } else {
            model.addAttribute("error", "El ID no corresponde al admin.");
            return "autenticacion";
        }
    }

    /**
     * Metodo de devuelve la pagina gestion de datos 
     * @return la pagina gestion_datos.html
     */
    @GetMapping("/datos")
    public String getGestionDatosPage() {
        return "gestion_datos";
    }

}
