package com.saucedo.molino.almacen.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.almacen.models.RegistroIngreso;
@Repository
public interface IRegistroIngresoRepository extends JpaRepository<RegistroIngreso,Long> {
	@Query(value="select distinct year(ri.fecha) as year from registro_ingreso ri",nativeQuery = true)
	List<String> getYears();
}
