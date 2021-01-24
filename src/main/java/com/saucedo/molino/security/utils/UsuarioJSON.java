package com.saucedo.molino.security.utils;

import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino_json_models.security.JRole;
import com.saucedo.molino_json_models.security.JUsuario;

public class UsuarioJSON {
	public static JUsuario build(Usuario u) {	
		JUsuario ju = new JUsuario();
		ju.setId(u.getId());
		ju.setUsername(u.getUsername());
		ju.setOwner(u.getOwner());
		ju.setStatus(new Long(u.getState()));
		for(KRole r:u.getRoles()) {
			ju.addRole(new JRole(r.getId(),r.getNombre()));
		}
		return ju;
	}
}
