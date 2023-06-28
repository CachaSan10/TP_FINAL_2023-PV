/**
 * 
 */
package ar.edu.unju.fi.entity;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Nahuel Alberto Cachambi
 * Clase que representa la Receta de una comida saludable.
 */
public class Receta {
	
	/* Representa el id de la receta */
	private Long id;
	
	/* Representa el nombre de la receta */
	@NotEmpty()
	@Size(min=6, max=20)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*", message="Los nombres solo pueden contener letras")
	private String nombre;
	
	/* Representa la categoria de la receta */
	@NotEmpty()
	@Size(min=6, max=20)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*", message="Los nombres solo pueden contener letras")
	private String categoria;
	
	/* Representa la lista de ingredientes que contiene la receta*/
	@NotNull()
	private List<Ingrediente> ingredientes;
	
	/* Representa la preparacion de la receta */
	@NotEmpty()
	@Size(min=60, max=200)
	private String preparacion;
	
	/* Representa el resumen de la receta */
	@NotEmpty()
	@Size(min=20, max=100)
	private String resumen;
	
	/* Representa la imagen de la receta */
	@NotEmpty()
	private String imagen;

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
	 * @param ingredientes reprenta los ingredientes de la receta
	 * @param preparacion representa la preparacion de la receta
	 * @param resumen representa el resumen de la receta
	 * @param imagen representa la imagen de la receta
	 */
	public Receta(Long id, String nombre, String categoria, List<Ingrediente> ingredientes, String preparacion,
			String resumen, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
		this.resumen = resumen;
		this.imagen = imagen;
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

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", ingredientes=" + ingredientes
				+ ", preparacion=" + preparacion + ", resumen=" + resumen + ", imagen=" + imagen + "]";
	}
	
	

}
