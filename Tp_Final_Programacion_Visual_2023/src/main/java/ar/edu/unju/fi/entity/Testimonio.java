package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Lautaro Nahuel Moyata Clase que representa el Testimonio de los
 *         usuarios "Bienestar en Accion".
 */
public class Testimonio {

	private int id;

	@FutureOrPresent(message = "La fecha del testimonio debe ser posterior o igual a la fecha actual")
	@NotNull(message = "La fecha del testimonio es requerida")
	private LocalDate fecha;

	@NotNull(message = "El usuario del testimonio es requerido")
	private Usuario usuario;

	@Size(min = 1, max = 200, message = "El comentario debe tener entre 1 y 200 caracteres")
	private String comentario;

	public Testimonio() {

	}

	public Testimonio(int id, LocalDate fecha, Usuario usuario, String comentario) {

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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

	@Override
	public String toString() {
		return "Testimonio [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", comentario=" + comentario
				+ "]";
	}

}
