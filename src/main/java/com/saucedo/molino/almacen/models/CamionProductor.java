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
@Table(name="camion_transporte")
public class CamionProductor {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="camion_transporte_id") private Long id;
	
	@Column(name="placa") private String  placa;
	@Column(name="chofer") private String chofer;
	@Column(name="descripcion") private String descripcion;
	
	@OneToMany(mappedBy="transporte",cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	private List<RegistroIngreso> registrosIngreso;
	
	
	public CamionProductor() {
	}
	
	public CamionProductor(String placa, String chofer, String descripcion) {
		this.placa = placa;
		this.chofer = chofer;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getChofer() {
		return chofer;
	}
	public void setChofer(String chofer) {
		this.chofer = chofer;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		ingreso.setTransporte(this);
		this.registrosIngreso.add(ingreso);
	}
	@Override
	public String toString() {
		return "CamionProductor [placa=" + placa + ", chofer=" + chofer + ", descripcion=" + descripcion + "]";
	}	
}