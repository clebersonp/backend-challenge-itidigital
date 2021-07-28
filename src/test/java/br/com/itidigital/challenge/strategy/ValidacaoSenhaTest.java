package br.com.itidigital.challenge.strategy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidacaoSenhaTest {

	@Autowired
	@Qualifier("validacaoSenha")
	private ValidacaoStrategy validacaoSenha;

	@Test
	public void testSenhaDeveSerValida() {
		final String senha = "P4b#Tf9%@";

		final boolean isSenhaValidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaValidaEsperada).isTrue();
	}

	@Test
	public void testSenhaDevePossuirQuantidadeCaracteresInvalido() {
		final String senha = "&*qW4cBb";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaSenhaVazia() {
		final String senha = "";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaPossuindoCaracteresRepetidos() {
		final String senha = "(-+Q1v)-+#";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaNaoPossuindoCombinacaoLetrasMaiusculasMinusculas() {
		final String senha = "+)!WF569B";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaNaoPossuindoDigitos() {
		final String senha = "^@GplQ#!*";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaNaoPossuindoCaracteresEspeciaisAceitos() {
		final String senha = "AbcD1894P";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}
	
	@Test
	public void testSenhaDeveSerInvalidaPossuindoCaracteresEspeciaisNaoAceitos() {
		final String senha = "Ab D_18!$%9Çç~â";

		final boolean isSenhaInvalidaEsperada = this.validacaoSenha.isValid(senha);

		Assertions.assertThat(isSenhaInvalidaEsperada).isFalse();
	}

}
