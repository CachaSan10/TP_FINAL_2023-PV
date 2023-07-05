package ar.edu.unju.fi.controller;

import java.net.MalformedURLException;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.util.UploadFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/testimonio")

public class TestimonioController {

	@Autowired
	@Qualifier("testimonioServiceMysqlImp")
	private ITestimonioService testimonioService;

	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;

	@Autowired
	private UploadFile uploadFile;

	@GetMapping("/listado")
	public String getListaTestimonioPage(Model model) {
		model.addAttribute("testimonios",testimonioService.obtenerListaTestimonio());
		return "testimonios";
	}
	
	@GetMapping("/id-usuario-testimonio")
	public String getIngresarIdUsuarioTestimonio(Model model) {
		boolean existeUsuario=true;
		model.addAttribute("existeUsuario", existeUsuario);
		return"id-usuario-testimonio";
	}
	
	@GetMapping("/nuevo")
	public ModelAndView getObtenerNuevoTestimonioPage(Long idUsuLong, Usuario usuario) {
		boolean existeUsuario;
		ModelAndView modelAndView = new ModelAndView();
		if (usuarioService.existeUsuario(idUsuLong)) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("testimonio", testimonioService.obtenerTestimonio());
			modelAndView.addObject("usuarios", usuarioService.obtenerLista());
			modelAndView.addObject("usuario", usuarioService.buscarUsuario(idUsuLong));
		} else {
			existeUsuario = false;
			modelAndView.addObject("existeUsuario", existeUsuario);
			modelAndView.setViewName("id-usuario-testimonio");
		}
		return modelAndView;
	}

	//modelatt unUsuario
	@PostMapping("/guardar")
	public ModelAndView getGuardarTestimonioPage(@Valid @ModelAttribute("testimonio") Testimonio testimonio, BindingResult result) {
		ModelAndView  modelAndView = new ModelAndView("redirect:/testimonio/gestion");
		
		if(result.hasErrors()) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("usuarios", usuarioService.obtenerLista());
			modelAndView.addObject("testimonio", testimonio);
			return modelAndView;
		}
		testimonioService.guardarTestimonio(testimonio);
		modelAndView.addObject("testimonios", testimonioService.obtenerListaTestimonio());

		return modelAndView;
	}

	@GetMapping("/modificar/{id}")
	public String getModificarTestimonioPage(Model model, @PathVariable(value="id") Long id){
		boolean edicion=true;
		model.addAttribute("usuarios", usuarioService.obtenerLista());
		model.addAttribute("testimonio", testimonioService.buscarTestimonio(id));
		model.addAttribute("edicion", edicion);
		
		return "nuevo_testimonio";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarTestimonio(@ModelAttribute("testimonio") Testimonio testimonioModificado) {
		testimonioService.modificarTestimonio(testimonioModificado);
		return "redirect:/testimonio/gestion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id")Long id) {
		testimonioService.eliminarTestimonio(id);
		return "redirect:/testimonio/gestion";
	}
	
	@GetMapping("/gestion")
	public String getGestionProductoPage(Model model) {
		model.addAttribute("usuarios", usuarioService.obtenerLista());
		model.addAttribute("testimonios", testimonioService.obtenerListaTestimonio());
		return "gestion_dato_testimonio";
	}
	
	@GetMapping("/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = (Resource) uploadFile.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  resource.getFilename() + "\"")
				.body(resource);
	}
	
	//validar usuario
	//crear un metodo usuario para recibir el id
	//para enviar el id al fomulario
	//con el post buscar el usuario
	


}
