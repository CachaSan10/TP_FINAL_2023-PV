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

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.service.IIndiceMasaCorporalService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/imc")
public class IndiceMasaCorporalController {

	@Autowired
	@Qualifier("indiceMasaCorporalServiceMysqlImp")
	private IIndiceMasaCorporalService indiceMasaCorporalService;
	
	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	
	@GetMapping("/calculadora")
	public String getCalculadoraImcPage(Model model) {
		boolean existeUsuario=true;

		model.addAttribute("indiceMasaCorporal", indiceMasaCorporalService.obtenerIndiceMasaCorporal());
		model.addAttribute("existeUsuario", existeUsuario);
		return "calculadora-imc";
	}
	
	@PostMapping("/calcular-imc")
	public ModelAndView calcularImc(@Valid @ModelAttribute("indiceMasaCorporal") IndiceMasaCorporal imc ,BindingResult result,
			Long idUsuario) {
		ModelAndView modelAndView = new ModelAndView("resultado-imc");
		boolean existeUsuario;
		if(usuarioService.existeUsuario(idUsuario)) {
			existeUsuario = true;
			if(result.hasErrors()) {
				modelAndView.addObject("existeUsuario", existeUsuario);
				modelAndView.setViewName("calculadora-imc");
				modelAndView.addObject("indiceMasaCorporal", imc);
				return modelAndView;
			}else {
			indiceMasaCorporalService.guardarIndiceMasaCorporal(imc, idUsuario);
			modelAndView.addObject("existeUsuario", existeUsuario);
			modelAndView.addObject("usuario", imc.getUsuario());
			modelAndView.addObject("resultado", indiceMasaCorporalService.calcularImc(imc));
			}
		}else {
			existeUsuario=false;
			if(result.hasErrors()) {
				modelAndView.addObject("existeUsuario", existeUsuario);
				modelAndView.setViewName("calculadora-imc");
				modelAndView.addObject("indiceMasaCorporal", imc);
				return modelAndView;
			}
		modelAndView.addObject("existeUsuario", existeUsuario);
		modelAndView.setViewName("calculadora-imc");
		}
		/**
		if(result.hasErrors()) {
			modelAndView.setViewName("calculadora-imc");
			modelAndView.addObject("indiceMasaCorporal", imc);
			return modelAndView;
		}**/
		return modelAndView;
	}
	

 	
	@GetMapping("/registros")
	public String obtenerPaginaRegistroImc(Model model) {
		boolean existeUsuario=true;
		boolean bandDatos = false;
		model.addAttribute("existeUsuario", existeUsuario);
		model.addAttribute("mostrarDatos", bandDatos);
		return "registros-imc";
	}
	
	@GetMapping("/obtener-registros-imc")
	public String obtenerRegistrosImc(Model model, Long idUsuario) {
		boolean existeUsuario;
		if(usuarioService.existeUsuario(idUsuario)) {
			existeUsuario = true;
			model.addAttribute("existeUsuario", existeUsuario);
			model.addAttribute("indicesMasaMuscular", indiceMasaCorporalService.obtenerFechasImcDescreciente(idUsuario));
			model.addAttribute("usuario", usuarioService.buscarUsuario(idUsuario));
			model.addAttribute("mostrarDatos", true);


		}else {
			existeUsuario=false;
			model.addAttribute("existeUsuario", existeUsuario);
			model.addAttribute("mostrarDatos", false);
			model.addAttribute("usuario", null);
			model.addAttribute("indicesMasaMuscular", null);
		}
		return "registros-imc";
	}
	
	@GetMapping("/peso-ideal")
	public String getPesoIdelaPage() {
		return "peso-ideal";
	}
	
	@GetMapping("/buscador")
	public String obtenerPaginaBuscadorPesoIdeal(Model model) {
		boolean existeUsuario=true;
		model.addAttribute("existeUsuario", existeUsuario);
		return "buscador-peso-ideal";
	}
	
	@GetMapping("/buscar-peso-ideal")
	public ModelAndView buscarPesoIdeal(Long idUsuario) {
		boolean existeUsuario=true;
		ModelAndView modelAndView = new ModelAndView();

		if(usuarioService.existeUsuario(idUsuario)) {
				modelAndView.setViewName("peso-ideal");
				modelAndView.addObject("pesoIdeal",indiceMasaCorporalService.obtenerPesoIdeal(idUsuario) );
				modelAndView.addObject("usuario", usuarioService.buscarUsuario(idUsuario));

		}else {
			existeUsuario = false;
			modelAndView.addObject("existeUsuario", existeUsuario);
			modelAndView.setViewName("buscador-peso-ideal");
		}
		return modelAndView;
	}
	
	@GetMapping("/gestion")
	public String obtenerPaginaGestionIndiceMasaCorporal(Model model) {
		model.addAttribute("indicesMasaCorporal",indiceMasaCorporalService.obtenerIndicesMasaCorporal());
		return "gestion_datos_imc";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		indiceMasaCorporalService.eliminarIndiceMasaCorporal(id);
		return "redirect:/imc/gestion";
	}
}
