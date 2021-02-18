package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.Ubicacion;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JUbicacion;

@Service
public class UbicacionParse implements IParse<Ubicacion,JUbicacion> {

	@Override
	public Ubicacion parseJsonToEntity(JUbicacion json) {
		Ubicacion entity=null;
		if(json!=null) {
			entity = new Ubicacion();
			entity.setId(json.getId());
			entity.setCodigo(json.getCodigo());
			entity.setDescripcion(json.getDescripcion());
		}		
		return entity;	
	}

	@Override
	public JUbicacion parseEntityToJson(Ubicacion entity) {
		JUbicacion json =null;
		if(entity!=null) {
			json=new JUbicacion();
			json.setId(entity.getId());
			json.setCodigo(entity.getCodigo());
			json.setDescripcion(entity.getDescripcion());
		}
		return json;
	}

}
