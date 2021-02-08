package com.saucedo.molino.almacen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.almacen.models.Productor;
import com.saucedo.molino.almacen.repositories.IProductorRepository;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.utils.Valid;
import com.saucedo.molino.utils.parse.alamcen.ProductorParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(APIProductorPath.CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class ProductorController {
	@Autowired
	private IProductorRepository productorRepository;
	
	@Autowired
	private ProductorParse parse;

	@RequestMapping(value = APIProductorPath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<Productor> productores = this.productorRepository.findAll();
		List<JProductor> responseProductores = new ArrayList<JProductor>();
		if (productores != null) {
			for (Productor p : productores) {
				responseProductores.add(parse.parseEntityToJson(p));
			}
		}
		return ResponseEntity.ok(responseProductores);
	}

	@RequestMapping(value = APIProductorPath.GET, method = RequestMethod.GET)
	ResponseEntity<?> get(@PathVariable long id) {
		return ResponseEntity
				.ok(parse.parseEntityToJson(this.productorRepository.findById(id).orElse(null)));
	}
	@RequestMapping(value = APIProductorPath.GET_DNI, method = RequestMethod.GET)
	ResponseEntity<?> get(@PathVariable String dni) {
		return ResponseEntity
				.ok(parse.parseEntityToJson(this.productorRepository.findByDni(dni).orElse(null)));
	}

	@RequestMapping(value = APIProductorPath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JProductor jp) {
		Productor productor = parse.parseJsonToEntity(jp);
		if (productor != null) {
			this.productorRepository.save(productor);
			return ResponseEntity.ok(new JResponse(JResponse.OK));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = APIProductorPath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JProductor jp) {
//		System.out.println("Server Side: "+jp.toString());
		Productor productor = parse.parseJsonToEntity(jp);
		//this.productorRepository.save(productor);
		if (productor != null && jp.getId() != null) {
			if (this.productorRepository.findById(jp.getId()) != null) {
				this.productorRepository.save(productor);
				return ResponseEntity.ok(new JResponse(JResponse.OK));
			}
			return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = APIProductorPath.DELETE, method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		if (Valid.isNumber(id)) {
			if (this.productorRepository.findById(Long.parseLong(id)) != null) {
				this.productorRepository.deleteById(Long.parseLong(id));
				return ResponseEntity.ok(new JResponse(JResponse.OK));
			}
			return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));

	}

}
