package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	public void agregarTestimonio(Testimonio testimonio);
	
	public List<Testimonio> obtenerTodosLosTestimonios();
	
	public Testimonio findTestimonioById(Long id);
	
}
