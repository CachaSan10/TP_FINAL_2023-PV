package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;

@Service("recetaServiceMysqlImp")
public class RecetaServiceMysqlImp implements IRecetaService {

	@Autowired
	private Receta receta;
	
	@Autowired
	private IRecetaRepository recetaRepository;
	
	@Override
	public Receta obtenerReceta() {
		return receta;
	}

	@Override
	public void guardarReceta(Receta receta) {
		receta.setEstado(true);
		recetaRepository.save(receta);
	}

	@Override
	public List<Receta> obtenerRecetas() {
		return recetaRepository.findByEstado(true);
	}

	@Override
	public void eliminarReceta(Long id) {
		Receta unaReceta = new Receta();
		unaReceta = buscarReceta(id);
		unaReceta.setEstado(false);
		recetaRepository.save(unaReceta);
	}

	@Override
	public Receta buscarReceta(Long id) {
		return recetaRepository.findById(id).get();
	}

	@Override
	public void modificarReceta(Receta recetaModificada) {
		recetaRepository.save(recetaModificada);
	}

	@Override
	public List<Receta> filtrarRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

}
