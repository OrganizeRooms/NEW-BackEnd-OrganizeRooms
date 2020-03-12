package br.com.organizerooms.services;

import java.util.Optional;

import br.com.organizerooms.models.Pessoa;

public interface PessoaServiceInterface {

	/**
	* Busca e retorna um usuário dado um email.
	*
	* @param pesEmail
	* @return Optional<Pessoa>
	*/
	Optional <Pessoa> buscarPessoaPorEmail(String pesEmail);
}
