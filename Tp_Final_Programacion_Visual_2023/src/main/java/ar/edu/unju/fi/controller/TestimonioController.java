package ar.edu.unju.fi.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		boolean existeTestimonio = true;
		ModelAndView modelAndView = new ModelAndView();
		if (usuarioService.existeUsuario(idUsuLong)) {
			if(testimonioService.existeTestimonio(idUsuLong)) {
				modelAndView.addObject("existeTestimonio", existeTestimonio);
				modelAndView.setViewName("id-usuario-testimonio");
			} else {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("testimonio", testimonioService.obtenerTestimonio());
			modelAndView.addObject("usuario", usuarioService.buscarUsuario(idUsuLong));
			}
		} else {
			existeUsuario = false;
			modelAndView.addObject("existeUsuario", existeUsuario);
			modelAndView.setViewName("id-usuario-testimonio");
		}
		return modelAndView;
	}

	@PostMapping("/guardar/{usuario-id}")
	public ModelAndView getGuardarTestimonioPage(@Valid @ModelAttribute("testimonio") Testimonio testimonio, BindingResult result, @PathVariable(value="usuario-id") Long id, 
			@RequestParam("file") MultipartFile imagen)throws IOException {
		ModelAndView  modelAndView = new ModelAndView("redirect:/testimonio/listado");
		if(result.hasErrors()) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("usuario", usuarioService.buscarUsuario(id));
			modelAndView.addObject("testimonio", testimonio);
			return modelAndView;
		}
		testimonio.setUsuario(usuarioService.buscarUsuario(id));
		testimonioService.guardarTestimonio(testimonio, imagen);
		modelAndView.addObject("testimonios", testimonioService.obtenerListaTestimonio());

		return modelAndView;
	}

	@GetMapping("/modificar/{id}")
	public String getModificarTestimonioPage(Model model, @PathVariable(value="id") Long id){
		boolean edicion=true;
		model.addAttribute("usuario", usuarioService.buscarUsuario(id));
		model.addAttribute("testimonio", testimonioService.buscarTestimonio(id));
		model.addAttribute("edicion", edicion);
		
		return "nuevo_testimonio";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarTestimonio(@ModelAttribute("testimonio") Testimonio testimonioModificado, BindingResult bindingResult, MultipartFile imagen) throws IOException {
		if ( bindingResult.hasErrors() ) {
			return "nuevo_testimonio";
		}
		testimonioService.modificarTestimonio(testimonioModificado, imagen);
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
	
	@GetMapping("/cargar/{imagen}")
	public ResponseEntity<Resource> goImage(@PathVariable String imagen) {
		Resource resource = null;
		try {
			resource = uploadFile.load(imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagen=\"" +  resource.getFilename() + "\"")
				.body(resource);
	}
	
}
