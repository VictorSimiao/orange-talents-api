package br.com.orange.talents.api.domain.exception;

import lombok.Getter;

@Getter
public class ErroDeInputDto {
	private String campo;
	private String mensagemErro;
	
	public ErroDeInputDto(String campo, String erro) {
		this.campo = campo;
		this.mensagemErro = erro;
	}
}
