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
	public void agregarTestimonio(Testimonio testimonio) {
		testimonioRepository.save(testimonio);
		
	}

	@Override
	public List<Testimonio> obtenerTodosLosTestimonios() {
		List<Testimonio> testimonios = (List<Testimonio>) testimonioRepository.findAll();
		return testimonios;
	}

	@Override
	public Testimonio findTestimonioById(Long id) {
		testimonio = testimonioRepository.findById(id).get();
		return testimonio;
	}
	
	
	
}
