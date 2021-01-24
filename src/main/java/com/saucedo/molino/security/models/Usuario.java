package com.saucedo.molino.security.models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="usuario_id") private Long id;
	@Column(name="username") private String username;
	@Column(name="pwd") private String password;
	@Column(name="owner") private String owner;
	@Column(name="state") private Integer state;
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "usuario_roles", 
				joinColumns = { @JoinColumn(name = "usuario_id") }, 
				inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<KRole> roles;

	public Usuario() {
		this.roles = new HashSet<>();
	}

	public Usuario(String username, String password, String owner, Integer state) {
		this.username = username;
		this.password = password;
		this.owner = owner;
		this.state = state;
		this.roles = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isState() {
		return (this.state==0)? false:true;
	}

	public void setState(boolean state) {
		if(state) this.state=1;
		else this.state=0;
	}
	

	public Set<KRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<KRole> roles) {
		this.roles = roles;
	}
	public void addRole(KRole role) {
		if(role!=null) {
			System.out.println(role.toString());
			role.addUser(this);
			this.roles.add(role);
		}		
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", owner=" + owner
				+ ", state=" + state + "]";
	}
	
	
	
}
