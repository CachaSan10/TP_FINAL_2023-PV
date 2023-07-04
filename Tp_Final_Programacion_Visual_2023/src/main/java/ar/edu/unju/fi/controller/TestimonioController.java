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
	private Testimonio unTestimonio;

	@Autowired
	private Usuario unUsuario;

	@Autowired
	@Qualifier("testimonioServiceMysqlImp")
	private ITestimonioService testimonioService;

	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;

	@Autowired
	private UploadFile uploadFile;

	@GetMapping("/lista")
	public ModelAndView  getPageTestimonios(Model model) {
		ModelAndView mav= new ModelAndView("gestion_dato_testimonio");
		mav.addObject("testimonios", testimonioService.obtenerTodosLosTestimonios());
		return mav;
	}

	@GetMapping("/formulario")
	public ModelAndView getPageFormTestimonio() {
		
		unTestimonio= new Testimonio();
		ModelAndView mav= new ModelAndView("nuevo_testimonio");
		mav.addObject("unTestimonio", unTestimonio);
		mav.addObject("usuarios",usuarioService.obtenerLista());
		return mav;
	}

	@PostMapping("/guardar")
	public ModelAndView postPageSaveTestimonio(@Valid @ModelAttribute("unTestimonio") Testimonio testimonio,
			BindingResult result, @RequestParam("file") MultipartFile image) throws Exception {
		ModelAndView mav;
		if (result.hasErrors()) {
			mav = new ModelAndView("nuevo_testimonio");
			mav.addObject("usuarios", usuarioService.obtenerLista());
		} else {
			unUsuario = usuarioService.buscarUsuario(testimonio.getUsuario().getId());
			testimonio.setUsuario(unUsuario);
			if (testimonio.getId() > 0) {
				unTestimonio = testimonioService.findTestimonioById(testimonio.getId());
				if (!image.isEmpty()) {
					if (unTestimonio.getImagen() != null && unTestimonio.getImagen().length() > 0)
						uploadFile.delete(unTestimonio.getImagen());
					String uniqueFileName = uploadFile.copy(image);
					testimonio.setImagen(uniqueFileName);
				} else {
					if (unTestimonio.getImagen() != null)
						testimonio.setImagen(unTestimonio.getImagen());
				}
			} else {
				if (!image.isEmpty()) {
					String uniqueFileName = uploadFile.copy(image);
					testimonio.setImagen(uniqueFileName);
				}
			}
			testimonioService.agregarTestimonio(testimonio);
			mav = new ModelAndView("gestion_dato_testimonio");
			mav.addObject("testimonios", testimonioService.obtenerTodosLosTestimonios());
		}
		return mav;
	}

	@GetMapping("/modificar/{id}")
	public ModelAndView getPageEditTestimonio(@PathVariable("id") Long id) {
		unTestimonio = testimonioService.findTestimonioById(id);
		ModelAndView mav = new ModelAndView("nuevo_testimonio");
		mav.addObject("unTestimonio", unTestimonio);
		mav.addObject("usuarios", usuarioService.obtenerLista());
		return mav;
	}

	@GetMapping("/detalle/{id}")
	public ModelAndView getPageMostrarTestimonio(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("detalleTestimonio");
		unTestimonio = testimonioService.findTestimonioById(id);
		mav.addObject("comentario", "MUESTRA DE TESTIMONIO" + unTestimonio.getComentario());
		mav.addObject("filename", unTestimonio.getImagen());
		return mav;
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


}
