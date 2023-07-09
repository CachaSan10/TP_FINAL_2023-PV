package ar.edu.unju.fi.service.imp;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.util.UploadFile;

/**
 * Implementación de la interfaz ITestimonioService que proporciona los métodos para el manejo de testimonios.
 */
@Service("testimonioServiceMysqlImp")
public class TestimonioServiceMysqlImp implements ITestimonioService{
    
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	@Autowired
	private UploadFile uploadFile;
	
	@Autowired
	private Usuario usuario;
	
	/**
     * Obtiene la lista de testimonios activos.
     *
     * @return La lista de testimonios activos.
     */
	@Override
	public List<Testimonio> obtenerListaTestimonio() {
		return testimonioRepository.findByEstado(true);
	}
	
	/**
     * Guarda un testimonio y su imagen asociada.
     *
     * @param testimonio El testimonio a guardar.
     * @param imagen La imagen asociada al testimonio.
     * @throws IOException Si ocurre un error durante la manipulación de archivos.
     */
	@Override
	public void guardarTestimonio(Testimonio testimonio, MultipartFile imagen) throws IOException {
		// TODO Auto-generated method stub
		String testimonioImagen = uploadFile.copy(imagen);
		testimonio.setImagen(testimonioImagen);
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
	}
	
	/**
     * Modifica un testimonio existente y actualiza su imagen asociada si se proporciona una nueva imagen.
     *
     * @param testimonioModificado El testimonio modificado.
     * @param imagen La nueva imagen asociada al testimonio (opcional).
     * @throws IOException Si ocurre un error durante la manipulación de archivos.
     */
	@Override
	public void modificarTestimonio(Testimonio testimonioModificado, MultipartFile imagen) throws IOException {
		// TODO Auto-generated method stub
		testimonioModificado.setEstado(true);
		
		
		 if (imagen != null && !imagen.isEmpty()) {
		        String imagenString = imagen.getOriginalFilename();

		        Testimonio testimonioExistente = buscarTestimonio(testimonioModificado.getId());
		        if (testimonioExistente != null && !imagenString.equals(testimonioExistente.getImagen())) {
		            uploadFile.delete(testimonioExistente.getImagen());
		            imagenString = uploadFile.copy(imagen);
		            testimonioModificado.setImagen(imagenString);
		        }
		    } else {
		        Testimonio testimonioExistente = buscarTestimonio(testimonioModificado.getId());
		        if (testimonioExistente != null) {
		            testimonioModificado.setImagen(testimonioExistente.getImagen());
		        }
		    }
		
		
		testimonioRepository.save(testimonioModificado);
		
	}
	
	 /**
     * Elimina un testimonio y su imagen asociada por su ID.
     *
     * @param id El ID del testimonio a eliminar.
     * @throws NotFoundException Si el testimonio no se encuentra.
     */
	@Override
	public void eliminarTestimonio(Long id) throws NotFoundException {
		Testimonio unTestimonio = testimonioRepository.findById(id)
	            .orElseThrow(() -> new NotFoundException());

	    uploadFile.delete(unTestimonio.getImagen());
	    testimonioRepository.delete(unTestimonio);
	}
	
	/**
     * Busca un testimonio por su ID.
     *
     * @param id El ID del testimonio a buscar.
     * @return El testimonio encontrado.
     */
	@Override
	public Testimonio buscarTestimonio(Long id) {
		return testimonioRepository.findById(id).get();
		
	}
	
	/**
     * Busca un testimonio por su ID.
     *
     * @param id El ID del testimonio a buscar.
     * @return El testimonio encontrado.
     */
	@Override
	public Testimonio findTestimonioById(Long id) {
		testimonio = testimonioRepository.findById(id).get();
		return testimonio;
	}
	
	/**
     * Obtiene el testimonio actualmente en uso.
     *
     * @return El testimonio actualmente en uso.
     */
	@Override
	public Testimonio obtenerTestimonio() {
		return testimonio;
	}
	
	/**
     * Verifica si existe un testimonio con el ID especificado.
     *
     * @param idUsuLong El ID del testimonio a verificar.
     * @return true si el testimonio existe, false en caso contrario.
     */
	@Override
	public boolean existeTestimonio(Long idUsuLong) {
		boolean existe = false;
		for (Testimonio testimonio : obtenerListaTestimonio()) {
			if (testimonio.getId() == idUsuLong) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	/**
     * Verifica si existe un testimonio registrado por el ID de usuario especificado.
     *
     * @param usuarioId El ID del usuario.
     * @return true si existe un testimonio registrado por el usuario, false en caso contrario.
     */
	@Override
	public boolean existeTestimonioRegistrado(Long usuarioId) {
	    return testimonioRepository.existsByUsuarioId(usuarioId);
	}
	
	@Override
	public Testimonio buscarTestimonioPorUsuarioId(Long usuarioId) {
		return testimonioRepository.findByUsuarioId(usuarioId);
	}

	
}
