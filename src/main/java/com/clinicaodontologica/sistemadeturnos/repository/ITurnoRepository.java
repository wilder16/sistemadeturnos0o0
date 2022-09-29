package com.clinicaodontologica.sistemadeturnos.repository;

import com.clinicaodontologica.sistemadeturnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
