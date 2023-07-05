package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	public Testimonio findTestimonioById(Long id);

	List<Testimonio> obtenerListaTestimonio();
	
	public void guardarTestimonio(Testimonio testimonio);
	
	public void modificarTestimonio(Testimonio testimonioModificado);
	
	public void eliminarTestimonio(Long id);
	
	public Testimonio buscarTestimonio(Long id);
	
	public Testimonio obtenerTestimonio();
	
}
