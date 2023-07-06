package ar.edu.unju.fi.controller;


import java.io.IOException;
import java.net.MalformedURLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.util.UploadFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/receta")
public class RecetaController {

	@Autowired
	@Qualifier("recetaServiceMysqlImp")
	private IRecetaService recetaService;
	
	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IIngredienteService ingredienteService;
	
	@Autowired
	private UploadFile uploadFile;
	
	@GetMapping("/gestion")
	public String obtenerPaginaGestionDatosReceta(Model model) {
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		return "gestion_datos_receta";
	}
	
	@GetMapping("/nuevo")
	public String obtenerPaginaNuevaReceta(Model model) {
		boolean edicion=false;
		model.addAttribute("receta", recetaService.obtenerReceta());
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("receta") Receta receta, BindingResult result,
			@RequestParam("file")MultipartFile imagen,
			@RequestParam("ingredientes") Long[] idIngredientes) throws IOException {
		ModelAndView mav = new ModelAndView("redirect:/receta/gestion");
		System.out.println(idIngredientes[0]);
		if (result.hasErrors()) {
			mav.setViewName("nueva_receta");
			mav.addObject("ingredientes", ingredienteService.obtenerIngredientes());
			mav.addObject("edicion", false);
			return mav;
		}
		recetaService.guardarReceta(receta,imagen, idIngredientes);
		return mav;
	}
	
	@GetMapping("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id")Long id) {
		boolean edicion=true;

		model.addAttribute("receta", recetaService.buscarReceta(id));
		model.addAttribute("ingredientes",ingredienteService.obtenerIngredientes());

		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("receta")Receta recetaModificada, BindingResult result,
			@RequestParam("file")MultipartFile imagen,Model  model,
			@RequestParam("ingredientes") Long[] idIngredientes) throws IOException {
		System.out.println("id del array: " + idIngredientes[0]);

		if (result.hasErrors()) {
			model.addAttribute("receta", recetaModificada);
			model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
			return "nueva_receta";
		}
		recetaService.modificarReceta(recetaModificada,imagen,idIngredientes);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		recetaService.eliminarReceta(id);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/cargar/{imagen}")
	 public ResponseEntity<Resource> goImage(@PathVariable String imagen){
		Resource resource = null;
		try {
			resource = uploadFile.load(imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagen=\""+resource.getFilename()+"\"")
				.body(resource);
		
	}
	
	@GetMapping("/ver/{id}")
	public ModelAndView mostrarReceta(@PathVariable(value = "id")Long id){
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", true);
		modelAndView.addObject("listaReceta", false);

		return modelAndView;
	}
	
	@GetMapping("/lista")
	public String mostrarRecetas(Model model) {
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		return "recetas";
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView verReceta(@PathVariable(value = "id")Long id){
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		modelAndView.addObject("gestion", false);
		modelAndView.addObject("listaReceta", true);
		return modelAndView;
	}
	
}
