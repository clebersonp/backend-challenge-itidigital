package br.com.itidigital.challenge.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itidigital.challenge.BackendChallengeItidigitalApplication;

@SpringBootTest(classes = { BackendChallengeItidigitalApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ValidacaoControllerIntegracaoTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Test
	public void testSenhaDeveSerValida() {

		final String senhaValida = "Dk#%9d)$!";

		final StringBuilder sbUrl = new StringBuilder();
		sbUrl.append("http://localhost:");
		sbUrl.append(this.port);
		sbUrl.append(this.env.getProperty("server.servlet.context-path"));
		sbUrl.append("/validacoes/v1/senha");

		final UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(sbUrl.toString())
				.queryParam("test", senhaValida)
				// desabilitado encode, a url sera encode ao disparar a requisicao, evitando
				// duplo encoding
				.build(false);

		final Boolean isSenhaValidaEsperada = this.restTemplate.getForObject(uriComponents.toUri(), Boolean.class);

		Assertions.assertThat(isSenhaValidaEsperada).isTrue();
	}

	@Test
	public void testSenhaDeveSerInvalida() {

		final String senhaValida = "Dk^~#%9d)$!";

		final StringBuilder sbUrl = new StringBuilder();
		sbUrl.append("http://localhost:");
		sbUrl.append(this.port);
		sbUrl.append(this.env.getProperty("server.servlet.context-path"));
		sbUrl.append("/validacoes/v1/senha");

		final UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(sbUrl.toString())
				.queryParam("test", senhaValida)
				// desabilitado encode, a url sera encode ao disparar a requisicao, evitando
				// duplo encoding
				.build(false);

		final Boolean isSenhaValidaEsperada = this.restTemplate.getForObject(uriComponents.toUri(), Boolean.class);

		Assertions.assertThat(isSenhaValidaEsperada).isFalse();
	}

}
