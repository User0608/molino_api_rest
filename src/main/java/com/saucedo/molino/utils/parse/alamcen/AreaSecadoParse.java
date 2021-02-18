package com.saucedo.molino.utils.parse.alamcen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.AreaSecado;
import com.saucedo.molino.almacen.models.Ubicacion;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JAreaSecado;
import com.saucedo.molino_json_models.almacen.JUbicacion;

@Service
public class AreaSecadoParse implements IParse<AreaSecado,JAreaSecado> {
	@Autowired()
	private UbicacionParse ubicacionParse;
	
	@Override
	public AreaSecado parseJsonToEntity(JAreaSecado json) {
		AreaSecado entity=null;
		if(json!=null) {
			entity = new AreaSecado();
			entity.setId(json.getId());
			entity.setUbicacion(json.getUbicacion());
			entity.setCapacidad(json.getCapacidad());
			if(json.getUbicaciones()!=null) {
				List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
				for(JUbicacion u:json.getUbicaciones()) {
					ubicaciones.add(this.ubicacionParse.parseJsonToEntity(u));
				}
				entity.setUbicaciones(ubicaciones);
			}			
		}		
		return entity;	
	}

	@Override
	public JAreaSecado parseEntityToJson(AreaSecado entity) {
		JAreaSecado json =null;
		if(entity!=null) {
			json=new JAreaSecado();
			json.setId(entity.getId());
			json.setUbicacion(entity.getUbicacion());
			json.setCapacidad(entity.getCapacidad());
			if(entity.getUbicaciones()!=null) {
				List<JUbicacion> jubicaciones = new ArrayList<JUbicacion>();
				for(Ubicacion u:entity.getUbicaciones()) {
					jubicaciones.add(this.ubicacionParse.parseEntityToJson(u));
				}
				json.setUbicaciones(jubicaciones);
			}			
		}
		return json;
	}

}
