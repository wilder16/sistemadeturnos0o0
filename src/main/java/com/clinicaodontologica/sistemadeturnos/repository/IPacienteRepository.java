package com.clinicaodontologica.sistemadeturnos.repository;

import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.dni = ?1")
    Optional<Paciente> findByDni(String dni);

}
