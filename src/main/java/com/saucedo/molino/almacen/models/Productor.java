package com.saucedo.molino.almacen.models;

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

@Entity
@Table(name="productor")
public class Productor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="productor_id") private Long id;
	@Column(name="dni") private String dni;
	@Column(name="nombre") private String nombre;
	@Column(name="apellido_paterno") private String apellidoPaterno;
	@Column(name="apellido_materno") private String apellidoMaterno;
	@Column(name="direccion") private String direccion;
	@Column(name="telefono") private String telefon;
	@Column(name="email") private String email;	
	
	@OneToMany(mappedBy="productor",cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	private List<LoteArroz> lotesArroz;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
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



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefon() {
		return telefon;
	}



	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	
	public List<LoteArroz> getLotesArroz() {
		return lotesArroz;
	}

	public void setLotesArroz(List<LoteArroz> lotesArroz) {
		this.lotesArroz = lotesArroz;
	}
	public void addLoteArroz(LoteArroz lote) {
		if(this.lotesArroz==null)
			this.lotesArroz=new ArrayList<>();
		lote.setProductor(this);
		this.lotesArroz.add(lote);
	}
	public Productor() {
	}

	public Productor(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion,
			String telefon, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.telefon = telefon;
		this.email = email;
	}



	@Override
	public String toString() {
		return "Productor [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", direccion=" + direccion + ", telefon=" + telefon
				+ ", email=" + email + "]";
	}
	
	
}