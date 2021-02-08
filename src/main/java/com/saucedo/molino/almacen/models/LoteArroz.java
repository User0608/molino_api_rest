package com.saucedo.molino.almacen.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="lote_arroz")
public class LoteArroz {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="lote_id") private Long id;
	@Column(name="numero_sacos") private int numeroSacos;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "lote_id")
	private RegistroIngreso ingreso;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= CascadeType.MERGE)
	@JoinColumn(name="productor_id")	
	private Productor productor;
	

	@ManyToOne(cascade= {CascadeType.MERGE})
	@JoinColumn(name="tipo_arroz_id")	
	private TipoArroz tipoArroz;
	
	@ManyToOne(cascade= {CascadeType.MERGE})
	@JoinColumn(name="procedencia_id")	
	private Procedencia procedencia;

	public LoteArroz() {
	}

	public LoteArroz(Long id, int numeroSacos) {
		this.id = id;
		this.numeroSacos = numeroSacos;
	}


	public LoteArroz(int numeroSacos) {
		this.numeroSacos = numeroSacos;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getNumeroSacos() {
		return numeroSacos;
	}


	public void setNumeroSacos(int numeroSacos) {
		this.numeroSacos = numeroSacos;
	}


	public RegistroIngreso getIngreso() {
		return ingreso;
	}


	public void setIngreso(RegistroIngreso ingreso) {
		this.ingreso = ingreso;
	}


	public Productor getProductor() {
		return productor;
	}


	public void setProductor(Productor productor) {
		this.productor = productor;
	}


	public Procedencia getProcedencia() {
		return procedencia;
	}


	public void setProcedencia(Procedencia procedencia) {
		this.procedencia = procedencia;
		this.procedencia.addLoteArroz(this);
	}


	public TipoArroz getTipoArroz() {
		return tipoArroz;
	}


	public void setTipoArroz(TipoArroz tipoArroz) {
		this.tipoArroz = tipoArroz;
	}


	@Override
	public String toString() {
		return "LoteArroz [id=" + id + ", numeroSacos=" + numeroSacos + "]";
	}
	

}
