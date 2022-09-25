package com.clinicaodontologica.sistemadeturnos.repository;

import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

}
