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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Nahuel Alberto Cachambi
 * Clase que representa el indice de masa corporal de un usuario del sistema.
 */
@Component
@Entity
@Table(name = "indices_masa_corporal")
public class IndiceMasaCorporal {
	
	/* Representa el id de indices de masa corporal */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imc_id")
	private Long id;
	
	/* Representa la fecha de indice de masa corporal que registro el usuario */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "imc_fecha")
	private LocalDate fechaImc;
	
	/* Representa el usuario al cual pertenece el indice de masa corporal */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_id")
	private Usuario usuario;
	
	/* Representa la disponibilidad del indice de masa corporal en la base de datos */
	@Column(name = "imc_estado")
	private boolean estado;
	
	/* Representa el peso del usuario*/
	@Column(name = "imc_peso")
	private double peso;


	/**
	 * Constructor por defecto
	 */
	public IndiceMasaCorporal() {
	}


	/**
	 * Constructor Parametrizado
	 * @param id representa el id del indice de masa corporal del usuario del sistema
	 * @param fechaImc representa la fecha del indice de masa corporal el cual se registro.
	 * @param usuario representa el usuario que pertenece al indice de masa corporal.
	 * @param estado  representa la disponibilidad del indice de masa corporal en la base de datos.
	 */
	public IndiceMasaCorporal(Long id, LocalDate fechaImc, Usuario usuario, boolean estado) {
		this.id = id;
		this.fechaImc = fechaImc;
		this.usuario = usuario;
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
	 * @return the fechaImc
	 */
	public LocalDate getFechaImc() {
		return fechaImc;
	}


	/**
	 * @param fechaImc the fechaImc to set
	 */
	public void setFechaImc(LocalDate fechaImc) {
		this.fechaImc = fechaImc;
	}


	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	/**
	 * Metodo que calcula el indice de masa corporal
	 * @param peso representa el peso que ingresa el usuario
	 * @return el resultado del indice de masa corporal
	 */
	public double calcularImc() {
		int altura = (int) (usuario.getEstatura() * 100);
		return peso / Math.pow(altura, 2);
	}

	
	/**
	 * Metodo que calcula el peso ideal del usuario.
	 * @return el resultado del peso ideal del usuario.
	 */
	public double calcularPesoIdeal() {
		int altura = (int) (usuario.getEstatura() * 100);
		return (double)(altura - 100 + ((usuario.calcularAnio()/10)*0.9)); 
	}
	
	@Override
	public String toString() {
		return "IndiceMasaCorporal [id=" + id + ", fechaImc=" + fechaImc + ", usuario=" + usuario + ", estado=" + estado
				+ "]";
	}
	
	
	

}
