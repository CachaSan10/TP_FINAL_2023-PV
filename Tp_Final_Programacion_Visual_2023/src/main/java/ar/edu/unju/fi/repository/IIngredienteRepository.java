package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Ingrediente;
import java.util.List;
import ar.edu.unju.fi.entity.Receta;

/**
 * Repositorio de datos para la entidad Ingrediente.
 */
@Repository
public interface IIngredienteRepository extends CrudRepository<Ingrediente, Long> {
	/**
     * Obtiene una lista de ingredientes por estado.
     *
     * @param estado El estado de los ingredientes a buscar.
     * @return La lista de ingredientes encontrados.
     */
	public List<Ingrediente> findByEstado(boolean estado);
}
