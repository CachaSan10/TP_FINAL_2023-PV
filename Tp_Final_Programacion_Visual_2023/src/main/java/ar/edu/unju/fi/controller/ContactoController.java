package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactoController {
	
	/**
     * Maneja la solicitud GET para la página de contacto.
     *
     * @return El nombre de la vista "contacto" para mostrar la página de contacto.
     */
	@GetMapping("/contacto")
	public String getContactoPage() {
		return "contacto";
	}
}

