package com.saucedo.molino.utils.parse.alamcen;

import org.springframework.stereotype.Service;

import com.saucedo.molino.almacen.models.Productor;
import com.saucedo.molino.utils.parse.IParse;
import com.saucedo.molino_json_models.almacen.JProductor;
@Service
public class ProductorParse implements IParse<Productor,JProductor> {

	@Override
	public Productor parseJsonToEntity(JProductor json) {
		Productor p = null;
		if (json != null) {
			p = new Productor();
			p.setId(json.getId());
			p.setDni(json.getDni());
			p.setNombre(json.getNombre());
			p.setApellidoPaterno(json.getApellidoPaterno());
			p.setApellidoMaterno(json.getApellidoMaterno());
			p.setDireccion(json.getDireccion());
			p.setTelefon(json.getTelefon());
			p.setEmail(json.getEmail());
		}
		return p;
	}

	@Override
	public JProductor parseEntityToJson(Productor entity) {
		JProductor jp = new JProductor();
		if (entity != null) {
			jp.setId(entity.getId());
			jp.setDni(entity.getDni());
			jp.setNombre(entity.getNombre());
			jp.setApellidoPaterno(entity.getApellidoPaterno());
			jp.setApellidoMaterno(entity.getApellidoMaterno());
			jp.setDireccion(entity.getDireccion());
			jp.setTelefon(entity.getTelefon());
			jp.setEmail(entity.getEmail());
		}
		return jp;
	}

}
