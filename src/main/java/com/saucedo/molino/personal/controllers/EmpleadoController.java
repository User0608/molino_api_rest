package com.saucedo.molino.personal.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saucedo.molino.personal.models.Empleado;
import com.saucedo.molino.personal.repositories.IEmpleadoRepository;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino.security.repositories.KRoleRepository;
import com.saucedo.molino.security.services.UsuarioService;
import com.saucedo.molino.utils.Valid;
import com.saucedo.molino.utils.parse.EmpleadoParse;
import com.saucedo.molino.utils.parse.UsuarioParse;
import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.personal.JEmpleado;
import com.saucedo.molino_json_models.security.JUsuario;
import com.saucedo.molino.routes.*;

@RestController
@RequestMapping(RoutePath.EMPLEADO_CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class EmpleadoController {
	@Autowired
	private  IEmpleadoRepository empleadoRepository;
	
	@Autowired
	private  KRoleRepository roleRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpleadoParse parse;
	@Autowired
	private UsuarioParse Usuarioparse;

	@RequestMapping(value = RoutePath.GET_ALL, method = RequestMethod.GET)
	ResponseEntity<?> getAll() {
		List<Empleado> productores = this.empleadoRepository.findAll();
		List<JEmpleado> responseProductores = new ArrayList<JEmpleado>();
		if (productores != null) {
			for (Empleado emp : productores) {
				responseProductores.add(parse.parseEntityToJson(emp));
			}
		}
		return ResponseEntity.ok(responseProductores);
	}

	@RequestMapping(value = RoutePath.GET, method = RequestMethod.GET)
	ResponseEntity<?> get(@PathVariable long id) {
		Empleado empleado = this.empleadoRepository.findById(id).orElse(null);
		if (empleado != null) {
			this.empleadoRepository.save(empleado);
			Usuario usuario = this.usuarioService.findUsuarioByOwner("empleado."+id);
			JEmpleado jemp=parse.parseEntityToJson(empleado);
			if(usuario!=null) jemp.setUsuario(this.Usuarioparse.parseEntityToJson(usuario));
			return ResponseEntity.ok(jemp);
		}
		return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));		
	}

	@RequestMapping(value = RoutePath.INSERT, method = RequestMethod.POST)
	ResponseEntity<?> register(@RequestBody JEmpleado je) {
		je.setFechaContrato(LocalDate.now());
		Empleado empleado = parse.parseJsonToEntity(je);
		JUsuario ju = je.getUsuario();
		if(ju!=null) {			
			Usuario oldUsuario = this.usuarioService.findUsuarioByUsername(ju.getUsername());
			if (oldUsuario != null)
				return ResponseEntity.ok(new JResponse(JResponse.ERROR_USUARI_EXISTE));
		}
		if (empleado != null) {
			this.empleadoRepository.save(empleado);
			if(ju!=null) {
				List<KRole> listOfRoles = this.roleRepository.findAll();
				ju.setOwner("empleado."+empleado.getId().toString());
				Usuario u = this.Usuarioparse.parseJsonToEntity(ju, listOfRoles);		
				this.usuarioService.save(u);
			}
			return ResponseEntity.ok(new JResponse(JResponse.OK));
		}		
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = RoutePath.UPDATE, method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody JEmpleado je) {
		Empleado empleado = parse.parseJsonToEntity(je);
		if (empleado != null && je.getId() != null) {
			if (this.empleadoRepository.findById(je.getId()) != null) {
				this.empleadoRepository.save(empleado);
				JUsuario ju = je.getUsuario();
				if(ju!=null) {
					List<KRole> listOfRoles = this.roleRepository.findAll();
					ju.setOwner("empleado."+empleado.getId().toString());
					Usuario u = this.Usuarioparse.parseJsonToEntity(ju, listOfRoles);		
					this.usuarioService.save(u);
				}
				return ResponseEntity.ok(new JResponse(JResponse.OK));
			}
			return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));
	}

	@RequestMapping(value = RoutePath.DELETE, method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		if (Valid.isNumber(id)) {
			if (this.empleadoRepository.findById(Long.parseLong(id)) != null) {
				this.empleadoRepository.deleteById(Long.parseLong(id));
				return ResponseEntity.ok(new JResponse(JResponse.OK));
			}
			return ResponseEntity.ok(new JResponse(JResponse.REGISTRO_NOT_FOUND));
		}
		return ResponseEntity.ok(new JResponse(JResponse.ERROR));

	}
}
