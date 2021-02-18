package com.saucedo.molino.almacen.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.almacen.models.IngresoSecado;

@Repository
public interface IIngresoSecadoRepository extends JpaRepository<IngresoSecado,Long> {
	 
}
