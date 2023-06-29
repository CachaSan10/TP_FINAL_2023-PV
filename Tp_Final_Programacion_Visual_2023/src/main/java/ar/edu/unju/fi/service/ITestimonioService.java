package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {

	void agregarTestimonio(Testimonio testimonio);

	void guardarTestimonio(Testimonio testimonio);
	
	void modificarTestimonio(Testimonio testimoino);

	void eliminarTestimonio(int id);

	List<Testimonio> obtenerTodosLosTestimonios();

}
