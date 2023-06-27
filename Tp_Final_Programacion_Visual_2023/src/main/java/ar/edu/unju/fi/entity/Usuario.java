package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Long id;
	
	@Column(name="usu_nombre")
	private String nombre;
	
	@Column(name="usu_apellido")
	private String apellido;
	
	@Column(name="usu_email")
	private String email;
	
	@Column(name="usu_fecha_nacimiento")
	private LocalDate fechaN;
	
	@Column(name="usu_telefono")
	private String telefono;
	
	@Column(name="usu_sexo")
	private String sexo;
	
	@Column(name="usu_estatura")
	private int estatura;
	@Column(name="usu_estado")
	private boolean estado;
	
	
	public Usuario() {
		
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


	public LocalDate getFechaN() {
		return fechaN;
	}


	public void setFechaN(LocalDate fechaN) {
		this.fechaN = fechaN;
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


	public int getEstatura() {
		return estatura;
	}


	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", fechaN="
				+ fechaN + ", telefono=" + telefono + ", sexo=" + sexo + ", estatura=" + estatura + ", estado=" + estado
				+ "]";
	}
	
	
	
	
	
}
