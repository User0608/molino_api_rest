package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.DetalleRecojo;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JDetalleRecojo;

@Service
public class DetalleRecojoParse implements IParse<DetalleRecojo,JDetalleRecojo> {

	@Override
	public DetalleRecojo parseJsonToEntity(JDetalleRecojo json) {
		DetalleRecojo entity=null;
		if(json!=null) {
			entity = new DetalleRecojo();
			entity.setId(json.getId());
			entity.setNumeroSacos(json.getNumeroSacos());
			entity.setHumedad(json.getHumedad());
			entity.setFecha(json.getFecha());
			entity.setHora(json.getHora());		
		}		
		return entity;	
	}

	@Override
	public JDetalleRecojo parseEntityToJson(DetalleRecojo entity) {
		JDetalleRecojo json =null;
		if(entity!=null) {
			json=new JDetalleRecojo();
			json.setId(entity.getId());
			json.setNumeroSacos(entity.getNumeroSacos());
			json.setHumedad(entity.getHumedad());
			json.setFecha(entity.getFecha());
			json.setHora(entity.getHora());
		}
		return json;
	}

}
