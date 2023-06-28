package ar.edu.unju.fi.entity;

import java.sql.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Lautaro Nahuel Moyata
 * Clase que representa el Testimonio de los usuarios "Bienestar en Accion".
 */
public class Testimonio {
	
	private int id;
	
	@FutureOrPresent(message = "La fecha del testimonio debe ser posterior o igual a la fecha actual")
	@NotNull(message = "La fecha del testimonio es requerida")
	private Date fecha;
	
	@NotNull(message = "El usuario del testimonio es requerido")
	private Usuario usuario;
	
	@Size(min = 1, max = 200, message = "El comentario debe tener entre 1 y 200 caracteres")
	private String comentario;
	
	public Testimonio(int id, Date fecha, Usuario usuario, String comentario) {
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
