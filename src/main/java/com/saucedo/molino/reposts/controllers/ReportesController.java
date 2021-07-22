package com.saucedo.molino.reposts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.security.HasRole;
import com.saucedo.molino_json_models.RProductor;
import com.saucedo.molino_json_models.reportes.RArroz;
import com.saucedo.molino_json_models.reportes.RIngresoArroz;
import com.saucedo.molino.almacen.repositories.IRegistroIngresoRepository;
import com.saucedo.molino.reposts.repositories.ReportRepository;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping()
@PreAuthorize(HasRole.ADMIN)
public class ReportesController {

	@Autowired
	private IRegistroIngresoRepository yearRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@RequestMapping(value = RoutePath.YEAR_REPOSRTS, method = RequestMethod.GET)
	ResponseEntity<?> years() {	
		List<String> ys = this.yearRepository.getYears();
		ys.add("all");
		return ResponseEntity.ok(ys);
	}
	
	@RequestMapping(value = RoutePath.PRODUCTORES_REPOSRTS, method = RequestMethod.GET)
	ResponseEntity<?> productores(@PathVariable(value = "year") String year) {	
		List<RProductor> productores = this.reportRepository.executeProductoresSP(year);
		return ResponseEntity.ok(productores);
	}
	@RequestMapping(value = RoutePath.ARROZ_REPOSRTS, method = RequestMethod.GET)
	ResponseEntity<?> rarroz(@PathVariable(value = "year") String year) {	
		List<RArroz> arrozs = this.reportRepository.executePsArroz(year);
		return ResponseEntity.ok(arrozs);
	}

	@RequestMapping(value = RoutePath.ARROZ_INGRESO_REPOSRTS, method = RequestMethod.GET)
	ResponseEntity<?> rarrozIngreso(@PathVariable(value = "year") String year) {	
		List<RIngresoArroz> arrozs = this.reportRepository.executePsArrozIngreso(year);
		return ResponseEntity.ok(arrozs);
	}
}
