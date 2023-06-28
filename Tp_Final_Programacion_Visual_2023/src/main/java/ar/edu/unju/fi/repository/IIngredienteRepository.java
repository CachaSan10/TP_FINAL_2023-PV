package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Ingrediente;
import java.util.List;


@Repository
public interface IIngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	public List<Ingrediente> findByEstado(boolean estado);

}
