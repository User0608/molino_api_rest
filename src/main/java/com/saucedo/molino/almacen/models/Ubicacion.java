package com.saucedo.molino.almacen.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ubicacion")
public class Ubicacion {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ubicacion_id") private Long id;
	@Column(name="codigo") private String codigo;
	@Column(name="descripcion") private String descripcion;	
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="area_secado_id")
	private AreaSecado areaSecado;
	
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
	
	public AreaSecado getAreaSecado() {
		return areaSecado;
	}
	public void setAreaSecado(AreaSecado areaSecado) {
		this.areaSecado = areaSecado;
	}
	@Override
	public String toString() {
		return "Ubicacion [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
}
