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
	

	
	
	/**
	 * Metodo que retorna el indice de masa coroporal
	 * @return el indice de masa corporal
	 */
	@Override
	public IndiceMasaCorporal obtenerIndiceMasaCorporal() {
		return indiceMasaCorporal;
	}

	/**
	 * Metodo que guarda el indice de masa corporal en la base de datos
	 * @param indiceMasaCorporal representa el indice de masa de corporal
	 * @param idUsuario representa el id del usuario que se vincula con la masa muscular
	 */
	@Override
	public void guardarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporal, Long idUsuario) {
			indiceMasaCorporal.setEstado(true);
			indiceMasaCorporal.setUsuario(usuarioService.buscarUsuario(idUsuario));
			indiceMasaCorporalRepository.save(indiceMasaCorporal);
			
	}

	/**
	 * Metodo que modifica el indice de masa corporal
	 * @param indiceMasaCorporalModificado representa el indice de masa corporal modificado
	 */
	@Override
	public void modificarIndiceMasaCorporal(IndiceMasaCorporal indiceMasaCorporalModificado) {
		indiceMasaCorporalRepository.save(indiceMasaCorporalModificado);
	}

	/**
	 * Metodo que elimina el indice de masa corporal de la base de datos
	 * @param id representa el id de masa corporal que se desea eliminar
	 */
	@Override
	public void eliminarIndiceMasaCorporal(Long id) {
		IndiceMasaCorporal unIndiceMasaCorporal = new IndiceMasaCorporal();
		unIndiceMasaCorporal = buscarIndiceMasaCorporal(id);
		unIndiceMasaCorporal.setEstado(false);
		indiceMasaCorporalRepository.save(unIndiceMasaCorporal);
	}

	/**
	 * Metodo que busca el indice de masa corporal por el id
	 * @param id representa el id del indice de masa corporal que se desea eliminar
	 * @return el indice de masa corporal encontrado
	 */
	@Override
	public IndiceMasaCorporal buscarIndiceMasaCorporal(Long id) {
		return indiceMasaCorporalRepository.findById(id).get();
	}

	

	/**
	 * Metodo que calcula el peso ideal del usuario
	 * @param id representa el id del usuario que quiere calcular su peso ideal
	 * @return el resultado del peso ideal del usuario
	 */
	@Override
	public double obtenerPesoIdeal(Long id) {
		Usuario dosUsuario = new Usuario();
		dosUsuario = usuarioService.buscarUsuario(id);
			int estaturaConvertido = (int) (dosUsuario.getEstatura() * 100);
			return (double)(estaturaConvertido - 100 + ((dosUsuario.calcularAnio()/10)*0.9)); 
		

	}
	

	/**
	 * Metodo que devuelve la lista de indice de masa muscular de manera descreciente segun la fecha
	 * @param id representa el id del usuario al cual le corresponde la lista de indice de masa corporal
	 * @return la lista de indice de masa corporal de manera descreciente de acuerdo a la fecha
	 */
	@Override
	public List<IndiceMasaCorporal> obtenerFechasImcDescreciente(Long id) {
		List<IndiceMasaCorporal> listaImc = new ArrayList<IndiceMasaCorporal>();
		Usuario tresUsuario = new Usuario();
		tresUsuario = usuarioService.buscarUsuario(id);
		listaImc = indiceMasaCorporalRepository.findByUsuarioAndEstado(tresUsuario, true);
		listaImc.sort((f1,f2) -> f2.getFechaImc().compareTo(f1.getFechaImc()));
		
		return listaImc;
	}

	/**
	 * Metodo que calcula el indice de masa corporal
	 * @param imc representa el indice de masa coporal a buscar por el id para calcular
	 * @return el resultado del indice de masa corporal del usuario
	 */
	@Override
	public String calcularImc(IndiceMasaCorporal imc) {
	 return indiceMasaCorporalRepository.findById(imc.getId()).get().calcularImc();	
	}

	/**
	 * Metodo que devuelve la lista de indice de masa corporal 
	 * @return la lista de indice de masa corporal
	 */
	@Override
	public List<IndiceMasaCorporal> obtenerIndicesMasaCorporal() {
		return indiceMasaCorporalRepository.findByEstado(true);
	}


}
