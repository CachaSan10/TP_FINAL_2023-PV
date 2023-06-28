/**
 * 
 */
package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Nahuel Alberto Cachambi
 * Clase que representa el ingrediente de una receta.
 */
public class Ingrediente {
	
	/* Representa el id del ingrediente*/
	private Long id;
	
	/* Representa el nombre del ingrediente*/
	@NotEmpty()
	@Size(min=6, max=20)
	@Pattern(regexp="[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*", message="Los nombres solo pueden contener letras")
	private String nombre;
	
	/* Representa la receta que entan vinculdos los ingredientes*/
	private Receta receta;

	/**
	 * Constructor por defecto
	 */
	public Ingrediente() {
	}

	/**
	 * Constructor parametrizado
	 * @param id representa el id del ingrediente
	 * @param nombre representa el nombre del ingrediente
	 * @param receta representa la receta el cual esta vinculado
	 */
	public Ingrediente(Long id, String nombre, Receta receta) {
		this.id = id;
		this.nombre = nombre;
		this.receta = receta;
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
	 * @return the receta
	 */
	public Receta getReceta() {
		return receta;
	}

	/**
	 * @param receta the receta to set
	 */
	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nombre=" + nombre + ", receta=" + receta + "]";
	}
	
	
	
	

}
