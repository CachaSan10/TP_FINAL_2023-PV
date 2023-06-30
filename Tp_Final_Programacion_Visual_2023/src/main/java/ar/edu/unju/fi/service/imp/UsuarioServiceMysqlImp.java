package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;

@Service("usuarioServiceMysqlImp")
public class UsuarioServiceMysqlImp implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private Usuario usuario;
	
	@Override
	public Usuario obtenerUsuario() {
		
		return usuario;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		
		usuario.setEstado(true);
		usuarioRepository.save(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		
		usuario.setEstado(true);
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuario.setEstado(false);
		usuarioRepository.save(usuario);
		
	}

	@Override
	public List<Usuario> obtenerLista() {
		
		return usuarioRepository.findByEstado(true);
	}

	@Override
	public Usuario buscarUsuario(Long id) {
		
		return usuarioRepository.findById(id).get();
	}

	@Override
	public boolean existeUsuario(Long id) {
		return usuarioRepository.existsById(id);
	}

}
