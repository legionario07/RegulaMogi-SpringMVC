package br.com.regulamogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.regulamogi.model.Conta;
import br.com.regulamogi.model.Perfil;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findByPerfilNotLike(Perfil perfil);
	
}
