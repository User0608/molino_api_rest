package com.saucedo.molino.almacen.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.almacen.models.IngresoSecado;
import com.saucedo.molino.almacen.models.LoteSecado;
import com.saucedo.molino.almacen.repositories.IIngresoSecadoRepository;
import com.saucedo.molino.almacen.repositories.ILoteSecadoRepository;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.LoteSecadoParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.INGRESO_AREA_SECADO_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class IngresoSecadoController {
	@Autowired
	private ILoteSecadoRepository repository;
	@Autowired
	private IIngresoSecadoRepository ingresoSecadoRepository;
	

	@Autowired
	private LoteSecadoParse parse;

	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<LoteSecado> loteSecado = this.repository.findAll();
		List<JLoteSecado> responseLoteSecado = new ArrayList<JLoteSecado>();
		if (loteSecado != null) {
			for (LoteSecado emp : loteSecado) {				
				responseLoteSecado.add(parse.parseEntityToJson(emp));
			}
		}
		return ResponseEntity.ok(responseLoteSecado);
	}

	@RequestMapping(value = RoutePath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JLoteSecado js) {			
		LoteSecado loteSecado = this.parse.parseJsonToEntity(js);
		IngresoSecado ingreso =loteSecado.getIngreso();
		loteSecado.setIngreso(null);
		loteSecado.setFecha(LocalDate.now());
		this.repository.save(loteSecado);		
		ingreso.setId(loteSecado.getId());
		ingreso.setFecha(LocalDate.now());
		ingreso.setHora(LocalTime.now());
		this.ingresoSecadoRepository.save(ingreso);		
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}
	
	@RequestMapping(value = RoutePath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JLoteArroz je) {		
		
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

}
