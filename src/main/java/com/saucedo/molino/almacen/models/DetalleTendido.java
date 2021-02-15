package com.saucedo.molino.almacen.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_tendido")
public class DetalleTendido {
	@Id 
	@Column(name="lote_secado_id") private Long id;
	@Column(name="ubicacion") private String ubicacion;
	@Column(name="fecha") private LocalDate fecha;
	@Column(name="hora") private LocalTime hora;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="lote_secado_id")
	private LoteSecado loteSecado;

	@OneToOne(mappedBy="detalleTendido",cascade=CascadeType.ALL)
	private DetalleRecojo detalleRecojo;
	
	public DetalleTendido() {
	}

	public DetalleTendido(String ubicacion, LocalDate fecha, LocalTime hora, LoteSecado loteSecado) {
		this.ubicacion = ubicacion;
		this.fecha = fecha;
		this.hora = hora;
		this.loteSecado = loteSecado;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public LoteSecado getLoteSecado() {
		return loteSecado;
	}

	public void setLoteSecado(LoteSecado loteSecado) {
		this.loteSecado = loteSecado;
	}
	

	public DetalleRecojo getDetalleRecojo() {
		return detalleRecojo;
	}

	public void setDetalleRecojo(DetalleRecojo detalleRecojo) {
		this.detalleRecojo = detalleRecojo;
	}

	@Override
	public String toString() {
		return "DetalleTendido [id=" + id + ", ubicacion=" + ubicacion + ", fecha=" + fecha + ", hora=" + hora
				+ ", loteSecado=" + loteSecado + "]";
	}
		
}
