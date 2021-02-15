package com.saucedo.molino.almacen.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.saucedo.molino.personal.models.Empleado;

@Entity
@Table(name="ingreso_secado")
public class IngresoSecado {
	@Id 
	@Column(name="lote_secado_id") private Long id;
	@Column(name="fecha") private LocalDate fecha;
	@Column(name="hora") private LocalTime hora;
	@Column(name="numero_sacos") private int numeroSacos;
	@Column(name="nivel_humedad") private Double humedad;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="empleado_id")
	private Empleado empleado;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="lote_secado_id")
	private LoteSecado loteSecado;

	
	
	public IngresoSecado() {
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public LoteSecado getLoteSecado() {
		return loteSecado;
	}

	public void setLoteSecado(LoteSecado loteSecado) {
		this.loteSecado = loteSecado;
	}

	@Override
	public String toString() {
		return "IngresoSecado [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", numeroSacos=" + numeroSacos
				+ ", humedad=" + humedad + "]";
	}
	
	
}
