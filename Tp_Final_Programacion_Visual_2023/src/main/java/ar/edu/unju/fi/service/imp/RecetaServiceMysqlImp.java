package ar.edu.unju.fi.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.util.UploadFile;

@Service("recetaServiceMysqlImp")
public class RecetaServiceMysqlImp implements IRecetaService {

	/**
	 * Inyeccion del objeto Receta
	 */
	@Autowired
	private Receta receta;
	
	@Autowired
	private UploadFile uploadFile;
	
	/**
	 * Inyeccion de la interfaz receta repository
	 */
	@Autowired
	private IRecetaRepository recetaRepository;
	
	@Autowired
	@Qualifier("ingredienteServiceMysqlImp")
	private IngredienteServiceMysqlImp ingredienteService;
	
	/**
	 * Metodo que retorna objeto receta
	 */
	@Override
	public Receta obtenerReceta() {
		return receta;
	}

	/**
	 * Metodo para guardar un receta
	 */
	@Override
	public void guardarReceta(Receta receta, MultipartFile imagen,Long[] idIngredientes) throws IOException {
		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		
		if(idIngredientes.length==0) {
		listaIngrediente=null;	
		}else {
			for(int i=0;i<idIngredientes.length;i++) {
				listaIngrediente.add(ingredienteService.buscarIngrediente(idIngredientes[i]));
			}
		}
		
		
		String nombreImagen = uploadFile.copy(imagen);
		receta.setImagen(nombreImagen);
		receta.setEstado(true);
		receta.setIngredientes(listaIngrediente);
		recetaRepository.save(receta);
	}

	/**
	 * Metodo para obtener la lista de recetas
	 * cuyo estado sea true (activo)
	 */
	@Override
	public List<Receta> obtenerRecetas() {
		return recetaRepository.findByEstado(true);
	}

	/**
	 * Eliminacion logica de receta
	 * cambiando el atributo  estado a falso
	 */
	@Override
	public void eliminarReceta(Long id) {
		Receta unaReceta = new Receta();
		unaReceta = buscarReceta(id);
		unaReceta.setEstado(false);
		recetaRepository.save(unaReceta);
	}

	/**
	 * Metodo que retorna  receta por id
	 */
	@Override
	public Receta buscarReceta(Long id) {
		return recetaRepository.findById(id).get();
	}

	/**
	 * Metodo para modificar usuario
	 */
	@Override
	public void modificarReceta(Receta recetaModificada, MultipartFile imagenModificada,Long[] idIngredientes) throws IOException {
		recetaModificada.setEstado(true);
		
			List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		
		if(idIngredientes.length==0) {
		listaIngrediente=buscarReceta(recetaModificada.getId()).getIngredientes();	
		}else {
			for(int i=0;i<idIngredientes.length;i++) {
				System.out.println("id: "+idIngredientes[i]);
				listaIngrediente.add(ingredienteService.buscarIngrediente(idIngredientes[i]));
			}
		}
		
		recetaModificada.setIngredientes(listaIngrediente);
		if (!imagenModificada.isEmpty()) {
			String imagenString = imagenModificada.getOriginalFilename();

			if (imagenString.compareTo(buscarReceta(recetaModificada.getId()).getImagen()) != 0) {
				uploadFile.delete(buscarReceta(recetaModificada.getId()).getImagen());
				imagenString = uploadFile.copy(imagenModificada);
				recetaModificada.setImagen(imagenString);
			}
		}else {
			recetaModificada.setImagen(buscarReceta(recetaModificada.getId()).getImagen());
		}
		
		recetaRepository.save(recetaModificada);

	}

	/**
	 * Metodo que filtra recetas por su categoria
	 */
	@Override
	public List<Receta> filtrarRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

	



}
