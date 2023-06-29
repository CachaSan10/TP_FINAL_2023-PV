package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface IIndiceMasaCorporalRepository extends CrudRepository<IndiceMasaCorporal, Long>  {
	
	public List<IndiceMasaCorporal> findByEstado(boolean estado); 
	public List<IndiceMasaCorporal> findByFechaImcBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
