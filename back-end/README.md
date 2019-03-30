# Visits Routes

O sistemas Visits Routes foi desenvolvido para criação de rotas de visitas a clientes, para os funcionários de acordo com a geolocalização dos mesmos. 

# Arquitetura
- Microservice
- REST

# Tecnologias Utilizadas
- **Quarkus** - Supersonic Subatomic Java
Lançado 
Lançado em Março de 2019, [Quarkus](https://quarkus.io/) é um framework Java Kubernetes-Native projetado para Java Virtual Machines (JVMs), como GraalVM e HotSpot. Quarkus otimiza o Java especificamente para a Kubernetes, reduzindo o tamanho do aplicativo Java e do tamanho da imagem do container, otimizando a utilização da memória por parte da solução . A escolha do mesmo foi devido a nova proposta criada por Quarkus e as infinitas possibilidades a partir dele para a nova geração da linguagem Java. 

- **Angular 6**
Framework Javascript de alta performance que utiliza TypeScript, o [Angular](https://angular.io/) é muito conhecido pela comunidade e de fácil implementação, por isso da sua escolha.

-**PostgreSQL**
Um dos DataBase ainda mais utilizados no mundo, [PostgreSQL](https://www.postgresql.org/) é robusto e performático e possui um boa integração com docker, por isso foi escolhido com nosso DataBase.

# Executando a aplicação
### Pré-requisitos
- [Docker](https://get.docker.com/) (caso vá testar a implantação via docker)
- [Docker Compose](https://docs.docker.com/compose/install/) (caso vá testar a implantação via docker)
- [Maven](https://maven.apache.org/install.html)


### Subindo a stack com docker

Após baixar o projeto em uma pasta no computador e de ter instalado os pré-requisitos, abra um terminal e vá até a pasta raiz do projeto. No terminal execute o seguinte comando:
		`sudo ./start.sh`
O script irá fazer:
- Build de uma imagem nativa da API construída com Quarkus.
- Build da aplicação front-end construída com Angular
- Start container PostgreSQL com o banco que será utilizado. 
- Start container com a imagem de código nativo da API
- Start container com Nginx para deploy da aplicação front-end

Pronto! Para acessar a aplicação acesse o endereço http://localhost:4200.

### Iniciando a aplicação sem utilização do docker
Para iniciar a aplicação separadamente, sem utilização de containers docker, você deve:

- Ter um banco de dados local PostgreSQL ou alterar as configurações da conexão no arquivo back-end/src/main/resources/application.properties

- Entrar na pasta back-end/ e executar o seguinte comando:
   `mvnw compile quarkus:dev`

- Em outro terminal entre na pasta front-end/ execute o comando:
	`npm install`
 e 
	`npm start`

- Pronto agora é só acessar o endereço http://localhost:4200


### Observações

A estrutura foi criada no intuito de gerar uma imagem nativa com Quarkus e executa-la em um container. Para a geração da imagem, necessitamos de um container com a [GraalVM](https://www.graalvm.org/) e o OpenJDK Java, e para a compilação o container precisa de minutos a mais para o processo  e também boa porcentagem de memória RAM, tudo isso para gerar uma aplicação leve de tamanho relativamente pequeno e que irá consumir os recursos de uma forma muito otimizada. Por isso sempre tenha em mente que o processo para geração da imagem, por mais que pareça moroso, o beneficio após esse processo é grandioso. 