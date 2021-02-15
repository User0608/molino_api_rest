package com.saucedo.molino.almacen.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="area_secado")
public class AreaSecado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="area ") private Long id;
	@Column(name="area ") private String ubicacion;
	@Column(name="area ") private int capacidad;
	
	
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
	@Override
	public String toString() {
		return "AreaSecado [id=" + id + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + "]";
	}
	
		
}
