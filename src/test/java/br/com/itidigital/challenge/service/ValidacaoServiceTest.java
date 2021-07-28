package br.com.itidigital.challenge.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import br.com.itidigital.challenge.strategy.ValidacaoSenha;
import br.com.itidigital.challenge.strategy.ValidacaoStrategy;

@SpringBootTest
@MockBeans(value = { @MockBean(ValidacaoSenha.class) })
public class ValidacaoServiceTest {

	@Autowired
	private ValidacaoService validacaoService;

	@Autowired
	@Qualifier("validacaoSenha")
	private ValidacaoStrategy validacaoSenha;

	@Test
	public void testSenhaDeveSerValida() {
		final Boolean isSenhaValidaMock = Boolean.TRUE;
		final String senhaMock = "Abg8!qa%C";

		Mockito.when(this.validacaoSenha.isValid(ArgumentMatchers.anyString())).thenReturn(isSenhaValidaMock);

		final Boolean isSenhaValidaEsperada = this.validacaoService.validar(senhaMock, this.validacaoSenha);

		Assertions.assertThat(isSenhaValidaEsperada).isTrue();
	}

	@Test
	public void testSenhaDeveSerInvalida() {
		final Boolean isSenhaValidaMock = Boolean.FALSE;
		final String senhaMock = "AAbbg8!wf";

		Mockito.when(this.validacaoSenha.isValid(ArgumentMatchers.anyString())).thenReturn(isSenhaValidaMock);

		final Boolean isSenhaValidaEsperada = this.validacaoService.validar(senhaMock, this.validacaoSenha);

		Assertions.assertThat(isSenhaValidaEsperada).isFalse();
	}

}
