package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.Procedencia;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JProcedencia;
@Service
public class ProcedenciaParse implements IParse<Procedencia,JProcedencia> {

	@Override
	public Procedencia parseJsonToEntity(JProcedencia json) {
		Procedencia entity=null;
		if(json!=null) {
			entity = new Procedencia();
			entity.setId(json.getId());
			entity.setLugar(json.getLugar());
		}		
		return entity;	
	}

	@Override
	public JProcedencia parseEntityToJson(Procedencia entity) {
		JProcedencia json =null;
		if(entity!=null) {
			json=new JProcedencia();
			json.setId(entity.getId());
			json.setLugar(entity.getLugar());
		}
		return json;
	}

}
