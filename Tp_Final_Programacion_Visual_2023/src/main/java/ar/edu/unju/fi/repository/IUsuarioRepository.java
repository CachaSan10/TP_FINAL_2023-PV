package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Usuario;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

	/**
	 * Metodo para obtener todos los usuarios por el estado
	 * @param estado
	 * @return
	 */
	public List<Usuario> findByEstado (boolean estado);
}
