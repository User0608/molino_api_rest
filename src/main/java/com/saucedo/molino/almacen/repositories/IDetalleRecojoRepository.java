package com.saucedo.molino.almacen.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.almacen.models.DetalleRecojo;

@Repository
public interface IDetalleRecojoRepository extends JpaRepository<DetalleRecojo,Long> {
	 
}
