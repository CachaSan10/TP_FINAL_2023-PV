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

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
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
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("receta") Receta receta, BindingResult result) {
		ModelAndView mav = new ModelAndView("redirect:/receta/gestion");
		
		if (result.hasErrors()) {
			mav.setViewName("nueva_receta");
			mav.addObject("ingredientes", ingredienteService.obtenerIngredientes());
			mav.addObject("edicion", false);
			return mav;
		}
		recetaService.guardarReceta(receta);
		return mav;
	}
	
	@GetMapping("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id")Long id) {
		boolean edicion=true;
		model.addAttribute("receta", recetaService.buscarReceta(id));
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("receta") Receta recetaModificada, BindingResult result) {
		if (result.hasErrors()) {
			return "nueva_receta";
		}
		recetaService.modificarReceta(recetaModificada);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		recetaService.eliminarReceta(id);
		return "redirect:/receta/gestion";
	}
	
	@GetMapping("/ver/{id}")
	public ModelAndView mostrarReceta(@PathVariable(value = "id")Long id){
		ModelAndView modelAndView = new ModelAndView("receta");
		modelAndView.addObject("receta", recetaService.buscarReceta(id));
		return modelAndView;
	}
	
}
