package com.saucedo.molino.almacen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.almacen.models.AreaSecado;
import com.saucedo.molino.almacen.repositories.IAreaSecadoRepository;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.AreaSecadoParse;
import com.saucedo.molino_json_models.almacen.JAreaSecado;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.AREA_SECADO_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class AreaSecadoController {
	@Autowired
	private IAreaSecadoRepository repository;
	@Autowired
	private AreaSecadoParse parse;
	

	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<AreaSecado> lotesdearroz = this.repository.findAll();
		List<JAreaSecado> responseProductores = new ArrayList<JAreaSecado>();
		if (lotesdearroz != null) {
			for (AreaSecado emp : lotesdearroz) {				
				responseProductores.add(parse.parseEntityToJson(emp));
			}
		}
		return ResponseEntity.ok(responseProductores);
	}

}
