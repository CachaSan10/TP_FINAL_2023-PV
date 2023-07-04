package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;

@Service("usuarioServiceMysqlImp")
public class UsuarioServiceMysqlImp implements IUsuarioService {

	/**
	 * Inyeccion de la interfaz usuario repository
	 */
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	/**
	 * Inyeccion del objeto Usuario
	 */
	@Autowired
	private Usuario usuario;
	
	/**
	 * Metodo que retorna objeto usuario.
	 * @return objeto de tipo usuario.
	 */
	@Override
	public Usuario obtenerUsuario() {
		
		return usuario;
	}

	/**
	 * Metodo para guardar un usuario en la base de datos.
	 * @param usuario representa un usuario.
	 */
	@Override
	public void guardarUsuario(Usuario usuario) {
		
		usuario.setEstado(true);
		usuarioRepository.save(usuario);
	}

	/**
	 * Metodo para modificar usuario.
	 * @param usuario representa un usuario.
	 */
	@Override
	public void modificarUsuario(Usuario usuario) {
		
		usuario.setEstado(true);
		usuarioRepository.save(usuario);
	}

	/**
	 * Eliminacion logica de usuario
	 * cambiando el atributo  estado a falso.
	 * @param usuario representa a un usuario.
	 */
	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuario.setEstado(false);
		usuarioRepository.save(usuario);
		
	}
	
	/**
	 * Metodo para obtener la lista de usuarios
	 * cuyo estado sea true (activo).
	 * @return la lista de usuarios.
	 */
	@Override
	public List<Usuario> obtenerLista() {
		
		return usuarioRepository.findByEstado(true);
	}

	/**
	 * Metodo que retorna  usuario por id.
	 * @param id representa el id de un usuario.
	 * @return un usuario cuyo id es el solicitado.
	 */
	@Override
	public Usuario buscarUsuario(Long id) {
		return usuarioRepository.findById(id).get();
	}

	/**
	 * Metodo que verifica la existencia
	 * de un usuario.
	 * @param id representa el id de un usuario.
	 * @return un valor booleano true si existe un usuario.
	 */
	@Override
	public boolean existeUsuario(Long id) {
		boolean existe = false;
		for (Usuario usuario : obtenerLista()) {
			if (usuario.getId() == id) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}

}
