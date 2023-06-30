package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.service.IIngredienteService;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IIngredienteService ingredienteService;
}
