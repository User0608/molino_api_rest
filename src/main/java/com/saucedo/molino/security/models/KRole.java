package com.saucedo.molino.security.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class KRole {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="role_id") private Long id;	
	@Column(name="nombre") private String nombre;
	@Column(name="descripcion") private String descripcion;
	
	@ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
	public KRole() {
		this.usuarios= new HashSet<Usuario>();
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
	
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void addUser(Usuario usuario) {
		if(usuario!=null) {
			this.usuarios.add(usuario);
		}
	}

	@Override
	public String toString() {
		return "KRole [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
