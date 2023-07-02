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

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.util.UploadFile;
import io.github.classgraph.Resource;
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
	public String getListaTestimonio(Model model) {
		model.addAttribute("testimonio", testimonioService.obtenerTodosLosTestimonios());
		return "testimonio";
	}
	
	@GetMapping("/id-usuario-testimonio")
	public String getIngresarIdUsuarioTestimonio(Model model) {
		boolean existeUsuario=true;
		model.addAttribute("existeUsuario", existeUsuario);
		return"id-usuario-testimonio";
	}
	
	@GetMapping("/nuevo-testimonio")
	public ModelAndView obtenerFormularioTestimonioPage(Long idUsuLong, Usuario nombre) {
		boolean existeUsuario;
		ModelAndView modelAndView = new ModelAndView();
		if (usuarioService.existeUsuario(idUsuLong)) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("testimonio", new Testimonio());
			modelAndView.addObject("nombre", usuarioService.obtenerUsuario());
			modelAndView.addObject("usuario", usuarioService.buscarUsuario(idUsuLong));
		} else {
			existeUsuario = false;
			modelAndView.addObject("existeUsuario", existeUsuario);
			modelAndView.setViewName("id-usuario-testimonio");
		}
		return modelAndView;
	}
	
	@GetMapping("/nuevo")
	public String getNuevoTestimonio(Model model) {
		boolean edicion=false;
		model.addAttribute("testimonio", new Testimonio());
		model.addAttribute("edicion", edicion);
		return "nuevo_testimonio";
	}

	@PostMapping("/guardar")
	public ModelAndView agregarTestimonio(@Valid @ModelAttribute("testimonio") Testimonio testimonio,
			BindingResult result, @RequestParam("file") MultipartFile imagen) throws IOException {
		ModelAndView modelAndView = new ModelAndView("gestion_dato_testimonio");
		if (result.hasErrors()) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("edicion", false);
			return modelAndView;
		}

		testimonioService.agregarTestimonio(testimonio, imagen);
		modelAndView.addObject("testimonio", testimonioService.obtenerTodosLosTestimonios());

		return modelAndView;
	}

	@GetMapping("/cargar/{imagen}")
	public ResponseEntity<Resource> goImage(@PathVariable String imagen) {
		Resource resource = null;
		try {
			resource = (Resource) uploadFile.load(imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagen + "\"")
				.body(resource);
	}

	@GetMapping("/modificar/{id}")
	public String getModificarTestimonioPage(Model model, @PathVariable(value = "id") Long id) {
		boolean edicion = true;
		model.addAttribute("testimonio", testimonioService.obtenerTestimonioEncontrado(id));
		model.addAttribute("edicion", edicion);

		return "nuevo_testimonio";
	}

	@PostMapping("/modificar/{id}")
	public String modificarTestimonio(@Valid @ModelAttribute("testimonio") Testimonio testimonioModificado,
			@RequestParam("file") MultipartFile imagen) throws IOException {
		testimonioService.actualizarTestimonio(testimonioModificado, imagen);
		return "redirect:/testimonio/datos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value = "id") Long id) {
		testimonioService.eliminarTestimonio(id);
		return "redirect:/testimonio/datos";
	}

	@GetMapping("/gestion")
	public String getGestionTestimonioPage(Model model) {
		model.addAttribute("testimonios", testimonioService.obtenerTodosLosTestimonios());
		return "gestion_dato_testimonio";
	}

	@GetMapping("/{id}")
	public ModelAndView getTestimonioPage(ModelAndView modelAndView, @PathVariable(value = "id") Long id) {
		modelAndView.addObject("testimonio", testimonioService.obtenerTestimonioEncontrado(id));
		modelAndView.setViewName("testimonio");
		return modelAndView;
	}
	
	@GetMapping("/nombre-de-ususario")
	public String getNuevoUsuarioPage(Model model) {
	    boolean edicion=false;
	    Usuario usuario = usuarioService.obtenerUsuario();
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("edicion", edicion);
	    model.addAttribute("nombreUsuario", usuario.getNombre());
	    return "nuevo_usuario";
	}

}
