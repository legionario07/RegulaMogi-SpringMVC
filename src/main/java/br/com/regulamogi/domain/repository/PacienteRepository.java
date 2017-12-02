package br.com.regulamogi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.regulamogi.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
