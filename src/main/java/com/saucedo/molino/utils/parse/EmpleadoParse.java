package com.saucedo.molino.utils.parse;

import org.springframework.stereotype.Service;

import com.saucedo.molino.personal.models.Empleado;
import com.saucedo.molino_json_models.personal.JEmpleado;

@Service
public class EmpleadoParse implements IParse<Empleado,JEmpleado>{

	@Override
	public Empleado parseJsonToEntity(JEmpleado json) {
		Empleado entity = null;
		if (json != null) {
			entity = new Empleado();
			entity.setId(json.getId());
			
			entity.setNombre(json.getNombre());
			entity.setApellidoPaterno(json.getApellidoPaterno());
			entity.setApellidoMaterno(json.getApellidoMaterno());
			entity.setDni(json.getDni());
						
			entity.setTelefon(json.getTelefon());
			entity.setDireccion(json.getDireccion());
			entity.setEmail(json.getEmail());
			
			entity.setSueldo(json.getSueldo());
			entity.setFechaContrato(json.getFechaContrato());
			entity.setEstado(json.isEstado());
		}
		return entity;
	}

	@Override
	public JEmpleado parseEntityToJson(Empleado entity) {
		JEmpleado je = new JEmpleado();
		if (entity != null) {
			je.setId(entity.getId());			
			je.setNombre(entity.getNombre());
			je.setApellidoPaterno(entity.getApellidoPaterno());
			je.setApellidoMaterno(entity.getApellidoMaterno());
			je.setDni(entity.getDni());
			
			je.setTelefon(entity.getTelefon());
			je.setDireccion(entity.getDireccion());
			je.setEmail(entity.getEmail());
			
			je.setSueldo(entity.getSueldo());
			je.setFechaContrato(entity.getFechaContrato());
			je.setEstado(entity.isEstado());
		}
		return je;
	}


}
