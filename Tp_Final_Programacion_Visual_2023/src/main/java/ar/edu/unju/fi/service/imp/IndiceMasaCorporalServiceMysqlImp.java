package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IIndiceMasaCorporalRepository;
import ar.edu.unju.fi.service.IIndiceMasaCorporalService;
import ar.edu.unju.fi.service.IUsuarioService;

@Service("indiceMasaCorporalServiceMysqlImp")
public class IndiceMasaCorporalServiceMysqlImp implements IIndiceMasaCorporalService{

	@Autowired
	private IIndiceMasaCorporalRepository indiceMasaCorporalRepository;
	
	@Autowired
	private IndiceMasaCorporal indiceMasaCorporal;
	
	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	

	@Override
	public IndiceMasaCorporal obtenerIndiceMasaCorporal() {
		return indiceMasaCorporal;
	}

	@Override
	public void guardarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporal, Long id) {
			indiceMasaCorporal.setEstado(true);
			indiceMasaCorporal.setUsuario(usuarioService.buscarUsuario(id));
			indiceMasaCorporalRepository.save(indiceMasaCorporal);
			
	}



	@Override
	public void modificarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporalModificado) {
		indiceMasaCorporalRepository.save(indiceMasaCorporalModificado);
	}

	@Override
	public void eliminarIndiceMasaCorporal(Long id) {
		IndiceMasaCorporal unIndiceMasaCorporal = new IndiceMasaCorporal();
		unIndiceMasaCorporal = buscarIndiceMasaCorporal(id);
		unIndiceMasaCorporal.setEstado(false);
		indiceMasaCorporalRepository.save(unIndiceMasaCorporal);
	}

	@Override
	public IndiceMasaCorporal buscarIndiceMasaCorporal(Long id) {
		return indiceMasaCorporalRepository.findById(id).get();
	}

	

	@Override
	public double obtenerPesoIdeal(Long id) {
		Usuario dosUsuario = new Usuario();
		dosUsuario = usuarioService.buscarUsuario(id);
			int estaturaConvertido = (int) (dosUsuario.getEstatura() * 100);
			return (double)(estaturaConvertido - 100 + ((dosUsuario.calcularAnio()/10)*0.9)); 
		

	}
	

	@Override
	public List<IndiceMasaCorporal> obtenerFechasImcDescreciente(Long id) {
		List<IndiceMasaCorporal> listaImc = new ArrayList<IndiceMasaCorporal>();
		Usuario tresUsuario = new Usuario();
		tresUsuario = usuarioService.buscarUsuario(id);
		listaImc = indiceMasaCorporalRepository.findByUsuarioAndEstado(tresUsuario, true);
		listaImc.sort((f1,f2) -> f2.getFechaImc().compareTo(f1.getFechaImc()));
		
		return listaImc;
	}

	@Override
	public String calcularImc(IndiceMasaCorporal imc) {
	 return indiceMasaCorporalRepository.findById(imc.getId()).get().calcularImc();	
	}


}
