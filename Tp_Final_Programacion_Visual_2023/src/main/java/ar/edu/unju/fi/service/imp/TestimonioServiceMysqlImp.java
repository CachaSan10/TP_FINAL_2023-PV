package ar.edu.unju.fi.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;


@Service("testimonioServiceMysqlImp")
public class TestimonioServiceMysqlImp implements ITestimonioService{
    
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	
	@Override
	public List<Testimonio> obtenerListaTestimonio() {
		return testimonioRepository.findByEstado(true);
	}
	
	@Override
	public void guardarTestimonio(Testimonio testimonio) {
		// TODO Auto-generated method stub
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
	}

	@Override
	public void modificarTestimonio(Testimonio testimonioModificado) {
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
	
}
