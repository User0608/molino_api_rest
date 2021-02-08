package com.saucedo.molino.almacen.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.almacen.models.LoteArroz;
import com.saucedo.molino.almacen.models.Procedencia;
import com.saucedo.molino.almacen.models.RegistroIngreso;
import com.saucedo.molino.almacen.models.TipoArroz;
import com.saucedo.molino.almacen.repositories.ILoteArrozRepository;
import com.saucedo.molino.almacen.repositories.IProcedenciaRepository;
import com.saucedo.molino.almacen.repositories.IRegistroIngresoRepository;
import com.saucedo.molino.almacen.repositories.ITipoArrozRepository;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.Valid;
import com.saucedo.molino.utils.parse.alamcen.LoteArrozParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.ARROZ_LOTE_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class IngresoController {
	@Autowired
	private ILoteArrozRepository repository;
	@Autowired
	private IProcedenciaRepository procedenciaRepository;
	
	@Autowired
	private ITipoArrozRepository tipoArrozRepository;
	
	@Autowired
	private IRegistroIngresoRepository ingresoRepository;

	@Autowired
	private LoteArrozParse parse;
	


	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<LoteArroz> lotesdearroz = this.repository.findAll();
		List<JLoteArroz> responseProductores = new ArrayList<JLoteArroz>();
		if (lotesdearroz != null) {
			for (LoteArroz emp : lotesdearroz) {				
				responseProductores.add(parse.parseEntityToJson(emp));
			}
		}
		return ResponseEntity.ok(responseProductores);
	}

	@RequestMapping(value = RoutePath.GET, method = RequestMethod.GET)
	ResponseEntity<?> get(@PathVariable long id) {
		LoteArroz arroz = this.repository.findById(id).orElse(null);
		if (arroz != null) {
			arroz.getTipoArroz();
			arroz.getProductor();
			arroz.getProcedencia();
			arroz.getIngreso();
			if (arroz.getIngreso() != null)
				arroz.getIngreso().getEmpleado();
			if (arroz.getIngreso() != null)
				arroz.getIngreso().getTransporte();
			JLoteArroz jemp = parse.parseEntityToJson(arroz);
			return ResponseEntity.ok(jemp);
		}
		return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
	}

	@RequestMapping(value = RoutePath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JLoteArroz je) {			
		if (je != null) {			
			je.getIngreso().setFecha(LocalDate.now());
			je.getIngreso().setHora(LocalTime.now());	
			LoteArroz lotearroz = parse.parseJsonToEntity(je);
			RegistroIngreso ingresoEntity =lotearroz.getIngreso();
			lotearroz.setIngreso(null);
			TipoArroz tipo = lotearroz.getTipoArroz();
			Procedencia pr = lotearroz.getProcedencia();			
			if(tipo.getId()==null) {
				this.tipoArrozRepository.save(tipo);
			}
			if(pr.getId()==null) {
				this.procedenciaRepository.save(pr);
			}
			
			this.repository.save(lotearroz);	
			ingresoEntity.setId(lotearroz.getId());
			this.ingresoRepository.save(ingresoEntity);
			return ResponseEntity.ok(new JResponse(JResponse.OK));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = RoutePath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JLoteArroz je) {		
		
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = RoutePath.DELETE, method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		if (Valid.isNumber(id)) {
			if (this.repository.findById(Long.parseLong(id)) != null) {
				this.repository.deleteById(Long.parseLong(id));
				return ResponseEntity.ok(new JResponse(JResponse.OK));
			}
			return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));

	}
}
