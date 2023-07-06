package ar.edu.unju.fi.service.imp;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.util.UploadFile;


@Service("testimonioServiceMysqlImp")
public class TestimonioServiceMysqlImp implements ITestimonioService{
    
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	@Autowired
	private UploadFile uploadFile;
	
	
	@Override
	public List<Testimonio> obtenerListaTestimonio() {
		return testimonioRepository.findByEstado(true);
	}
	
	@Override
	public void guardarTestimonio(Testimonio testimonio, MultipartFile imagen) throws IOException {
		// TODO Auto-generated method stub
		String testimonioImagen = uploadFile.copy(imagen);
		testimonio.setImagen(testimonioImagen);
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
	}

	@Override
	public void modificarTestimonio(Testimonio testimonioModificado, MultipartFile imagen) throws IOException {
		// TODO Auto-generated method stub
		testimonioModificado.setEstado(true);
		testimonioRepository.save(testimonioModificado);
		
	}
	
	@Override
	public void eliminarTestimonio(Long id) {
		// TODO Auto-generated method stub
		Testimonio unTestimonio = new Testimonio();
		unTestimonio = buscarTestimonio(id);
		unTestimonio.setEstado(false);
		testimonioRepository.save(unTestimonio);
	}
	
	@Override
	public Testimonio buscarTestimonio(Long id) {
		return testimonioRepository.findById(id).get();
		
	}

	@Override
	public Testimonio findTestimonioById(Long id) {
		testimonio = testimonioRepository.findById(id).get();
		return testimonio;
	}

	@Override
	public Testimonio obtenerTestimonio() {
		// TODO Auto-generated method stub
		return testimonio;
	}

	@Override
	public boolean existeTestimonio(Long idUsuLong) {
		boolean existe = false;
		for (Testimonio testimonio : obtenerListaTestimonio()) {
			if (testimonio.getId() == idUsuLong) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
}
