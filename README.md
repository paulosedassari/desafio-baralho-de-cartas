# desafio-baralho-de-cartas - microservice em java e springboot.

![Java](https://img.shields.io/badge/Java-17-green?style=plastic&logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.2.6-green?style=plastic&logo=spring)
![Spring Cloud](https://img.shields.io/badge/SpringCloud-2023.0.2-green?style=plastic&logo=spring)
![Feign](https://img.shields.io/badge/OpenFeign-green?style=plastic&logo=feign)
![JUnit](https://img.shields.io/badge/JUnit-5-green?style=plastic&)
![Maven](https://img.shields.io/badge/Maven-green?style=plastic)

O **desafio-baralho-de-cartas** é um microsserviço para realizar o desafio das cartas. Oferece duas modalidades de partida
e registra os dados de jogo na base de dados.

### ÍNDICE

1. [ Principais caracterísitas e responsabilidades deste Microsserviço ](#principaisCaracteristicasResponsabilidade)
2. [ Swagger ](#swagger)
3. [ Endpoints Disponíveis no Microsserviço ](#acessandoaplicacao)
4. [ Acessando a aplicação na AWS ](#aws)
    <br /><br /><br />

<a name="principaisCaracteristicasResponsabilidade"></a>
## Principais caracterísitas e responsabilidades deste microsserviço

- Realiza o desafio das cartas em duas modalidades diferentes: sem jogador e com jogador.
- Registra os dados da partida na base de dados.
- Permite consultar os registros dos jogos anteriores, assim como consultar pelo nome do jogador.

<a name="swagger"></a>
### Swagger

- Link da interface gráfica da aplicação para o Swagger UI
    - [Executando Localmente - Profile LOCAL](http://localhost:8080/swagger-ui/index.html)
- Para utilização em outro ambiente deverá utilizar o IP do servidor:
  http://IP_SERVIDOR:8080/swagger-ui/index.html

<a name="acessandoaplicacao"></a>
### Acessando a aplicação

Verifique qual o host a app está acessivel, localmente é usado o endereço http://localhost:8080/

- /cartas/desafio/informacoes - Retorna informações sobre o Microsserviço.
- /cartas/desafio - Realiza o desafio sem jogador.
- /cartas/desafio/partida - Realiza o desafio com jogador.
- /rodadas/sem-jogador - Retorna todos os registros sem jogador.
- /rodadas/com-jogador - Retorna todos os registros com jogador.

<a name="aws"></a>
### AWS

O microsserviço está disponível na AWS, publicado juntamente com uma interface para facilitar a interação com a aplicação.

- Link: http://ec2-3-80-216-220.compute-1.amazonaws.com/#
- As requisições estão disponíveis na pasta /collection na raiz do srv.