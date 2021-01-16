package br.com.orange.talents.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.orange.talents.api.domain.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
	Aposta findByNumeroAndPessoaEmail(String numero, String email);
}
