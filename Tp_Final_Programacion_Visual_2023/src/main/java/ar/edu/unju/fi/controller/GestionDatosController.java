package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
@Controller
@RequestMapping("/gestion")
public class GestionDatosController {

	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	
    @GetMapping("/autenticacion")
    public String getAutenticacionPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "autenticacion";
    }
    
    @PostMapping("/autenticacion")
    public String postAutenticacion(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuarioService.existeUsuario(usuario.getId())&& usuario.getId() == 1) {
            return "gestion_datos";
        } else {
            model.addAttribute("error", "El ID no corresponde al admin.");
            return "autenticacion";
        }
    }

    
    @GetMapping("/datos")
    public String getGestionDatosPage() {
        return "gestion_datos";
    }

}