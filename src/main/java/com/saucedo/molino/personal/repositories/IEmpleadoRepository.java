package com.saucedo.molino.personal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saucedo.molino.personal.models.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado,Long> {

}

