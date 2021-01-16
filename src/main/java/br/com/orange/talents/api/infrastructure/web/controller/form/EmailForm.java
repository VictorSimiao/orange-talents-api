package br.com.orange.talents.api.infrastructure.web.controller.form;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.orange.talents.api.domain.model.Pessoa;
import br.com.orange.talents.api.domain.repository.PessoaRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailForm {

	@NotBlank(message = "O e-mail não pode ser vazio")
	@Email(message = "O e-mail é inválido")
	private String email;

	public Pessoa toModel(PessoaRepository pessoaRepository) {
		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
		if (pessoa.isPresent()) {
			return pessoa.get();
		}
		return new Pessoa(email);
	}
}
