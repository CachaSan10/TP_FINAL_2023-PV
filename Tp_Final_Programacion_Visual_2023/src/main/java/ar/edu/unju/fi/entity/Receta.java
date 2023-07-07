
package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Nahuel Alberto Cachambi
 * Clase que representa la Receta de una comida saludable.
 */
@Component
@Entity
@Table(name = "recetas")
public class Receta {
	
	/**
	 * Representa el id de la receta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id")
	private Long id;
	
	/**
	 * Representa el nombre de la receta
	 */
	@NotEmpty()
	@Size(min=6)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*")
	@Column(name = "rec_nombre")
	private String nombre;
	
	/**
	 * Representa la categoria de la receta
	 */
	@NotBlank(message="Debe seleccionar una categoria")
	@Column(name = "rec_categoria", nullable = false)
	private String categoria;
	
	/**
	 * Representa la lista de ingredientes que contiene la receta
	 */
	@OneToMany(mappedBy = "receta")
	private List<Ingrediente> ingredientes;
	
	/**
	 * Representa la preparacion de la receta
	 */
	@NotEmpty()
	@Size(min=10)
	@Column(name = "rec_preparacion")
	private String preparacion;
	
	/**
	 * Representa el resumen de la receta
	 */
	@NotEmpty()
	@Size(min=10, max=100)
	@Column(name = "rec_resumen")
	private String resumen;
	
	/**
	 * Representa la imagen de la receta
	 */
	@Column(name = "rec_imagen")
	private String imagen;

	/**
	 * Representa la disponibilidad de la receta
	 */
	@Column(name = "rec_estado")
	private boolean estado;
	
	/**
	 * Constructor por defecto
	 */
	public Receta() {
	}

	
	/**
	 * Constructor parametrizado
	 * @param id representa el id de la receta
	 * @param nombre representa el nombre de la receta
	 * @param categoria representa la categoria de la receta
	 * @param ingredientes reprenta la lista de ingrediente que tiene la receta
	 * @param preparacion representa la preparacion de la receta
	 * @param resumen representa el resumen de la receta
	 * @param imagen representa la imagen de la receta
	 * @param estado representa la disponibilidad de la receta en la base de datos
	 */
	public Receta(Long id,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String nombre,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String categoria,
			@NotNull List<Ingrediente> ingredientes, @NotEmpty @Size(min = 60, max = 200) String preparacion,
			@NotEmpty @Size(min = 20, max = 100) String resumen, @NotEmpty String imagen, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
		this.resumen = resumen;
		this.imagen = imagen;
		this.estado = estado;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the ingredientes
	 */
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * @return the preparacion
	 */
	public String getPreparacion() {
		return preparacion;
	}

	/**
	 * @param preparacion the preparacion to set
	 */
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	/**
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", ingredientes=" + ingredientes
				+ ", preparacion=" + preparacion + ", resumen=" + resumen + ", imagen=" + imagen + ", estado=" + estado
				+ "]";
	}

}
