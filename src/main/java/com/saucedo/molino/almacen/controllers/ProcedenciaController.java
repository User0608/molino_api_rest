package com.saucedo.molino.almacen.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.ProcedenciaParse;
import com.saucedo.molino_json_models.almacen.JProcedencia;
import com.saucedo.molino.almacen.models.Procedencia;
import com.saucedo.molino.almacen.repositories.IProcedenciaRepository;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.ARROZ_PROCEDENCIA_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class ProcedenciaController {	
	
	@Autowired
	IProcedenciaRepository repository;
	@Autowired
	ProcedenciaParse parser;
	

	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<Procedencia> procedencias = this.repository.findAll();
		List<JProcedencia> jsonProcedencias = new ArrayList<JProcedencia>();
		for(Procedencia procedencia:procedencias) {
			jsonProcedencias.add(this.parser.parseEntityToJson(procedencia));
		}
		return ResponseEntity.ok(jsonProcedencias);
	}
}
