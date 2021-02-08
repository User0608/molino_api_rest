package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.CamionProductor;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JCamionProductor;

@Service
public class CamionProductorParse implements IParse<CamionProductor,JCamionProductor> {

	@Override
	public CamionProductor parseJsonToEntity(JCamionProductor json) {
		CamionProductor entity=null;
		if(json!=null) {
			entity = new CamionProductor();
			entity.setId(json.getId());
			entity.setPlaca(json.getPlaca());
			entity.setChofer(json.getChofer());
			entity.setDescripcion(json.getDescripcion());
		}		
		return entity;	
	}

	@Override
	public JCamionProductor parseEntityToJson(CamionProductor entity) {
		JCamionProductor json =null;
		if(entity!=null) {
			json=new JCamionProductor();
			json.setId(entity.getId());
			json.setPlaca(entity.getPlaca());
			json.setChofer(entity.getChofer());
			json.setDescripcion(entity.getDescripcion());
		}
		return json;
	}

}
