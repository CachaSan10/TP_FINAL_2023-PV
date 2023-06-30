package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.service.IRecetaService;

@Controller
@RequestMapping("/receta")
public class RecetaController {

	@Autowired
	@Qualifier("recetaServiceMysqlImp")
	private IRecetaService recetaService;
	
	
}
