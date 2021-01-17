package br.com.orange.talents.api.infrastructure.web.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.orange.talents.api.domain.model.Aposta;
import lombok.Getter;


@Getter
public class ApostaDto {

	private Long id;
	private String numeroSorteado;
	private LocalDateTime dataCadastro;
	

	public ApostaDto(Aposta aposta) {
		this.id = aposta.getId();
		this.numeroSorteado = aposta.getNumero();
		this.dataCadastro = aposta.getDataCadastro();	
	}


	public static List<ApostaDto> converter(List<Aposta> apostas) {
		return apostas.stream().map(ApostaDto::new).collect(Collectors.toList());
	}
	
	

}
