package br.com.orange.talents.api.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O e-mail não pode ser vazio")
	@Email(message = "O e-mail é inválido")
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Aposta> apostas = new ArrayList<>();
	
	
	public Pessoa() {
		
	}
	
	public Pessoa(String email) {
		this.email = email;
	}
	
}
