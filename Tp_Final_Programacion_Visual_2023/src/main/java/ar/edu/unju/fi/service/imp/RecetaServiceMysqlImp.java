package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;

@Service("recetaServiceMysqlImp")
public class RecetaServiceMysqlImp implements IRecetaService {

	/**
	 * Inyeccion del objeto Receta
	 */
	@Autowired
	private Receta receta;
	
	/**
	 * Inyeccion de la interfaz receta repository
	 */
	@Autowired
	private IRecetaRepository recetaRepository;
	
	/**
	 * Metodo que retorna objeto receta
	 */
	@Override
	public Receta obtenerReceta() {
		return receta;
	}

	/**
	 * Metodo para guardar un receta
	 */
	@Override
	public void guardarReceta(Receta receta) {
		receta.setEstado(true);
		recetaRepository.save(receta);
	}

	/**
	 * Metodo para obtener la lista de recetas
	 * cuyo estado sea true (activo)
	 */
	@Override
	public List<Receta> obtenerRecetas() {
		return recetaRepository.findByEstado(true);
	}

	/**
	 * Eliminacion logica de receta
	 * cambiando el atributo  estado a falso
	 */
	@Override
	public void eliminarReceta(Long id) {
		Receta unaReceta = new Receta();
		unaReceta = buscarReceta(id);
		unaReceta.setEstado(false);
		recetaRepository.save(unaReceta);
	}

	/**
	 * Metodo que retorna  receta por id
	 */
	@Override
	public Receta buscarReceta(Long id) {
		return recetaRepository.findById(id).get();
	}

	/**
	 * Metodo para modificar usuario
	 */
	@Override
	public void modificarReceta(Receta recetaModificada) {
		recetaModificada.setEstado(true);
		recetaRepository.save(recetaModificada);
	}

	/**
	 * Metodo que filtra recetas por su categoria
	 */
	@Override
	public List<Receta> filtrarRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

}
