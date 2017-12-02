package br.com.regulamogi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.regulamogi.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	
}
