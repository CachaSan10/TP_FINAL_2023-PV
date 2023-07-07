/**
 * 
 */
package ar.edu.unju.fi.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Nahuel Alberto Cachambi
 * Clase que representa el ingrediente de una receta.
 */
@Component
@Entity
@Table(name = "ingredientes")
public class Ingrediente {
	
	/**
	 * Representa el id del ingrediente
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingre_id")
	private Long id;
	
	/**
	 * Representa el nombre del ingrediente
	 */
	@NotEmpty()
	@Size(min=6, max=60)
	@Column(name = "ingre_nombre")
	private String nombre;
	
	/**
	 * Representa la  receta que enta vinculado con el ingrediente
	 */
	@ManyToOne
	@JoinColumn(name = "rec_id")
	private Receta receta;

	/**
	 * Representa la disponibilidad del ingrediente en la base de datos
	 */
	@Column(name = "ingre_estado")
	private boolean estado;
	
	/**
	 * Constructor por defecto
	 */
	public Ingrediente() {
	}

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Constructor parametrizado
	 * @param id id representa el id del ingrediente
	 * @param nombre representa el nombre del ingrediente
	 * @param recetas representa la lista de receta los cuales estan vinculado al ingrediente
	 * @param estado representa la disponibilidad del ingrediente en la base de datos
	 */
	public Ingrediente(Long id,
			@NotEmpty @Size(min = 6, max = 20) @Pattern(regexp = "[a-z A-ZÀ-ÿ\\u00f1\\u00d1]*") String nombre,
			Receta receta, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.receta = receta;
		this.estado = estado;
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





	public Receta getReceta() {
		return receta;
	}


	public void setReceta(Receta receta) {
		this.receta = receta;
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





	

	
	
	

}
