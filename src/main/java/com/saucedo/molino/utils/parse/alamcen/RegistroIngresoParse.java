package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.RegistroIngreso;
import com.saucedo.molino.utils.parse.EmpleadoParse;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JRegistroIngreso;

@Service
public class RegistroIngresoParse implements IParse<RegistroIngreso,JRegistroIngreso> {
	@Autowired
	CamionProductorParse camionParse;
	@Autowired
	EmpleadoParse empleadoParse;
	
	@Override
	public RegistroIngreso parseJsonToEntity(JRegistroIngreso json) {
		RegistroIngreso entity=null;
		if(json!=null) {
			entity = new RegistroIngreso();
			entity.setId(json.getId());
			entity.setNumeroSacos(json.getNumeroSacos());
			entity.setKilosPorSaco(json.getKilosPorSaco());
			entity.setTotalKilos(json.getKilosPorSaco());
			entity.setFecha(json.getFecha());
			entity.setHora(json.getHora());
			entity.setEmpleado(this.empleadoParse.parseJsonToEntity(json.getEmpleado()));
			entity.setTransporte(this.camionParse.parseJsonToEntity(json.getTransporte()));
		}		
		return entity;	
	}

	@Override
	public JRegistroIngreso parseEntityToJson(RegistroIngreso entity) {
		JRegistroIngreso json =null;
		if(entity!=null) {
			json=new JRegistroIngreso();
			json.setId(entity.getId());
			json.setNumeroSacos(entity.getNumeroSacos());
			json.setKilosPorSaco(entity.getKilosPorSaco());
			json.setTotalKilos(entity.getKilosPorSaco());
			json.setFecha(entity.getFecha());
			json.setHora(entity.getHora());
			json.setEmpleado(this.empleadoParse.parseEntityToJson(entity.getEmpleado()));
			json.setTransporte(this.camionParse.parseEntityToJson(entity.getTransporte()));
		}
		return json;
	}

}
