package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.LoteArroz;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JLoteArroz;

@Service
public class LoteArrozParse implements IParse<LoteArroz,JLoteArroz> {
	@Autowired
	ProductorParse productorParse;
	@Autowired
	TipoArrozParse tipoarrozParse;
	@Autowired
	ProcedenciaParse procedenciaParse;
	@Autowired 
	RegistroIngresoParse registroIngresoParse;
	
	@Override
	public LoteArroz parseJsonToEntity(JLoteArroz json) {
		LoteArroz entity=null;
		if(json!=null) {
			entity = new LoteArroz();
			entity.setId(json.getId());
			entity.setNumeroSacos(json.getNumeroSacos());
			entity.setProductor(this.productorParse.parseJsonToEntity(json.getProductor()));
			entity.setProcedencia(this.procedenciaParse.parseJsonToEntity(json.getProcedencia()));
			entity.setTipoArroz(this.tipoarrozParse.parseJsonToEntity(json.getTipoArroz()));
			entity.setIngreso(this.registroIngresoParse.parseJsonToEntity(json.getIngreso()));
		}		
		return entity;	
	}

	@Override
	public JLoteArroz parseEntityToJson(LoteArroz entity) {
		JLoteArroz json =null;
		if(entity!=null) {
			json=new JLoteArroz();
			json.setId(entity.getId());
			json.setNumeroSacos(entity.getNumeroSacos());
			json.setProductor(this.productorParse.parseEntityToJson(entity.getProductor()));
			json.setProcedencia(this.procedenciaParse.parseEntityToJson(entity.getProcedencia()));
			json.setTipoArroz(this.tipoarrozParse.parseEntityToJson(entity.getTipoArroz()));
			json.setIngreso(this.registroIngresoParse.parseEntityToJson(entity.getIngreso()));
		}
		return json;
	}

}
