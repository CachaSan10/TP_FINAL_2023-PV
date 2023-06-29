package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;

public class TestimonioServiceImp implements ITestimonioService{
	
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	
	@Override
	public void agregarTestimonio(Testimonio testimonio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarTestimonio(Testimonio testimonio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarTestimonio(Testimonio testimoino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTestimonio(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Testimonio> obtenerTodosLosTestimonios() {
		// TODO Auto-generated method stub
		return null;
	}

}
