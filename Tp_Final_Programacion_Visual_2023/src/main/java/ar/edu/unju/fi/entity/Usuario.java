package ar.edu.unju.fi.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * @author Villena Franco
 * Clase que representa un usuario.
 */
@Component
@Entity
@Table(name="usuarios")
public class Usuario {
	
	/* Representa el id del usuario */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Long id;
	
	/* Representa nombre del usuario */
	@NotEmpty(message="El nombre del usuario no puede estar vacio")
	@Size(min=5, max=50,message="El nombre de usuario debe tener entre 5 y 50 caracteres")
	@Column(name="usu_nombre")
	private String nombre;
	
	/* Representa apellido del usuario */
	@NotEmpty(message="El apellido del usuario no puede estar vacio")
	@Size(min=5, max=50,message="El nombre de usuario debe tener entre 5 y 50 caracteres")
	@Column(name="usu_apellido")
	private String apellido;
	
	/* Representa email del usuario */
	@Email(message = "Ingrese un mail valido")
	@NotEmpty(message="El mail del usuario no puede estar vacio")	
	@Column(name="usu_email")
	private String email;
	
	/* Representa fecha de nacimiento del usuario */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@Column(name="usu_fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	/* Representa telefono del usuario */
	@Pattern(regexp = "\\d+", message = "Ingrese un número de teléfono válido solo numeros			")
	@NotEmpty(message="Este campo no puede estar vacio")
	@Size(min=10, max=10,message="Ingrese un numero de telefono valido de 10 digitos. ")
	@Column(name="usu_telefono")
	private String telefono;
	
	/* Representa genero del usuario */
	@NotEmpty(message="Este campo no puede estar vacio")
	@Column(name="usu_sexo")
	private String sexo;
	
	/* Representa altura del usuario */
	@NotNull(message = "La estatura no puede estar vacía")
	@DecimalMin(value = "1.20", inclusive = true, message = "La estatura mínima permitida es 1.20 metros")
	@DecimalMax(value = "2.20", inclusive = true, message = "La estatura máxima permitida es 2.20 metros")
	@Column(name="usu_estatura")
	private Double estatura;
	
	/*Representa la disponibilidad del usuario*/
	@Column(name="usu_estado")
	private boolean estado;
	
	/* Representa la lista de indice de masa corporal que le pertenecen al usuario*/
	@OneToMany(mappedBy = "usuario") // Un usuario puede tener almacenada muchos indice de masa corporal
	private List<IndiceMasaCorporal> indicesMasaCorporal;
	

	@OneToOne(mappedBy = "usuario")
	private Testimonio testimonio;
	
	public Usuario() {
		
		
	}
	
	

	/**
	 * Constructor parametrizado usando los atributos
	 */
	public Usuario(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento, String telefono,
			String sexo, Double estatura, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.sexo = sexo;
		this.estatura = estatura;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param nombre the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param nombre the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param nombre the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param nombre the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param nombre the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}




	/**
	 * @return the estatura
	 */
	public Double getEstatura() {
		return estatura;
	}

	/**
	 * @param nombre the estatura to set
	 */
	public void setEstatura(Double estatura) {
		this.estatura = estatura;
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
	 * Metodo que calcula el anio del usuario
	 * @return el anio del usuario
	 */
	public byte calcularAnio() {
		LocalDate fechaActual = LocalDate.now();
		byte anioPersona;
		Period periodo = Period.between(fechaNacimiento, fechaActual);
		anioPersona = (byte) (periodo.getYears());
		return anioPersona;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", fechaNacimiento="
				+ fechaNacimiento + ", telefono=" + telefono + ", sexo=" + sexo + ", estatura=" + estatura + ", estado=" + estado
				+ "]";
	}
	
	
	
	
	
}
