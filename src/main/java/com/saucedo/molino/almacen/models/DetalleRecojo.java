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
@Table(name="detalle_recojo")
public class DetalleRecojo {
	@Id 
	@Column(name="lote_secado_id") private Long id;
	@Column(name="fecha") private LocalDate fecha;
	@Column(name="hora") private LocalTime hora;
	@Column(name="numero_sacos") private int numeroSacos;
	@Column(name="humedad") private Double humedad;


	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="lote_secado_id")
	private DetalleTendido detalleTendido;

	

	public DetalleRecojo() {
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


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	public int getNumeroSacos() {
		return numeroSacos;
	}


	public void setNumeroSacos(int numeroSacos) {
		this.numeroSacos = numeroSacos;
	}


	public Double getHumedad() {
		return humedad;
	}


	public void setHumedad(Double humedad) {
		this.humedad = humedad;
	}


	public DetalleTendido getDetalleTendido() {
		return detalleTendido;
	}


	public void setDetalleTendido(DetalleTendido detalleTendido) {
		this.detalleTendido = detalleTendido;
	}


	@Override
	public String toString() {
		return "DetalleRecojo [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", numeroSacos=" + numeroSacos
				+ ", humedad=" + humedad + "]";
	}
	
	
}
