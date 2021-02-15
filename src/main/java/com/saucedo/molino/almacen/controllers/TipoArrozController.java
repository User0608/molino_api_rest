package com.saucedo.molino.almacen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.TipoArrozParse;
import com.saucedo.molino_json_models.almacen.JTipoArroz;
import com.saucedo.molino.almacen.models.TipoArroz;
import com.saucedo.molino.almacen.repositories.ITipoArrozRepository;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.ARROZ_TIPO_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class TipoArrozController {	
	
	@Autowired
	ITipoArrozRepository repository;
	
	@Autowired
	TipoArrozParse parser;
	

	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<TipoArroz> procedencias = this.repository.findAll();
		List<JTipoArroz> jsonProcedencias = new ArrayList<JTipoArroz>();
		for(TipoArroz procedencia:procedencias) {
			jsonProcedencias.add(this.parser.parseEntityToJson(procedencia));
		}
		return ResponseEntity.ok(jsonProcedencias);
	}
}
