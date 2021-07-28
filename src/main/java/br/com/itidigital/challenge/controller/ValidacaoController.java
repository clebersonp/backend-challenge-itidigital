package br.com.itidigital.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itidigital.challenge.service.ValidacaoService;
import br.com.itidigital.challenge.strategy.ValidacaoStrategy;
import br.com.itidigital.challenge.util.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/validacoes")
@Api(tags = { Constantes.VALIDACAO_SWAGGER_TAG })
public class ValidacaoController {

	private final ValidacaoService validacaoService;
	private final ValidacaoStrategy validacaoSenha;

	@Autowired
	public ValidacaoController(final ValidacaoService validacaoService,
			@Qualifier("validacaoSenha") final ValidacaoStrategy validacaoSenha) {
		this.validacaoService = validacaoService;
		this.validacaoSenha = validacaoSenha;
	}

	@ApiOperation(value = "Endpoint para validação de senha.")
	@GetMapping(path = "/v1/senha")
	public ResponseEntity<Boolean> validarSenha(
			@ApiParam(name = "test", value = "O valor a ser testado deve ser convertido no formato encode URL caso"
					+ " contenha caracteres especiais. A execução do service via swagger não deve ser realizada"
					+ " a conversão do valor pois o mesmo é convertido automaticamente.",
					example = "AbTp9!fo^k") @RequestParam(name = "test") final String test) {
		final boolean isValido = this.getValidacaoService().validar(test, this.getValidacaoSenha());
		return ResponseEntity.ok(isValido);
	}

	private ValidacaoService getValidacaoService() {
		return this.validacaoService;
	}

	private ValidacaoStrategy getValidacaoSenha() {
		return this.validacaoSenha;
	}

}
