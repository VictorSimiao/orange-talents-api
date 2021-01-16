package br.com.orange.talents.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.orange.talents.api.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
