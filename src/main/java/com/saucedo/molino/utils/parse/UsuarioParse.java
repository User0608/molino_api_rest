package com.saucedo.molino.utils.parse;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino_json_models.security.JRole;
import com.saucedo.molino_json_models.security.JUsuario;
@Service
public class UsuarioParse implements IParse<Usuario,JUsuario>{

	public Usuario parseJsonToEntity(JUsuario jUsuario,List<KRole> listOfRoles) {
		List<JRole> roles = jUsuario.getRoles();
		Usuario u = this.parseJsonToEntity(jUsuario);
		for (JRole rol : roles) {
			u.addRole(this.filterRole(listOfRoles, rol.getName()));
		}
		
		return u;
	}


	public KRole filterRole(List<KRole> roles, String roleName) {
		for (KRole rol : roles) {
			if (rol.getNombre().equals(roleName))
				return rol;
		}
		return null;
	}
	@Override
	public Usuario parseJsonToEntity(JUsuario json) {
		Usuario u = new Usuario();
		u.setUsername(json.getUsername());
		u.setPassword(json.getPassword());
		u.setOwner(json.getOwner());
		u.setState(json.getStringOfStatus().equals(JUsuario.STRATUS_ACTIVE) ? true : false);
		return u;
	}
	@Override
	public JUsuario parseEntityToJson(Usuario entity) {
		JUsuario ju = new JUsuario();
		ju.setId(entity.getId());
		ju.setUsername(entity.getUsername());
		ju.setOwner(entity.getOwner());
		ju.setStatus(new Long(entity.getState()));
		for(KRole r:entity.getRoles()) {
			ju.addRole(new JRole(r.getId(),r.getNombre()));
		}
		return ju;
	}

}
