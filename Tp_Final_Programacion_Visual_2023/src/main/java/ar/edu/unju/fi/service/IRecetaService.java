package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Receta;

public interface IRecetaService {

	public Receta obtenerReceta();
	public void guardarReceta(Receta receta);
	public List<Receta> obtenerRecetas();
	public void eliminarReceta(Long id);
	public Receta buscarReceta(Long id);
	public void modificarReceta(Receta recetaModificada);
	public List<Receta> filtrarRecetaCategoria(String categoria);
}
