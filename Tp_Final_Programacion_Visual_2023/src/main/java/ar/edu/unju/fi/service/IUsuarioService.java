package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;

@Service
public interface IUsuarioService {

	public Usuario obtenerUsuario();
	public void guardarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void eliminarUsuario(Usuario usuario);
	public List<Usuario> obtenerLista();
	public Usuario buscarUsuario(Long id);
	public boolean existeUsuario(Long id);
	
}
