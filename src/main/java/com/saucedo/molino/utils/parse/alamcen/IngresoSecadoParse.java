package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.IngresoSecado;
import com.saucedo.molino.utils.parse.EmpleadoParse;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JIngresoSecado;

@Service
public class IngresoSecadoParse implements IParse<IngresoSecado,JIngresoSecado> {
	@Autowired
	EmpleadoParse empleadoParse;
	
	@Override
	public IngresoSecado parseJsonToEntity(JIngresoSecado json) {
		IngresoSecado entity=null;
		if(json!=null) {
			entity = new IngresoSecado();
			entity.setId(json.getId());
			entity.setNumeroSacos(json.getNumeroSacos());
			entity.setHumedad(json.getHumedad());
			entity.setFecha(json.getFecha());
			entity.setHora(json.getHora());
			entity.setEmpleado(this.empleadoParse.parseJsonToEntity(json.getEmpleado()));			
		}		
		return entity;	
	}

	@Override
	public JIngresoSecado parseEntityToJson(IngresoSecado entity) {
		JIngresoSecado json =null;
		if(entity!=null) {
			json=new JIngresoSecado();
			json.setId(entity.getId());
			json.setNumeroSacos(entity.getNumeroSacos());
			json.setHumedad(entity.getHumedad());
			json.setFecha(entity.getFecha());
			json.setHora(entity.getHora());
			json.setEmpleado(this.empleadoParse.parseEntityToJson(entity.getEmpleado()));
		}
		return json;
	}

}
