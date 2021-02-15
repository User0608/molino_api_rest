package com.saucedo.molino.almacen.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="lote_secado")
public class LoteSecado {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="lote_secado_id") private Long id;
	@Column(name="fecha ") private LocalDate fecha ;
	@Column(name="total_sacos") private int totalSacos;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="lote_id ")
	private LoteArroz lotearroz;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="ubicacion_id")
	private Ubicacion ubicacion;
	
	@OneToOne( mappedBy="loteSecado",cascade=CascadeType.MERGE)
	private IngresoSecado ingreso;
	
	@OneToMany(mappedBy="loteSecado" ,cascade=CascadeType.ALL)
	private List <DetalleGasto> detalleGastos;
	
	public LoteSecado() {
	}

	public LoteSecado(LocalDate fecha, int totalSacos, LoteArroz lotearroz, Ubicacion ubicacion) {
		this.fecha = fecha;
		this.totalSacos = totalSacos;
		this.lotearroz = lotearroz;
		this.ubicacion = ubicacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getTotalSacos() {
		return totalSacos;
	}

	public void setTotalSacos(int totalSacos) {
		this.totalSacos = totalSacos;
	}

	public LoteArroz getLotearroz() {
		return lotearroz;
	}

	public void setLotearroz(LoteArroz lotearroz) {
		this.lotearroz = lotearroz;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public IngresoSecado getIngreso() {
		return ingreso;
	}

	public void setIngreso(IngresoSecado ingreso) {
		this.ingreso = ingreso;
	}
	

	public List<DetalleGasto> getDetalleGasto() {
		return detalleGastos;
	}

	public void setDetalleGasto(List<DetalleGasto> detalleGasto) {
		this.detalleGastos = detalleGasto;
	}
	public void addDetalleGasto(DetalleGasto detalleGasto) {
		if  (detalleGastos==null) detalleGastos=new ArrayList <DetalleGasto>();
		detalleGasto.setLoteSecado(this);
		this.detalleGastos.add(detalleGasto);
	}

	@Override
	public String toString() {
		return "LoteSecado [id=" + id + ", fecha=" + fecha + ", totalSacos=" + totalSacos + ", lotearroz=" + lotearroz
				+ ", ubicacion=" + ubicacion + "]";
	}
	
}
