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
@Table(name="tipo_arroz")
public class TipoArroz {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="tipo_arroz_id") private Long id;
	@Column(name="nombre") private String  nombre;
	@Column(name="descripcion") private String descripcion;
	
	@OneToMany(mappedBy="tipoArroz",cascade= {CascadeType.MERGE})
	private List<LoteArroz> lotesArroz;
	
	
	public TipoArroz() {
	}
	public TipoArroz(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public TipoArroz(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		lote.setTipoArroz(this);
		this.lotesArroz.add(lote);
	}
	@Override
	public String toString() {
		return "TipoArroz [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}	
}
