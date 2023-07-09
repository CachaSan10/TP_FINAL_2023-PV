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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

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
	
	/**
	 * Muestra la página que lista todos los testimonios.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista testimonios.html.
	 */
	@GetMapping("/listado")
	public String getListaTestimonioPage(Model model) {
		model.addAttribute("testimonios",testimonioService.obtenerListaTestimonio());
		return "testimonios";
	}
	
	/**
	 * Muestra la página para ingresar el ID de usuario y crear un nuevo testimonio.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista id-usuario-testimonio.html.
	 */
	@GetMapping("/id-usuario-testimonio")
	public String getIngresarIdUsuarioTestimonio(Model model) {
		boolean existeUsuario=true;
		model.addAttribute("existeUsuario", existeUsuario);
		return"id-usuario-testimonio";
	}
	
	/**
	 * Muestra la página para crear un nuevo testimonio.
	 *
	 * @param idUsuLong El ID del usuario asociado al testimonio.
	 * @param usuario El objeto Usuario asociado al testimonio.
	 * @return El objeto ModelAndView para la vista nuevo_testimonio.html.
	 */
	@GetMapping("/nuevo")
	public ModelAndView getObtenerNuevoTestimonioPage(Long idUsuLong, Usuario usuario) {
		boolean existeUsuario;
		boolean existeTestimonio = true;

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("edicion", false);
		if (usuarioService.existeUsuario(idUsuLong)) {
			if(testimonioService.existeTestimonioRegistrado(idUsuLong)) {
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
	
	/**
	 * Guarda un testimonio y su imagen asociada en la base de datos.
	 *
	 * @param testimonio El testimonio a guardar.
	 * @param result El objeto BindingResult que contiene los resultados de la validación.
	 * @param id El ID del usuario asociado al testimonio.
	 * @param imagen La imagen asociada al testimonio.
	 * @return El objeto ModelAndView para la vista redirect:/testimonio/listado.
	 */
	@PostMapping("/guardar/{usuario-id}")
	public ModelAndView getGuardarTestimonioPage(@Valid @ModelAttribute("testimonio") Testimonio testimonio, BindingResult result, @PathVariable(value="usuario-id") Long id, 
			@RequestParam("file") MultipartFile imagen)throws IOException {
		ModelAndView  modelAndView = new ModelAndView("redirect:/testimonio/listado");
		if(result.hasErrors()) {
			modelAndView.addObject("edicion", false);

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
	
	/**
	 * Muestra la página para modificar un testimonio existente.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @param id El ID del testimonio a modificar.
	 * @return El nombre de la vista nuevo_testimonio.html.
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarTestimonioPage(Model model, @PathVariable(value="id") Long id) {
		boolean edicion=true;
		model.addAttribute("usuario", usuarioService.buscarUsuario(id));
		model.addAttribute("testimonio", testimonioService.buscarTestimonio(id));
		model.addAttribute("edicion", edicion);
		
		return "nuevo_testimonio";
	}
	
	/**
	 * Modifica un testimonio existente y actualiza su imagen asociada si se
	 * proporciona una nueva imagen.
	 *
	 * @param id El ID del testimonio a modificar.
	 * @param testimonioModificado El testimonio modificado.
	 * @param bindingResult El objeto BindingResult que contiene los resultados de la validación.
	 * @param imagen La nueva imagen asociada al testimonio (opcional).
	 * @param testimonio El testimonio actual.
	 * @return El nombre de la vista redirect:/testimonio/gestion.
	 */
	@PostMapping("/modificar/{id}")
	public String modificarTestimonio(@PathVariable(value="id") Long id,@ModelAttribute("testimonio") Testimonio testimonioModificado, 
			BindingResult bindingResult, MultipartFile imagen, Testimonio testimonio) throws IOException {
		if (bindingResult.hasErrors()) {
	        return "nuevo_testimonio";
	    }

	    Testimonio testimonioExistente = testimonioService.buscarTestimonio(testimonioModificado.getId());
	    if (testimonioExistente == null) {
	        return "redirect:/testimonio/gestion";
	    }
	    
	    testimonioModificado.setUsuario(usuarioService.buscarUsuario(id));
	    testimonioService.modificarTestimonio(testimonioModificado, imagen);
	    return "redirect:/testimonio/gestion";
	}
	
	/**
	 * Elimina un testimonio por su ID.
	 *
	 * @param id El ID del testimonio a eliminar.
	 * @return El nombre de la vista redirect:/testimonio/gestion.
	 * @throws NotFoundException Si el testimonio no se encuentra.
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id")Long id) throws NotFoundException {
		testimonioService.eliminarTestimonio(id);
		return "redirect:/testimonio/gestion";
	}
	
	/**
	 * Muestra la página de gestión de testimonios.
	 *
	 * @param model El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista gestion_dato_testimonio.html.
	 */
	@GetMapping("/gestion")
	public String getGestionProductoPage(Model model) {
		model.addAttribute("usuarios", usuarioService.obtenerLista());
		model.addAttribute("testimonios", testimonioService.obtenerListaTestimonio());
		return "gestion_dato_testimonio";
	}
	
	/**
	 * Carga una imagen asociada a un testimonio.
	 *
	 * @param imagen El nombre del archivo de imagen a cargar.
	 * @return La respuesta ResponseEntity con la imagen cargada.
	 */
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
