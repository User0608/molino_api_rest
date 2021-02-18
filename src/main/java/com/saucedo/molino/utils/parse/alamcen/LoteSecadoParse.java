package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.LoteSecado;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JLoteSecado;

@Service
public class LoteSecadoParse implements IParse<LoteSecado,JLoteSecado> {
	
	@Autowired
	IngresoSecadoParse ingresoParse;
	
	@Autowired
	UbicacionParse ubicacionParse;
	
	@Autowired
	LoteArrozParse loteParse;
	
	@Autowired
	DetalleTendidoParse detalleTendidoParse;
	
	@Override
	public LoteSecado parseJsonToEntity(JLoteSecado json) {
		LoteSecado entity=null;
		if(json!=null) {
			entity = new LoteSecado();
			entity.setId(json.getId());
			entity.setFecha(json.getFecha());
			entity.setTotalSacos(json.getTotalSacos());
			entity.setLotearroz(this.loteParse.parseJsonToEntity(json.getLotearroz()));
			entity.setIngreso(this.ingresoParse.parseJsonToEntity(json.getIngreso()));
			entity.setUbicacion(this.ubicacionParse.parseJsonToEntity(json.getUbicacion()));
			entity.setTendido(this.detalleTendidoParse.parseJsonToEntity(json.getTendido()));
		}		
		return entity;	
	}

	@Override
	public JLoteSecado parseEntityToJson(LoteSecado entity) {
		JLoteSecado json =null;
		if(entity!=null) {
			json=new JLoteSecado();
			json.setId(entity.getId());
			json.setFecha(entity.getFecha());
			json.setTotalSacos(entity.getTotalSacos());
			json.setLotearroz(this.loteParse.parseEntityToJson(entity.getLotearroz()));
			json.setIngreso(this.ingresoParse.parseEntityToJson(entity.getIngreso()));
			json.setUbicacion(this.ubicacionParse.parseEntityToJson(entity.getUbicacion()));
			json.setTendido(this.detalleTendidoParse.parseEntityToJson(entity.getTendido()));
			
		}
		return json;
	}

}
