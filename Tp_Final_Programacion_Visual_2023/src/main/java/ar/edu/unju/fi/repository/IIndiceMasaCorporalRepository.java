package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface IIndiceMasaCorporalRepository extends CrudRepository<IndiceMasaCorporal, Long>  {
	
	
	public List<IndiceMasaCorporal> findByUsuarioAndEstado(Usuario usuario, boolean estado);
	public List<IndiceMasaCorporal> findByUsuario(Usuario usuario);
	public List<IndiceMasaCorporal> findByEstado(boolean estado);
}
