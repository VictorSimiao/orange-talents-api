package br.com.orange.talents.api.infrastructure.web.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orange.talents.api.domain.model.Aposta;
import br.com.orange.talents.api.domain.model.Pessoa;
import br.com.orange.talents.api.domain.repository.PessoaRepository;
import br.com.orange.talents.api.domain.service.ApostaService;
import br.com.orange.talents.api.infrastructure.web.controller.dto.ApostaDto;
import br.com.orange.talents.api.infrastructure.web.controller.form.EmailForm;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/apostas")
@RestController
public class ApostaController {
	@Autowired
	private PessoaRepository PessoaRepository;
	@Autowired
	private ApostaService apostaService;
	
	@PostMapping
	public ResponseEntity<ApostaDto> criar(@RequestBody @Valid EmailForm emailForm,UriComponentsBuilder uriBuilder){
		Pessoa pessoa = emailForm.toModel(PessoaRepository);
		
		Aposta aposta = apostaService.salvar(pessoa);
		URI uri = uriBuilder.path("/apostas/{id}").buildAndExpand(aposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ApostaDto(aposta));
	}

}
