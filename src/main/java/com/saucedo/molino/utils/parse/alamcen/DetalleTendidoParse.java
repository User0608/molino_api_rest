package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.DetalleTendido;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JDetalleTendido;

@Service
public class DetalleTendidoParse implements IParse<DetalleTendido,JDetalleTendido> {
	@Autowired
	DetalleRecojoParse detalleRecojoParse;
	
	@Override
	public DetalleTendido parseJsonToEntity(JDetalleTendido json) {
		DetalleTendido entity=null;
		if(json!=null) {
			entity = new DetalleTendido();
			entity.setId(json.getId());
			entity.setUbicacion(json.getUbicacion());
			entity.setHora(json.getHora());
			entity.setFecha(json.getFecha());
			entity.setDetalleRecojo(this.detalleRecojoParse.parseJsonToEntity(json.getDetalleRecojo()));
		}		
		return entity;	
	}

	@Override
	public JDetalleTendido parseEntityToJson(DetalleTendido entity) {
		JDetalleTendido json =null;
		if(entity!=null) {
			json=new JDetalleTendido();
			json.setId(entity.getId());
			json.setUbicacion(entity.getUbicacion());
			json.setHora(entity.getHora());
			json.setFecha(entity.getFecha());
			json.setDetalleRecojo(this.detalleRecojoParse.parseEntityToJson(entity.getDetalleRecojo()));			
		}
		return json;
	}

}
