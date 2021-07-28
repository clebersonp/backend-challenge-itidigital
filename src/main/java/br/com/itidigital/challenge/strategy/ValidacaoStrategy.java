package br.com.itidigital.challenge.strategy;

/**
 * 
 * Interface para estratégia de validação de dados do tipo {@code String}.
 * 
 * @author cleberson
 *
 */
@FunctionalInterface
public interface ValidacaoStrategy {

	/**
	 * 
	 * Método responsável por verificar se o valor passado via parâmetro é
	 * considerado válido.
	 * 
	 * @param test valor a ser testado
	 * @return {@code true} se o valor for válido, {@code false} caso contrário
	 */
	boolean isValid(String test);
}
