package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioRepository extends CrudRepository<Testimonio, Long>{
	
	/**
	 * Metodo que obtiene la lista de testimonio dependiendo del estado
	 * @param estado representa un valor boolean ya sea true o false
	 * @return lista de testimonio
	 */
	public List<Testimonio> findByEstado(boolean estado);
	
	/**
	 * Metodo que retorna un boolean dependiendo de la existencia del usuario
	 * @param usuarioId representa el id del usuario
	 * @return true o false
	 */
	public boolean existsByUsuarioId(Long usuarioId);
	
	/**
	 * Metodo que busca Testimonio dependiendo el id del usuario
	 * @param usuarioId representa el id del usuario
	 * @return el testimonio
	 */
	public Testimonio findByUsuarioId(Long usuarioId);
}
