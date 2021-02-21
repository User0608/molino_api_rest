package com.saucedo.molino.almacen.controllers;


import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.DetalleRecojoParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JDetalleRecojo;
import com.saucedo.molino.almacen.models.DetalleRecojo;
import com.saucedo.molino.almacen.repositories.IDetalleRecojoRepository;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.ARROZ_INGRESO_SECADO_RECOJO)
@PreAuthorize(HasRole.RECEPCION)
public class RecojoController {

	@Autowired
	private DetalleRecojoParse parse;
	@Autowired
	private IDetalleRecojoRepository repository;

	@RequestMapping(value = RoutePath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JDetalleRecojo jrecojo) {			
		DetalleRecojo recojo=this.parse.parseJsonToEntity(jrecojo);
		recojo.setFecha(LocalDate.now());
		recojo.setHora(LocalTime.now());
		this.repository.save(recojo);
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}
	
	@RequestMapping(value = RoutePath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JDetalleRecojo jrecojo) {		
		DetalleRecojo recojo=this.repository.findById(jrecojo.getId()).orElse(null);
		if (recojo==null) {
			return ResponseEntity.ok(new JResponse(JResponse.ERROR));
		}
		recojo.setHumedad(jrecojo.getHumedad());
		recojo.setNumeroSacos(jrecojo.getNumeroSacos());
		this.repository.save(recojo);
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}

}
