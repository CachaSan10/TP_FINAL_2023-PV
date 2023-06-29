package ar.edu.unju.fi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.util.UploadFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/testimonio")

public class TestimonioController {
	
	@Autowired
	@Qualifier("testimonioServiceImp")
	private ITestimonioService testimonioService;
	
	
	@Autowired
	private UploadFile uploadFile;
	
	@GetMapping("listado")
	public String getListaTestimonio(Model model) {
		model.addAttribute("testimonio", testimonioService.obtenerTodosLosTestimonios());
		return "testimonio";
	}
	
	@GetMapping("/nuevo")
	public String getAgregarTestimonioPage(Model model) {
		boolean edicion=false;
		model.addAttribute("consejo", testimonioService.obtenerTodosLosTestimonios());
		model.addAttribute("edicion", edicion);
		return "nuevo_testimonio";
	}
	
	@PostMapping("/guardar")
	public ModelAndView agregarTestimonio(@Valid @ModelAttribute("testimonio") Testimonio testimonio, BindingResult result,
			@RequestParam("file") MultipartFile imagen ) throws IOException {
		ModelAndView  modelAndView = new ModelAndView("gestion_testimonio");
		if(result.hasErrors()) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("testimonio", testimonio);
			return modelAndView;
		}
		
		testimonioService.agregarTestimonio(testimonio);
		modelAndView.addObject("testimonio", testimonioService.obtenerTodosLosTestimonios());
		
		return modelAndView;
	}
	
}
