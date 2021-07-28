package br.com.itidigital.challenge.strategy;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Classe concreta do tipo {@code ValidacaoStrategy} contendo sua estratégia
 * específica para validação de senha.
 * 
 * @author cleberson
 * @see {@link ValidacaoStrategy}
 *
 */
@Component("validacaoSenha")
@Slf4j
public class ValidacaoSenha implements ValidacaoStrategy {

	private static final String DIGITOS_LETRAS = "\\d*[A-Za-z]*";
	private static final int QUANTIDADE_CARACTERES_VALIDO = 9;
	private static final String CARACTERES_ESPECIAIS_ACEITOS = "!@#$%^&*()-+";

	/**
	 * 
	 * Método responsável por validar a senha seguindo as seguintes definições:
	 * 
	 * <ol>
	 * <li>Nove ou mais caracteres;</li>
	 * <li>Ao menos 1 dígito;</li>
	 * <li>Ao menos 1 letra minúscula;</li>
	 * <li>Ao menos 1 letra maiúscula;</li>
	 * <li>Ao menos 1 caractere especial. Considere como especial os seguintes
	 * caracteres: !@#$%^&*()-+</li>
	 * <li>Não possuir caracteres repetidos dentro do conjunto</li>
	 * <li>Espaços em branco não devem ser considerados como caracteres válidos</li>
	 * </ol>
	 * 
	 * <pre>
	 * 
	 * Exemplos:
	 * 
	 * IsValid("") // false
	 * IsValid("aa") // false
	 * IsValid("ab") // false
	 * IsValid("AAAbbbCc") // false
	 * IsValid("AbTp9!foo") // false
	 * IsValid("AbTp9!foA") // false
	 * IsValid("AbTp9 fok") // false
	 * IsValid("AbTp9!fok") // true
	 * </pre>
	 * 
	 */
	@Override
	public boolean isValid(final String senha) {
		log.info("SENHA A VALIDAR: {}", senha);

		final Predicate<String> hasQtdCaracteresValidos = (String test) -> StringUtils.isNotBlank(test)
				&& test.length() >= QUANTIDADE_CARACTERES_VALIDO;

		final Predicate<String> hasCombinacaoLetrasMaiusculaMinuscula = (String test) -> StringUtils.isMixedCase(test);

		final Predicate<String> hasDigito = (String test) -> StringUtils.isNotBlank(StringUtils.getDigits(test));

		final Predicate<String> hasCaracteresUnicos = (String test) -> this.hasCaracteresUnicos(test);

		final Predicate<String> hasCaracterEspecial = (String test) -> this.hasCaracterEspecial(test);

		final Predicate<String> composicaoValidacaoSenha = hasQtdCaracteresValidos
				.and(hasCombinacaoLetrasMaiusculaMinuscula).and(hasDigito).and(hasCaracteresUnicos)
				.and(hasCaracterEspecial);

		return composicaoValidacaoSenha.test(senha);
	}

	/**
	 * 
	 * Verifica se o valor possui algum caracter especial e se os caracteres
	 * especiais encontrados são somente os aceitos.
	 * 
	 * @param value valor a ser testado
	 * @return {@code true} se possuir somente os caracteres especiais aceitos
	 * @see {@link #CARACTERES_ESPECIAIS_ACEITOS}
	 */
	private boolean hasCaracterEspecial(final String value) {
		final String caracteresEspeciais = value.replaceAll(DIGITOS_LETRAS, StringUtils.EMPTY);
		return StringUtils.isNotBlank(caracteresEspeciais)
				&& StringUtils.containsOnly(caracteresEspeciais, CARACTERES_ESPECIAIS_ACEITOS);
	}

	/**
	 * 
	 * Testa o valor informado para verificar se a cadeia de caracteres são unicas,
	 * ou seja, não possui caracter repetido.
	 * 
	 * @param value valor a ser testado
	 * @return {@code true} se não possuir caracter repetido, {@code false} caso
	 *         contrário.
	 */
	private boolean hasCaracteresUnicos(final String value) {
		for (int i = 0; i < value.length(); i++) {
			for (int j = i + 1; j < value.length(); j++) {
				if (value.charAt(i) == value.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
}
