package br.com.orange.talents.api.infrastructure.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.talents.api.domain.model.Aposta;
import br.com.orange.talents.api.domain.model.Pessoa;
import br.com.orange.talents.api.domain.repository.ApostaRepository;
import br.com.orange.talents.api.domain.repository.PessoaRepository;
import br.com.orange.talents.api.domain.service.ApostaService;
import br.com.orange.talents.api.infrastructure.web.controller.dto.ApostaDto;
import br.com.orange.talents.api.infrastructure.web.controller.form.EmailForm;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/apostas")
@RestController
public class ApostaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ApostaService apostaService;
	@Autowired
	private ApostaRepository apostaRepository;

	@PostMapping
	public ResponseEntity<ApostaDto> criar(@RequestBody @Valid EmailForm emailForm, UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = emailForm.toModel(pessoaRepository);
		Aposta aposta = apostaService.salvar(pessoa);
		URI uri = uriBuilder.path("/apostas/{id}").buildAndExpand(aposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ApostaDto(aposta));
	}

	@GetMapping("/{email}")
	public ResponseEntity<List<ApostaDto>> pesquisarPorEmail(@PathVariable String email) {
		List<Aposta> apostas = apostaRepository.findByPessoaEmailOrderByDataCadastro(email);
		return ResponseEntity.ok(ApostaDto.converter(apostas));
	}
}
