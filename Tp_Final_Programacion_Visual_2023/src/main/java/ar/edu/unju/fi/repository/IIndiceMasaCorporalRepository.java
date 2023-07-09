package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

import java.util.List;


@Repository
public interface IIndiceMasaCorporalRepository extends CrudRepository<IndiceMasaCorporal, Long>  {
	
	/**
	 * Metodo obtiene la lista de acuerdo al usuario y al estado del indice de masa corporal
	 * @param usuario representa el usuario que esta vinculado la lista de indice de masa corporal
	 * @param estado representa el estado boolean de indice de masa corporal
	 * @return la lista de indice de masa corporal
	 */
	public List<IndiceMasaCorporal> findByUsuarioAndEstado(Usuario usuario, boolean estado);
	
	/**
	 * Metodo que obtiene la lista de dependiendo el estado de indice de masa corporal
	 * @param estado representa estado de indice de masa corporal
	 * @return la lista de indice de masa corporal
	 */
	public List<IndiceMasaCorporal> findByEstado(boolean estado);
}
