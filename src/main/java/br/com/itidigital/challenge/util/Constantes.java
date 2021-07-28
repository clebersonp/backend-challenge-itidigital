package br.com.itidigital.challenge.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * Classe abstrata de constantes da aplicação
 * 
 * @author cleberson
 *
 */
public abstract class Constantes {
	
	/**
	 * Mapa para armazenar tags para documentação do Swagger
	 */
	private static final Map<String, String> SWAGGER_TAGS = new ConcurrentHashMap<>();

	/**
	 * 
	 * Tag para a controller de validação
	 * 
	 */
	public static final String VALIDACAO_SWAGGER_TAG = "Validação Controller";
	
	/**
	 * 
	 * Inicialização do Mapa de tags do Swagger.
	 * 
	 */
	static {
		Constantes.SWAGGER_TAGS.put(Constantes.VALIDACAO_SWAGGER_TAG, "Controller para Validações de dados");
	}
	
	/**
	 * 
	 * Recupera o Mapa de tags da documentação do Swagger
	 * 
	 * @return Mapa de tags do Swagger
	 */
	public static Map<String, String> getSwaggerTags() {
		return Constantes.SWAGGER_TAGS;
	}

}
