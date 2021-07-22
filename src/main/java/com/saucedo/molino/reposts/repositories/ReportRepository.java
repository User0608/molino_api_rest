package com.saucedo.molino.reposts.repositories;

 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.saucedo.molino_json_models.RProductor;
import com.saucedo.molino_json_models.reportes.RArroz;
import com.saucedo.molino_json_models.reportes.RIngresoArroz;

@Service
public class ReportRepository {

	 @PersistenceContext // or even @Autowired
	    private EntityManager entityManager;
    
//    @Autowired
//    public void procedureInvoker(final EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
    public List<RArroz> executePsArroz(String year){
    	List<Object[]> results = this.executewith("year",year,"sp_arroz_mas_ingresado");
    	List<RArroz> productores=new ArrayList<RArroz>();
    	if (results!=null) {
    		for(Object[] row:results) {
    			productores.add(new RArroz(
    					(String)row[0],
    					(String)row[1],    				
    					((BigDecimal)row[2]).toString()    							
    				));
        	}
    	}	 
    	return productores;
    }
    public List<RIngresoArroz> executePsArrozIngreso(String year){
    	List<Object[]> results = this.executewith("year",year,"sp_arroz_ingreso_mes");
    	List<RIngresoArroz> productores=new ArrayList<RIngresoArroz>();
    	if (results!=null) {
    		for(Object[] row:results) {
    			productores.add(new RIngresoArroz(
    					(String)row[0],
    					row[1].toString()					
    				));
        	}
    	}	 
    	return productores;
    }
    
    
    public List<RProductor> executeProductoresSP(String year){
    	List<Object[]> results = this.executewith("year",year,"sp_productores");
    	List<RProductor> productores=new ArrayList<RProductor>();
    	if (results!=null) {
    		for(Object[] row:results) {
    			productores.add(new RProductor(
    					(String)row[0],
    					(String)row[1],
    					(String)row[2],
    					(String)row[3],
    					(String)row[4],
    					((BigDecimal)row[5]).longValue()
    							
    				));
        	}
    	}	 
    	return productores;
    }
    public  List<Object[]> executewith(String name,String year,String ps) {
    	List<Object[]> results = new ArrayList<>();
    	if (entityManager.isOpen()){    		
    	
    	StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(ps);
    	storedProcedureQuery.registerStoredProcedureParameter(name, String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("year", year);        
    	storedProcedureQuery.execute();
    	 synchronized (this) {    		 
    		 results = storedProcedureQuery.getResultList();   
    	 }
    	}
		return results;	
    	
    }
    
}
