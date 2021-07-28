package br.com.itidigital.challenge.service;

import org.springframework.stereotype.Service;

import br.com.itidigital.challenge.strategy.ValidacaoStrategy;

/**
 * 
 * Serviço de validação de dados.
 * 
 * @author cleberson
 *
 */
@Service
public class ValidacaoService {

	/**
	 * 
	 * Método responsável por delegar a responsabilidade de realizar o test de
	 * validade do dado para a instância concreta de {@code ValidacaoStrategy}.
	 * 
	 * @param test     dado a ser validado
	 * @param strategy instância concreta que realizará a validação do dado
	 * @return {@code true} se o dado for válido, {@code false} caso contrário
	 */
	public Boolean validar(final String test, final ValidacaoStrategy strategy) {
		return strategy.isValid(test);
	}

}
