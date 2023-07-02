package ar.edu.unju.fi.service.imp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.util.UploadFile;


@Service("testimonioServiceMysqlImp")
public class TestimonioServiceMysqlImp implements ITestimonioService{
    
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private UploadFile uploadFile;
	
	@Autowired
	private Testimonio testimonio;
	
	@Autowired
	@Qualifier("usuarioServiceMysqlImp")
	private IUsuarioService usuarioService;
	
	@Override
	public List<Testimonio> obtenerTodosLosTestimonios() {
		return testimonioRepository.findByEstado(true);
	}

	@Override
	public Testimonio obtenerTestimonio() {
		return testimonio;
	}

	@Override
	public void agregarTestimonio(Testimonio testimonio, MultipartFile imagen) throws IOException {
		testimonio.setFecha(LocalDate.now());
		String uniqueFileName = uploadFile.copy(imagen);
		testimonio.setEstado(true);
		System.out.println(testimonio.toString());
		testimonioRepository.save(testimonio);
	}

	@Override
	public Testimonio obtenerTestimonioEncontrado(Long id) {
		Testimonio testimonioEncontrado = new Testimonio();
		testimonioEncontrado.setId(id);
		testimonioEncontrado.setFecha(testimonioRepository.findById(id).get().getFecha());
		testimonioEncontrado.setEstado(true);
		testimonioEncontrado.setImagen(testimonioRepository.findById(id).get().getImagen());
		testimonioEncontrado.setUsuario(testimonioRepository.findById(id).get().getUsuario());
		testimonioEncontrado.setComentario(testimonioRepository.findById(id).get().getComentario());
		
		System.out.println("testimonio encontrado:" + testimonioRepository.findById(id).get().toString());
		return testimonioEncontrado;
	}

	@Override
	public void actualizarTestimonio(Testimonio testimonioModificado, MultipartFile imagen) throws IOException {
		Testimonio unTestimonio = new Testimonio();
		System.out.println("testimonio encontrado: " + testimonioModificado.toString());
		System.out.println("testimonio encontrado2: " + testimonioRepository.findById(testimonioModificado.getId()).get().toString());
		unTestimonio.setId(testimonioModificado.getId());
		unTestimonio.setEstado(true);
		unTestimonio.setFecha(LocalDate.now());
		unTestimonio.setUsuario(testimonioModificado.getUsuario());
		unTestimonio.setComentario(testimonioModificado.getComentario());
		
		if (!imagen.isEmpty()) {
			String imagenString = imagen.getOriginalFilename();

			if (imagenString.compareTo(obtenerTestimonioEncontrado(testimonioModificado.getId()).getImagen()) != 0) {
				uploadFile.delete(obtenerTestimonioEncontrado(testimonioModificado.getId()).getImagen());
				imagenString = uploadFile.copy(imagen);
				unTestimonio.setImagen(imagenString);
			}
		}else {
			unTestimonio.setImagen(obtenerTestimonioEncontrado(testimonioModificado.getId()).getImagen());
		}
		
		testimonioRepository.save(unTestimonio);
	}

	@Override
	public void eliminarTestimonio(Long id) {
		Testimonio otroTestimonio = new Testimonio();
		otroTestimonio = testimonioRepository.findById(id).get();
		uploadFile.delete(otroTestimonio.getImagen());
		otroTestimonio.setEstado(false);
		testimonioRepository.save(otroTestimonio);
	}
	
}
