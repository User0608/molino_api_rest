package com.saucedo.molino.almacen.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="costo")
public class Costo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="costo_id ") private Long id;
	@Column(name="monto") private Double monto;	
	@Column(name="descripcion") private String descripcion;
	
	public Costo(Double monto, String descripcion) {
		this.monto = monto;
		this.descripcion = descripcion;
	}
	public Costo() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Costo [id=" + id + ", monto=" + monto + ", descripcion=" + descripcion + "]";
	}
	
}
