package com.saucedo.molino.almacen.controllers;


import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.parse.alamcen.DetalleTendidoParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JDetalleTendido;
import com.saucedo.molino.almacen.models.DetalleTendido;
import com.saucedo.molino.almacen.repositories.IDetalleTendidoRepository;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.ARROZ_INGRESO_SECADO_TENDIDO)
@PreAuthorize(HasRole.RECEPCION)
public class TendidoController {

	@Autowired
	private DetalleTendidoParse parse;
	@Autowired
	private IDetalleTendidoRepository repository;

	@RequestMapping(value = RoutePath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JDetalleTendido jtendido) {			
		DetalleTendido tendido=this.parse.parseJsonToEntity(jtendido);
		tendido.setFecha(LocalDate.now());
		tendido.setHora(LocalTime.now());
		this.repository.save(tendido);
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}
	
	@RequestMapping(value = RoutePath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JDetalleTendido jtendido) {	
		DetalleTendido tendido=this.repository.findById(jtendido.getId()).orElse(null);
		if (tendido==null) {
			return ResponseEntity.ok(new JResponse(JResponse.ERROR));
		}
		tendido.setUbicacion(jtendido.getUbicacion());
		this.repository.save(tendido);
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}

}
