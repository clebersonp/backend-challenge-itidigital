package br.com.itidigital.challenge.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.itidigital.challenge.service.ValidacaoService;
import br.com.itidigital.challenge.strategy.ValidacaoStrategy;

@WebMvcTest(controllers = { ValidacaoController.class })
public class ValidacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ValidacaoService validacaoService;

	@Qualifier("validacaoSenha")
	@MockBean
	private ValidacaoStrategy validacaoSenha;

	@Test
	public void testSenhaDeveSerValida() throws Exception {
		final Boolean isSenhaValidaMock = Boolean.TRUE;
		final String senhaValidaMock = "Eb%#eA81-";

		Mockito.when(this.validacaoService.validar(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(ValidacaoStrategy.class))).thenReturn(isSenhaValidaMock);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/validacoes/v1/senha").param("test", senhaValidaMock))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
						MockMvcResultMatchers.content().string(Matchers.containsString(isSenhaValidaMock.toString())));
	}
	
	@Test
	public void testSenhaDeveSerInvalida() throws Exception {
		final Boolean isSenhaValidaMock = Boolean.FALSE;
		final String senhaValidaMock = "Eb~%#eA81-";

		Mockito.when(this.validacaoService.validar(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(ValidacaoStrategy.class))).thenReturn(isSenhaValidaMock);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/validacoes/v1/senha").param("test", senhaValidaMock))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
						MockMvcResultMatchers.content().string(Matchers.containsString(isSenhaValidaMock.toString())));
	}

}
