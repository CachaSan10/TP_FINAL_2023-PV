package ar.edu.unju.fi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	public Testimonio findTestimonioById(Long id);

	List<Testimonio> obtenerListaTestimonio();
	
	public void modificarTestimonio(Testimonio testimonioModificado, MultipartFile imagen) throws IOException;
	
	public void eliminarTestimonio(Long id) throws NotFoundException;
	
	public Testimonio buscarTestimonio(Long id);
	
	public Testimonio obtenerTestimonio();

	public boolean existeTestimonio(Long idUsuLong);

	void guardarTestimonio(Testimonio testimonio, MultipartFile imagen) throws IOException;
	
	public boolean existeTestimonioRegistrado(Long usuarioId);

	Testimonio buscarTestimonioPorUsuarioId(Long usuarioId);
	
}
