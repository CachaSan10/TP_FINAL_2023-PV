package ar.edu.unju.fi.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;

public interface IIndiceMasaCorporalService {
	
	public List<IndiceMasaCorporal> obtenerIndicesMasaCorporal();
	public IndiceMasaCorporal obtenerIndiceMasaCorporal();
	public void guardarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporal);
	public List<IndiceMasaCorporal> filtarFechaImc(LocalDate fechaInicio, LocalDate fechaFin);
	public void modificarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporalModificado);
	public void eliminarIndiceMasaCorporal(Long id);
	public IndiceMasaCorporal buscarIndiceMasaCorporal(Long id);

}
