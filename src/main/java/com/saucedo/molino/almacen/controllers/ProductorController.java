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
import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molino.routes.*;




@RestController
@RequestMapping(APIProductorPath.CONTROLLER_PATH)
@PreAuthorize(HasRole.RECEPCION)
public class ProductorController {
    @Autowired
    private IProductorRepository productorRepository;

 
    @RequestMapping(value = APIProductorPath.GET_ALL, method = RequestMethod.GET)   
    ResponseEntity<?> getAll() {
        List<Productor> productores= this.productorRepository.findAll();
        List<JProductor> responseProductores = new ArrayList<JProductor>();
        if(productores!=null) {
        	for(Productor p:productores) {
        		responseProductores.add(this.parseProductorToJSONProductor(p));
        	}
        }
    	return ResponseEntity.ok(responseProductores);
    }

   
    @RequestMapping(value =APIProductorPath.GET, method = RequestMethod.GET)
    Productor get(@PathVariable long id) {
        return this.productorRepository.findById(id).orElse(null);
    }

   
    @RequestMapping(value = APIProductorPath.INSERT, method = RequestMethod.POST)
    Productor register(@RequestBody Productor productor) {
       this.productorRepository.save(productor);
       return productor;
    }

 
    @RequestMapping(value = APIProductorPath.UPDATE, method = RequestMethod.PUT)
    Productor update(@RequestBody Productor productor) {
    	 this.productorRepository.save(productor);
         return productor;
    }
    
    @RequestMapping(value = APIProductorPath.DELETE, method = RequestMethod.DELETE)
    void delete(@PathVariable long id) {
        this.productorRepository.deleteById(id);
    }
    
    private JProductor parseProductorToJSONProductor(Productor p) {
    	JProductor jp = new JProductor();
    	if(p!=null) {
    		jp.setId(p.getId());
        	jp.setDni(p.getDni());
        	jp.setNombre(p.getNombre());
        	jp.setApellidoPaterno(p.getApellidoPaterno());
        	jp.setApellidoMaterno(p.getApellidoMaterno());
        	jp.setDireccion(p.getDireccion());
        	jp.setTelefon(p.getTelefon());
        	jp.setEmail(p.getEmail());
    	}
    	return jp;
    }

}
