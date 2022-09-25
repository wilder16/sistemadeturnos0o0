package com.clinicaodontologica.sistemadeturnos.repository;

import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
}
