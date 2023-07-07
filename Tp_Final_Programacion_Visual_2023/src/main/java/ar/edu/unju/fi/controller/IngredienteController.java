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

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.imp.RecetaServiceMysqlImp;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IIngredienteService ingredienteService;
	
	@Autowired
	private RecetaServiceMysqlImp recetaService;
	
	
	@GetMapping("/gestion")
	public ModelAndView obtenerPaginaGestionIngrediente() {
		ModelAndView modelAndView = new ModelAndView("gestion_datos_ingrediente");
		modelAndView.addObject("ingredientes", ingredienteService.obtenerIngredientes());
		return modelAndView;
	}
	
	@GetMapping("/nuevo")
	public String getNuevoIngredientePage(Model model) {
		boolean edicion=false;
		model.addAttribute("ingrediente", ingredienteService.obtenerIngrediente());
		model.addAttribute("recetas", recetaService.obtenerRecetas());
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarIngredientePage(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult ) {
		ModelAndView mav = new ModelAndView("redirect:/ingrediente/gestion");
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("nuevo_ingrediente");
			mav.addObject("recetas", recetaService.obtenerRecetas());
			mav.addObject("edicion", false);
			return mav;
		}
		
		ingredienteService.guardarIngrediente(ingrediente);
		mav.setViewName("redirect:/ingrediente/gestion");
		return mav;
	}
	
	@GetMapping("/modificar/{id}")
	public String getModificarIngredientePage(Model model, @PathVariable(value = "id")Long id) {
		boolean edicion=true;
		Ingrediente ingredienteEncontrado = ingredienteService.buscarIngrediente(id);
		model.addAttribute("recetas", recetaService.obtenerRecetas());

		model.addAttribute("ingrediente", ingredienteEncontrado);
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingredienteModificado, BindingResult resultado,Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("ingrediente",  ingredienteModificado);
			model.addAttribute("recetas", recetaService.obtenerRecetas());

			return "nuevo_ingrediente";
		}
		ingredienteService.modificarIngrediente(ingredienteModificado);
		return "redirect:/ingrediente/gestion";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		Ingrediente ingredienteEncontrado= ingredienteService.buscarIngrediente(id);
		ingredienteService.eliminarIngrediente(ingredienteEncontrado);
		return "redirect:/ingrediente/gestion";
	}
	
	@GetMapping("/listado")
	public String getIngredientePage(Model model) {
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes());
		return "ingredientes";
	}
	

	

	

}




















