package com.saucedo.molino.almacen.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ubicacion")
public class Ubicacion {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ubicacion_id") private Long id;
	@Column(name="ubicacion_id") private String codigo;
	@Column(name="ubicacion_id") private String descripcion;	
	
	public Ubicacion() {
	}
	public Ubicacion(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Ubicacion [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

	
	
}
