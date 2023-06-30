package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
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
		model.addAttribute("indiceMasaCorporal", indiceMasaCorporalService.obtenerIndiceMasaCorporal());
		return "calculadora-imc";
	}
	
	@PostMapping("/calcular-imc")
	public ModelAndView getResultadoImcPage(@Valid @ModelAttribute("indiceMasaCorporal")IndiceMasaCorporal imc ,Long idUsuario,BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("calculadora-imc");
		if(usuarioService.existeUsuario(idUsuario)) {
			if(result.hasErrors()) {
				modelAndView.setViewName("calculadora-imc");
				modelAndView.addObject("indiceMasaCorporal", imc);
				return modelAndView;
			}else {
			indiceMasaCorporalService.guardarIndiceMasaCorporal(imc, idUsuario);
			}
		}
		return modelAndView;
	}
 	
	@GetMapping("/registros")
	public String getRegistroImcPage(Model model, Long idUsuario) {
		model.addAttribute("indiceMasaCorporal", indiceMasaCorporalService.obtenerFechasImcDescreciente(null));
		return "registros-imc";
	}
	
	
	
	@GetMapping("/peso-ideal")
	public String getPesoIdelaPage(Model model) {
		return "peso-ideal";
	}
}
