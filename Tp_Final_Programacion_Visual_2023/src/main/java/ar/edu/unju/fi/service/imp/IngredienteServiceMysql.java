package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;

@Service("ingredienteServiceMysql")
public class IngredienteServiceMysql implements IIngredienteService {

	@Autowired
	private Ingrediente ingrediente;

	@Autowired
	private IIngredienteRepository ingredienteRepository;

	@Override
	public Ingrediente obtenerIngrediente() {
		return ingrediente;
	}

	@Override
	public List<Ingrediente> obtenerIngredientes() {
		return ingredienteRepository.findByEstado(true);
	}

	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public void modificarIngrediente(Ingrediente ingredienteModificado) {
		ingredienteRepository.save(ingredienteModificado);
	}

	@Override
	public Ingrediente buscarIngrediente(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	@Override
	public void eliminarIngrediente(Long id) {
		Ingrediente unIngrediente = new Ingrediente();
		unIngrediente = buscarIngrediente(id);
		unIngrediente.setEstado(false);
		ingredienteRepository.save(unIngrediente);
	}

}
