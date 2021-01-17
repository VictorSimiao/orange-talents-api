package br.com.orange.talents.api.domain.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.orange.talents.api.domain.model.Aposta;
import br.com.orange.talents.api.domain.model.Pessoa;
import br.com.orange.talents.api.domain.repository.ApostaRepository;
import br.com.orange.talents.api.domain.repository.PessoaRepository;

@Service
public class ApostaService {

	@Autowired
	private ApostaRepository apostaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Aposta salvar(Pessoa pessoa) {
		Aposta aposta = new Aposta(pessoa);
		aposta.setNumero(gerarRadom(pessoa.getEmail()));
		pessoa.getApostas().add(aposta);
		pessoaRepository.save(pessoa);
		apostaRepository.save(aposta);
		return aposta;
	}

	private String gerarRadom(String email) {
		StringBuilder sb = new StringBuilder();
		Random aleatorio = new Random();
		String numeroSoteado;
		for (int i = 0; i < 6; i++) {
			sb.append((aleatorio.nextInt(60) + 1) + " ");
		}
		numeroSoteado = sb.toString();
		if (isNumeroDuplicado(numeroSoteado, email)) {
			gerarRadom(email);
		}
		return numeroSoteado;
	}

	private boolean isNumeroDuplicado(String numero, String email) {
		Aposta aposta = apostaRepository.findByNumeroAndPessoaEmail(numero, email);
		if (aposta == null) {
			return false;
		}
		return true;
	}
}
