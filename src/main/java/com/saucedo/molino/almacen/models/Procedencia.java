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
@Table(name="procedencia")
public class Procedencia {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="procedencia_id") private Long id;
	@Column(name="lugar") private String lugar;
	
	@OneToMany(mappedBy="procedencia",cascade= {CascadeType.MERGE})
	private List<LoteArroz> lotesArroz;
	
	public Procedencia() {
	}
	
	public Procedencia(String lugar) {
		this.lugar = lugar;
	}
	
	public Procedencia(Long id, String lugar) {
		this.id = id;
		this.lugar = lugar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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
		this.lotesArroz.add(lote);
	}
}
