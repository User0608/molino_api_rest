package com.saucedo.molino.almacen.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.saucedo.molino.personal.models.Empleado;

@Entity
@Table(name="registro_ingreso")
public class RegistroIngreso {
	@Id 
	@Column(name="lote_id") private Long id;
	@Column(name="numero_sacos") private int numeroSacos;
	@Column(name="kilos_saco") private double kilosPorSaco;
	@Column(name="total_kilos") private double totalKilos;
	@Column(name="fecha") private LocalDate fecha;
	@Column(name="hora") private LocalTime hora;
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="camion_transporte_id ")	
	private CamionProductor transporte;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="empleado_id")	
	private Empleado empleado;
		
	public RegistroIngreso() {
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


	public double getKilosPorSaco() {
		return kilosPorSaco;
	}


	public void setKilosPorSaco(double kilosPorSaco) {
		this.kilosPorSaco = kilosPorSaco;
	}


	public double getTotalKilos() {
		return totalKilos;
	}


	public void setTotalKilos(double totalKilos) {
		this.totalKilos = totalKilos;
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


	public CamionProductor getTransporte() {
		return transporte;
	}


	public void setTransporte(CamionProductor transporte) {
		this.transporte = transporte;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	@Override
	public String toString() {
		return "RegistroIngreso [id=" + id + ", numeroSacos=" + numeroSacos + ", kilosPorSaco=" + kilosPorSaco
				+ ", totalKilos=" + totalKilos + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
}
