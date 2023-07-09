package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	/**
     * Maneja la solicitud GET para la página de inicio.
     *
     * @return El nombre de la vista "index" para mostrar la página de inicio.
     */
	@GetMapping("/inicio")
	public String getIndexPage() {
		return "index";
	}
	
	
}
