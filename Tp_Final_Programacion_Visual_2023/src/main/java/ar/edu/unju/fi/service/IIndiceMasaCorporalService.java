package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;

public interface IIndiceMasaCorporalService {
	
	public IndiceMasaCorporal obtenerIndiceMasaCorporal();
	public void guardarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporal, Long idUsuario);
	public List<IndiceMasaCorporal> obtenerFechasImcDescreciente(Long id);
	public void modificarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporalModificado);
	public void eliminarIndiceMasaCorporal(Long id);
	public IndiceMasaCorporal buscarIndiceMasaCorporal(Long id);
	public String calcularImc(IndiceMasaCorporal imc);
	public double obtenerPesoIdeal(Long id);

}
