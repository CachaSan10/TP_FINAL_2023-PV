package ar.edu.unju.fi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	public List<Testimonio> obtenerTodosLosTestimonios();
	
	public Testimonio obtenerTestimonio();

	public void agregarTestimonio(Testimonio testimonio, MultipartFile imagen) throws IOException;
	
	public Testimonio obtenerTestimonioEncontrado(Long id);
	
	public void actualizarTestimonio(Testimonio testimonioModificado, MultipartFile imagen) throws IOException;
	
	public void eliminarTestimonio(Long id);
	
}
