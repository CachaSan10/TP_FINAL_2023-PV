package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

/**
 * @author Lautaro Nahuel Moyata
 * Clase que representa el Testimonio de los usuarios "Bienestar en Accion".
 * 
 * Esta clase representa los testimonios realizados por los usuarios en el sistema.
 * Contiene información como el ID del testimonio, la fecha, el usuario que lo realizó,
 * el comentario, la imagen asociada y el estado.
 */

@Component
@Entity
@Table(name = "testimonios")

public class Testimonio {
	
	/**
	 * Representa el id de testimonio
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testimonio_id")
	private Long id;
	
	/**
	 * Representa la fecha del testimonio.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha")
	private LocalDate fecha;
	
	/**
	 * Representa el usuario que realizó el testimonio.
	 */
	@JoinColumn(name="usuario_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	/**
	 * Representa el comentario del testimonio.
	 */
	@NotEmpty(message = "No debe estar vacio este campo")
	@Size(min = 1, max = 200, message = "El comentario debe tener entre 1 y 200 caracteres")
	@Column(name = "testimonio_comentario")
	private String comentario;
	
	/**
	 * Representa la imagen asociada al testimonio.
	 */
	@Column(name = "testimonio_imagen")
	private String imagen;
	
	/**
	 * Representa el estado del testimonio.
	 */
	@Column(name = "estado")
	private boolean estado;
	
	/**
	 * Constructor por defecto de la clase Testimonio.
	 */
	public Testimonio() {

	}
	
	/**
	 * Constructor parametrizado de la clase Testimonio.
	 * 
	 * @param id El ID del testimonio.
	 * @param fecha La fecha del testimonio.
	 * @param usuario El usuario que realizó el testimonio.
	 * @param comentario El comentario del testimonio.
	 * @param imagen La imagen asociada al testimonio.
	 * @param estado El estado del testimonio.
	 */

	public Testimonio(Long id, @PastOrPresent(message = "La fecha de registro de Imc debe ser anterior o actual a la fecha actual")LocalDate fecha,
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
	
	/**
	 * Obtiene el ID del testimonio.
	 * 
	 * @return El ID del testimonio.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el ID del testimonio.
	 * 
	 * @param id El ID del testimonio a establecer.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene la fecha del testimonio.
	 * 
	 * @return La fecha del testimonio.
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Establece la fecha del testimonio.
	 * 
	 * @param fecha La fecha del testimonio a establecer.
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Obtiene el usuario que realizó el testimonio.
	 * 
	 * @return El usuario que realizó el testimonio.
	 */
	public  Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Establece el usuario que realizó el testimonio.
	 * 
	 * @param usuario El usuario que realizó el testimonio a establecer.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtiene el comentario del testimonio.
	 * 
	 * @return El comentario del testimonio.
	 */
	public String getComentario() {
		return comentario;
	}
	
	/**
	 * Establece el comentario del testimonio.
	 * 
	 * @param comentario El comentario del testimonio a establecer.
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	/**
	 * Obtiene la imagen asociada al testimonio.
	 * 
	 * @return La imagen asociada al testimonio.
	 */
	public String getImagen() {
		return imagen;
	}
	
	/**
	 * Establece la imagen asociada al testimonio.
	 * 
	 * @param imagen La imagen asociada al testimonio a establecer.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Verifica el estado del testimonio.
	 * 
	 * @return El estado del testimonio.
	 */
	public boolean isEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado del testimonio.
	 * 
	 * @param estado El estado del testimonio a establecer.
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	/**
	 * Devuelve una representación en cadena del objeto Testimonio.
	 * 
	 * @return Una cadena que representa el objeto Testimonio.
	 */
	@Override
	public String toString() {
		return "Testimonio [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario +", comentario=" + comentario
				+ ", imagen=" + imagen +",estado="+estado+ "]";
	}

	


}
