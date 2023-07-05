package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Lautaro Nahuel Moyata Clase que representa el Testimonio de los
 *         usuarios "Bienestar en Accion".
 */

@Component
@Entity
@Table(name = "TESTIMONIOS")

public class Testimonio {
	
	// Representa el id de testimonio
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tes_id")
	
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_publicacion")
	private LocalDate fecha;
	
	@Autowired
	@NotNull(message = "El usuario del testimonio es requerido")
	@JoinColumn(name="usu_id")
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private Usuario usuario;

	@Size(min = 1, max = 200, message = "El comentario debe tener entre 1 y 200 caracteres")
	private String comentario;
	
	@Column(name = "tes_imagen")
	private String imagen;
	
	private boolean estado;

	public Testimonio() {

	}

	public Testimonio(Long id, LocalDate fecha,
			@NotNull(message = "El usuario del testimonio es requerido") Usuario usuario,
			@Size(min = 1, max = 200, message = "El comentario debe tener entre 1 y 200 caracteres") String comentario,
			String imagen, boolean estado) {
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.comentario = comentario;
		this.imagen = imagen;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public  Usuario getUsuario() {
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
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Testimonio [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario +", comentario=" + comentario
				+ ", imagen=" + imagen +",estado="+estado+ "]";
	}

	


}
