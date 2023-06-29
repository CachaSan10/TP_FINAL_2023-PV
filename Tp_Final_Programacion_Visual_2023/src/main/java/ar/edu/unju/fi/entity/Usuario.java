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
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Long id;
	
	@NotEmpty(message="El nombre del usuario no puede estar vacio")
	@Size(min=5, max=50,message="El nombre de usuario debe tener entre 5 y 50 caracteres")
	@Column(name="usu_nombre")
	private String nombre;
	
	@NotEmpty(message="El apellido del usuario no puede estar vacio")
	@Size(min=5, max=50,message="El nombre de usuario debe tener entre 5 y 50 caracteres")
	@Column(name="usu_apellido")
	private String apellido;
	
	@Email(message = "Ingrese un mail valido")
	@NotEmpty(message="El mail del usuario no puede estar vacio")	
	@Column(name="usu_email")
	private String email;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@Column(name="usu_fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@NotEmpty(message="Este campo no puede estar vacio")
	@Size(min=11, max=11,message="Ingrese un numero de telefono valido")
	@Column(name="usu_telefono")
	private String telefono;
	
	@NotEmpty(message="Este campo no puede estar vacio")
	@Column(name="usu_sexo")
	private String sexo;
	
	@NotNull(message = "La estatura no puede estar vacía")
	@DecimalMin(value = "1.20", inclusive = true, message = "La estatura mínima permitida es 1.20 metros")
	@DecimalMax(value = "2.20", inclusive = true, message = "La estatura máxima permitida es 2.20 metros")
	@Column(name="usu_estatura")
	private Double estatura;
	@Column(name="usu_estado")
	private boolean estado;
	
	/* Representa la lista de indice de masa corporal que le pertenecen al usuario*/
	@OneToMany(mappedBy = "usuario") // Un usuario puede tener almacenada muchos indice de masa corporal
	private List<IndiceMasaCorporal> indicesMasaCorporal;
	
	@OneToOne(mappedBy = "usuario")
	private Testimonio testimonio;
	
	public Usuario() {
		
		
	}
	
	


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




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}





	public Double getEstatura() {
		return estatura;
	}


	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}


	public boolean isEstado() {
		return estado;
	}


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
