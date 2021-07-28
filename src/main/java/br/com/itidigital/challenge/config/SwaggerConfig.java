package br.com.itidigital.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.itidigital.challenge.util.Constantes;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * Configuração da documentação do Swagger
 * 
 * @author cleberson
 *
 */
@Configuration
public class SwaggerConfig {
	
	/**
	 * 
	 * Configura <em>Bean</em> da documentação do Swagger
	 * 
	 * @return instância do <em>Bean</em> da documentação do Swagger
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.itidigital.challenge.controller"))
				.paths(PathSelectors.any()).build().apiInfo(this.apiInfo())
				.tags(new Tag(Constantes.VALIDACAO_SWAGGER_TAG,
						Constantes.getSwaggerTags().get(Constantes.VALIDACAO_SWAGGER_TAG)));
	}
	
	/**
	 * 
	 * Configura a instância de informações da API.
	 * 
	 * @return instância de informações da API
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Api de validações.")
				.description("Api que expõe serviços para validações de dados.")
				.contact(new Contact("Cleberson Pauluci", "https://github.com/clebersonp/",
						"pauluci.cleberson@gmail.com"))
				.version("1.0.0").build();
	}

}
