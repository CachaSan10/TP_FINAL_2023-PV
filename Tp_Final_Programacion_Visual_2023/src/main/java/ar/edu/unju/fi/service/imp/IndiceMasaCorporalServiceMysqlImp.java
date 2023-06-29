package ar.edu.unju.fi.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.repository.IIndiceMasaCorporalRepository;
import ar.edu.unju.fi.service.IIndiceMasaCorporalService;

@Service("indiceMasaCorporalServiceMysqlImp")
public class IndiceMasaCorporalServiceMysqlImp implements IIndiceMasaCorporalService{

	@Autowired
	private IIndiceMasaCorporalRepository indiceMasaCorporalRepository;
	
	@Autowired
	private IndiceMasaCorporal indiceMasaCorporal;
	
	@Override
	public List<IndiceMasaCorporal> obtenerIndicesMasaCorporal() {
		return indiceMasaCorporalRepository.findByEstado(true);
	}

	@Override
	public IndiceMasaCorporal obtenerIndiceMasaCorporal() {
		return indiceMasaCorporal;
	}

	@Override
	public void guardarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporal) {
			indiceMasaCorporal.setEstado(true);
			indiceMasaCorporalRepository.save(indiceMasaCorporal);
	}

	@Override
	public List<IndiceMasaCorporal> filtarFechaImc(LocalDate fechaInicio, LocalDate fechaFin) {
		return indiceMasaCorporalRepository.findByFechaImcBetween(fechaInicio, fechaFin);
	}

	@Override
	public void modificarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporalModificado) {
		indiceMasaCorporalRepository.save(indiceMasaCorporalModificado);
	}

	@Override
	public void eliminarIndiceMasaCorporal(Long id) {
		IndiceMasaCorporal unIndiceMasaCorporal = new IndiceMasaCorporal();
		unIndiceMasaCorporal = buscarIndiceMasaCorporal(id);
		unIndiceMasaCorporal.setEstado(false);
		indiceMasaCorporalRepository.save(unIndiceMasaCorporal);
	}

	@Override
	public IndiceMasaCorporal buscarIndiceMasaCorporal(Long id) {
		return indiceMasaCorporalRepository.findById(id).get();
	}

}
