package com.saucedo.molino.almacen.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_gasto")
public class DetalleGasto {
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="lote_secado_id")
	private LoteSecado loteSecado;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="costo_id")
	private Costo costo;
	
	

	public DetalleGasto() {
	}

	public DetalleGasto(LoteSecado loteSecado, Costo costo) {
		this.loteSecado = loteSecado;
		this.costo = costo;
	}

	public LoteSecado getLoteSecado() {
		return loteSecado;
	}

	public void setLoteSecado(LoteSecado loteSecado) {
		this.loteSecado = loteSecado;
	}

	public Costo getCosto() {
		return costo;
	}

	public void setCosto(Costo costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "DetalleGasto [loteSecado=" + loteSecado + ", costo=" + costo + "]";
	}

	
	
}
