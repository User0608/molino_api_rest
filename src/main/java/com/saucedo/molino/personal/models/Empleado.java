package com.saucedo.molino.personal.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.saucedo.molino.almacen.models.RegistroIngreso;

@Entity
@Table(name="empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="empleado_id") private Long id;

	@Column(name="nombre") private String nombre;
	@Column(name="apellido_paterno") private String apellidoPaterno;
	@Column(name="apellido_materno") private String apellidoMaterno;
	@Column(name="dni") private String dni;
	
	@Column(name="telefono") private String telefon;
	@Column(name="direccion") private String direccion;
	@Column(name="email") private String email;	
	
	@Column(name="sueldo") private double sueldo;
	@Column(name="fecha_contrato") private LocalDate fechaContrato;
	@Column(name="estado") private boolean estado;
	

	@OneToMany(mappedBy="empleado",cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	private List<RegistroIngreso> registrosIngreso;
	
	public Empleado() {
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public LocalDate getFechaContrato() {
		return fechaContrato;
	}
	public void setFechaContrato(LocalDate fechaContrato) {
		this.fechaContrato = fechaContrato;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public List<RegistroIngreso> getRegistrosIngreso() {
		return registrosIngreso;
	}
	public void setRegistrosIngreso(List<RegistroIngreso> registrosIngreso) {
		this.registrosIngreso = registrosIngreso;
	}
	public void addRegistrosIngreso(RegistroIngreso ingreso) {
		if(this.registrosIngreso==null)
			this.registrosIngreso = new ArrayList<>();
		ingreso.setEmpleado(this);
		this.registrosIngreso.add(ingreso);
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", dni=" + dni + ", telefon=" + telefon + ", direccion="
				+ direccion + ", email=" + email + ", sueldo=" + sueldo + ", fechaContrato=" + fechaContrato
				+ ", estado=" + estado + "]";
	}
	
}