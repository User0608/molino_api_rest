package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.TipoArroz;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JTipoArroz;
@Service
public class TipoArrozParse implements IParse<TipoArroz,JTipoArroz> {

	@Override
	public TipoArroz parseJsonToEntity(JTipoArroz json) {
		TipoArroz entity=null;
		if(json!=null) {
			entity = new TipoArroz();
			entity.setId(json.getId());
			entity.setNombre(json.getNombre());
			entity.setDescripcion(json.getDescripcion());
		}		
		return entity;	
	}

	@Override
	public JTipoArroz parseEntityToJson(TipoArroz entity) {
		JTipoArroz json =null;
		if(entity!=null) {
			json=new JTipoArroz();
			json.setId(entity.getId());
			json.setNombre(entity.getNombre());
			json.setDescripcion(entity.getDescripcion());
		}
		return json;
	}

}
