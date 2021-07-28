# Descrição

API Rest desenvolvida para expor endpoint para validação de senha. Segue link da [especificação técnica 
challenge-itidigital](https://github.com/itidigital/backend-challenge/blob/master/README.md#descri%C3%A7%C3%A3o).

## Requerimentos

- Possuir *git* instalado na versão 2.30.0 ou superior. [Git installation](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
- Pode ser realizado o build e execução da aplicação via `docker file` ou se preferir via `maven` e `jdk-11`.

### Clone do projeto

Realizar o clone do projeto a partir do comando no diretório de sua escolha:
```
git clone https://github.com/clebersonp/backend-challenge-itidigital.git
```

### Build e execução docker file

- Versão docker 20.10.3 ou superior. [Docker installation](https://docs.docker.com/engine/install/).
- Executar o seguinte comando para o build:
```sh
docker run -it --user $(id -u):$(id -g) --rm --name challenge-itidigital -v "$(pwd)":/usr/src/challenge -w /usr/src/challenge maven:3.8.1-adoptopenjdk-11 mvn clean install
```
- Executar o seguinte comando para construir a imagem: 
  docker:
```sh
docker build -t challenge/itidigital .
```
- Executar a aplicação a partir da imagem criada:
```sh
docker run -p 8080:8080 -e JAVA_OPTS=-Dserver.port=8080 challenge/itidigital
```
> **_Notas:_**
>
> Assumindo estar no `diretório raiz do projeto` durante o `build` e `criação de imagem 
docker`;
>
> Assumindo que a porta `8080` seja escolhida para executar a aplicação;

### Build e execução via maven wrapper e jdk-11
- Versão do jdk 11 ou superior. [jdk-11 installation](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html).
- Executar o seguinte comando para o build:
```sh
./mvnw clean install
```
- Executar o seguinte comando para a execução da aplicação 
  a partir da porta desejada. Caso não passe a porta no argumento, por default será 
  executada na porta `8080`:
```sh
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```
> **_Notas:_**
>
> Assumindo estar no `diretório raiz do projeto` durante o `build` e `execução` da aplicação;
>
> Caso a versão defaut do jdk seja inferior a versão 11, deve ser informado o 
caminho da versão durante o build e execução da aplicação. Ex: `JAVA_HOME=/usr/lib/jvm/jdk-11.0.10/` ./mvnw clean install

## Doc API REST

Após a execução da aplicação, visualizar a documentação da API através do endereço: [Doc API 
Rest](http://localhost:8080/api-challenge-itidigital/swagger-ui/).

> **_Nota:_** Assumindo execução da aplicação na porta `8080`;

## Solução

Criado endpoint de método **_GET_** passando via **_query param_** a senha a ser 
  testada e validada.

Exemplo da chamada do serviço:
```sh
curl -X GET "http://localhost:8080/api-challenge-itidigital/validacoes/v1/senha?test=Dkfg%5E9d%213" -H "accept: */*"
```
> **_Nota:_** A senha deve ser convertida para o formato **_URL encoding_** por conter caracteres especiais 
na URL. Ex: A senha `Dkfg^9d!3` corresponde ao valor `Dkfg%5E9d%213`.


### Por que não foi utilizado o método **_POST_**?

O endpoint foi criado com o método **_GET_**, mesmo existindo a questão do _encoding URL_, 
por ser _idempotente_, ou seja, para múltiplas e idênticas requisições seu resultado será 
sempre o mesmo. Já o método **_POST_** não é considerado um método HTTP _idempotente_ 
pois remete o conceito de criação de novo recurso no servidor. Então, para cada nova 
requisição utilizando **_POST_**, em teoria, seria criado um novo recurso.

### Desing Pattern

Embora exista um único endpoint nessa API, o serviço foi desenvolvido pensando 
na existência de outros tipos de validações, como por exemplo: CPF, CNPJ, número de 
cartões, etc. Desta maneira, sabendo que pode existir uma família de algoritmos, o serviço foi criado utilizando 
o _Desing Pattern Strategy_. Onde existe um valor a ser testado/validado e seu algoritmo 
correspondente para realizar a validação. 


## Referências
- [Encoding URL](https://www.w3schools.com/tags/ref_urlencode.ASP) - Transmissão de caracteres especiais
