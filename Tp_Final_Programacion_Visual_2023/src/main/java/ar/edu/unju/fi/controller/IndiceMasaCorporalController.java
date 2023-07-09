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
	
	/**
	 * Metodo que devuelve la pagina que calcula el indice de masa corporal
	 * @param model representa la clase que envia indice de masa corporal y existe usuario
	 * @return la pagina calculadora-imc.html
	 */
	@GetMapping("/calculadora")
	public String getCalculadoraImcPage(Model model) {
		boolean existeUsuario=true;

		model.addAttribute("indiceMasaCorporal", indiceMasaCorporalService.obtenerIndiceMasaCorporal());
		model.addAttribute("existeUsuario", existeUsuario);
		return "calculadora-imc";
	}
	
	/**
	 * Metodo que guarda el indice de masa corporal en la base de datos
	 * @param imc representa el indice de masa corporal que se va a guardar
	 * @param result representa el resultado de las validacione en el formulario
	 * @param idUsuario representa el id del usuario que se envia desde el formulario atraves del atributo name
	 * @return la pagina resultado-imc.html en caso que se hayan cumplido las validaciones o
	 * calculadora-imc en caso contrario
	 */
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
			imc.setUsuario(usuarioService.buscarUsuario(idUsuario));
			indiceMasaCorporalService.guardarIndiceMasaCorporal(imc);
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
	
		return modelAndView;
	}
	
	/**
	 * Metodo que obtiene el formulario para modificar el indice de masa corporal
	 * @param model representa la clase que envia el indice de masa corporal
	 * @param id representa el id del indice de masa corporal
	 * @return la pagina gestion_datos_imc_modificar.html
	 */
	@GetMapping("/modificar/{id}")
	public String obtenerModificarIndiceMasaCorporalPage(Model model, @PathVariable(value = "id")Long id) {
		model.addAttribute("indiceMasaCorporal", indiceMasaCorporalService.buscarIndiceMasaCorporal(id));
		return "gestion_datos_imc_modificar";
	}
	
	/**
	 * Metodo que modifica el indice de masa corporal
	 * @param indiceMasaCorporalModificado representa el indice de masa corporal modificado
	 * @param resultado representa el resultado de las validacione en el formulario
	 * @param model representa la clase que envia el indice de masa corporal
	 * @return la pagina de gestion de indice de masa corporal en caso que se hayan cumplido las validaciones o
	 * el formulario en caso contrario
	 */
	@PostMapping("/modificar/{id}")
	public String modificarIndiceMasaCorporal(@Valid @ModelAttribute("indiceMasaCorporal") IndiceMasaCorporal indiceMasaCorporalModificado, BindingResult resultado,Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("indiceMasaCorporal", indiceMasaCorporalModificado);
			return "gestion_datos_imc_modificar";
		}
		indiceMasaCorporalService.modificarIndiceMasaCorporal(indiceMasaCorporalModificado);
		return "redirect:/imc/gestion";
		
	}

 	/**
 	 * Metodo que devuelve la pagina de registro
 	 * @param model representa la clase que envia existeUsuario, mostrarDatos
 	 * @return la pagina registros-imc.html
 	 */
	@GetMapping("/registros")
	public String obtenerPaginaRegistroImc(Model model) {
		boolean existeUsuario=true;
		boolean bandDatos = false;
		model.addAttribute("existeUsuario", existeUsuario);
		model.addAttribute("mostrarDatos", bandDatos);
		return "registros-imc";
	}
	
	/**
	 * Metodo que devuelve la lista de imc registrado
	 * @param model representa la clase que envia existeUsuario, mostrarDatos,indicesMasaMuscular,usuario
	 * @param idUsuario  representa el id del usuario que se envia desde el formulario atraves del atributo name
	 * @return la pagina registros-imc.html
	 */
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
	
	/**
	 * Metodo que devuelve el resultado de peso ideal del usuario
	 * @return la pagina peso-ideal.html
	 */
	@GetMapping("/peso-ideal")
	public String getPesoIdelaPage() {
		return "peso-ideal";
	}
	
	/**
	 * Metodo que devuelve la pagina de peso ideal
	 * @param model representa la clase que envia existeUsuario
	 * @return
	 */
	@GetMapping("/buscador")
	public String obtenerPaginaBuscadorPesoIdeal(Model model) {
		boolean existeUsuario=true;
		model.addAttribute("existeUsuario", existeUsuario);
		return "buscador-peso-ideal";
	}
	
	/**
	 * Metodo que devuelve el resultado del peso ideal del usuario
	 * @param idUsuario representa el id del usuario que se envia desde el formulario atraves del atributo name
	 * @return la pagina de peso-ideal en caso de que el usuario no exista en caso contrario devuelve el resultado
	 */
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
	
	/**
	 * Metodo que retorna la pagina de gestion de datos de indice de masa corporal
	 * @param model representa la clase que envia indicesMasaMuscular
	 * @return la pagina gestion_datos_imc.html
	 */
	@GetMapping("/gestion")
	public String obtenerPaginaGestionIndiceMasaCorporal(Model model) {
		model.addAttribute("indicesMasaCorporal",indiceMasaCorporalService.obtenerIndicesMasaCorporal());
		return "gestion_datos_imc";
	}
	
	/**
	 * Metodo que elimina el igrediente
	 * @param id representa el id del ingrediente que se quiere eliminar
	 * @return la pagina gestion de ingrediente
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id")Long id) {
		indiceMasaCorporalService.eliminarIndiceMasaCorporal(id);
		return "redirect:/imc/gestion";
	}
}
