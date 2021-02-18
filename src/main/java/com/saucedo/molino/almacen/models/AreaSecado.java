package com.saucedo.molino.almacen.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="area_secado")
public class AreaSecado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="area_secado_id") private Long id;
	@Column(name="ubicacion ") private String ubicacion;
	@Column(name="capacidad ") private int capacidad;
	@OneToMany(mappedBy="areaSecado",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Ubicacion> ubicaciones;
	
	public AreaSecado() {
	}
	public AreaSecado(String ubicacion, int capacidad) {
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	 
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	@Override
	public String toString() {
		return "AreaSecado [id=" + id + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + "]";
	}
	
		
}
