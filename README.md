# Tech_Challenge

Nome: Tech-Challenge-DevNet;  
  
Descrição: aplicação desenvolvida para compor nota no processo avaliativo da primeira fase da pós-graduação em Arquitetura de Software na Fiap/Alura. E visa construir simulacro de app para uma Lanchonete, nomeada DevNet.

  
## Índice
  
1. Documentação de Arquitetura do Projeto
    1. Linguagem Ubíqua;
    2. Linguagem Pictográfica (Domain Storytelling);
    3. Domínio (Subdomínio Principal, Subdomínio Genérico e Subdomínio de Suporte);
    4. Event Storming;
    5. Bounded Context e Context Maps.
  
2. Documentação de Desenvolvimento do Projeto;
    1. Tecnologias;
    2. Ferramentas;
    3. Diagramas.
  
3. Documentação de Utilização do Projeto;
    1. Documentação Swagger/OpenAPI;
    2. Arquivo de requisições do Postman;
    3. Verbos, EndPoints e Jsons;
    4. Como testar.
  
4. Autoria.

  
### Documentação de Arquitetura do Projeto


#### Linguagem Ubíqua:

A Linguagem Ubíqua (Ubiquitous Language) é um conceito fundamental que envolve o uso de uma linguagem comum e compartilhada entre todos os membros de uma equipe, incluindo especialistas de domínio, desenvolvedores, analistas e demais envolvidos no desenvolvimento de software.

A Linguagem Ubíqua é essencial para garantir uma comunicação efetiva e precisa entre todas as partes envolvidas no projeto, eliminando ambiguidades e mal-entendidos. Ela busca estabelecer um vocabulário comum que reflita as nuances e os conceitos do domínio em questão.

Ao adotar uma Linguagem Ubíqua, os termos técnicos e de negócio são definidos de forma colaborativa, levando em consideração a compreensão dos especialistas de domínio. Essa linguagem compartilhada é utilizada tanto na documentação do projeto quanto nas conversas diárias entre os membros da equipe.

A Linguagem Ubíqua permeia todos os artefatos do projeto, incluindo o código-fonte, os testes, a documentação e até mesmo as discussões em reuniões. Ela ajuda a alinhar a visão do domínio entre todas as partes interessadas e permite que o modelo de domínio seja expresso de forma clara e concisa em todo o processo de desenvolvimento.

[Clique aqui para ver o dicionário](https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/DICIONARIO.md)


#### Linguagem Pictográfica:

A Linguagem Pictográfica do Domain Storytelling é uma abordagem visual utilizada no contexto do Domain-Driven Design (DDD) para auxiliar na compreensão e comunicação de conceitos complexos do domínio de um software.

O Domain Storytelling, ou "narrativa do domínio", é uma técnica colaborativa que visa capturar e compartilhar o conhecimento sobre o domínio do problema de forma mais efetiva. Nessa abordagem, os membros da equipe de desenvolvimento, juntamente com especialistas do domínio, constroem uma narrativa em torno de como o software será usado, explorando diferentes cenários e interações.

Na linguagem pictográfica, a narrativa pode ser contada pelo prisma de dois escopos: Atual e Desejado. O conceito de "Escopo Atual" e "Escopo Desejado" é utilizado para representar a situação atual e a situação desejada, respectivamente, em relação a um determinado domínio ou contexto.

O "Escopo Atual" refere-se ao estado ou condição atual do sistema, processo ou situação que está sendo analisado. Ele representa a forma como as coisas são atualmente, incluindo os elementos existentes, regras de negócio vigentes e fluxos de trabalho em vigor. O Escopo Atual captura a realidade atual, descrevendo os problemas, desafios e oportunidades existentes no domínio.

Por outro lado, o "Escopo Desejado" representa a visão ou objetivo futuro do sistema, processo ou situação. Ele descreve como se espera que as coisas sejam ou funcionem no futuro, após as melhorias ou mudanças serem implementadas. O Escopo Desejado define as melhorias, os requisitos e os objetivos a serem alcançados para atender às necessidades e expectativas do negócio.

Na linguagem pictográfica, a narrativa também pode ser construída em dois domínios: Puro e Digitalizado. Os conceitos de "Domínio Puro" e "Domínio Digitalizado" referem-se a duas abordagens distintas na representação de informações e conceitos relacionados a um determinado domínio.

O "Domínio Puro" se refere à representação das informações, conceitos e regras de negócio em sua forma original, não digitalizada. Ele retrata o domínio tal como é compreendido pelos especialistas do domínio, usando uma linguagem e notações que são familiares e significativas para eles. Essa representação pode envolver descrições verbais, diagramas esboçados à mão ou ilustrações de conceitos-chave do domínio.

Por outro lado, o "Domínio Digitalizado" é a representação das informações e conceitos do domínio em um formato digital, como diagramas de software, modelos de banco de dados, especificações técnicas ou código de programação. Nessa abordagem, as informações são traduzidas para uma forma mais estruturada e orientada a sistemas, utilizando linguagens e notações técnicas específicas.

Portanto, nesse contexto, abaixo segue a narrativa do negócio. Ela foi escrita em Linguagem Pictográfica, no "Escopo Atual" e no "Domínio Puro". Trata-se da forma presente e analógica de como funciona a lanchonete objeto do software. 

<img width=800 src=Documentacao/DevNet-EscopoAtualAndDominioPuro_2023-05-28.png>


#### Domínio

No Domain-Driven Design (DDD), o termo "Domínio" refere-se ao conhecimento especializado de um determinado problema de negócio e às regras que governam esse problema. É a área específica de conhecimento em que um software está sendo desenvolvido.

Os subdomínios são partes delimitadas e autônomas do domínio geral do sistema. Eles representam áreas ou aspectos distintos dentro do domínio que possuem seus próprios conceitos, regras e comportamentos. Os subdomínios são identificados durante a análise e a modelagem do domínio e ajudam a organizar o sistema de forma mais clara e modular.

Existem três tipos comuns de subdomínios:

Subdomínio Principal (Core Subdomain): O subdomínio principal contém os principais conceitos, regras e processos centrais do negócio. É a parte central e crítica do domínio, que possui alto valor estratégico para a organização. O subdomínio principal geralmente concentra a maior parte do esforço de modelagem e implementação, pois é onde as funcionalidades principais e únicas do sistema estão localizadas.

Subdomínio Genérico (Generic Subdomain): O subdomínio genérico é composto por conceitos e regras mais comuns e amplamente aplicáveis, que podem ser encontrados em vários sistemas ou setores de negócio. Esses conceitos são considerados mais gerais e abrangentes, não possuindo diferenciais competitivos significativos para a organização. O subdomínio genérico normalmente é menos complexo e requer menos esforço de modelagem, pois pode ser atendido por soluções prontas ou padrões estabelecidos.

Subdomínio de Suporte (Supporting Subdomain): O subdomínio de suporte engloba funcionalidades e serviços de apoio que são necessários para o funcionamento do sistema, mas não são o cerne do negócio. Isso inclui recursos como autenticação, autorização, logging, geração de relatórios, entre outros. O subdomínio de suporte é responsável por fornecer funcionalidades transversais e infraestruturais, garantindo a segurança, o desempenho e a manutenção adequada do sistema.

<img width=600 src=Documentacao/DevNet-DiagramaDeDomínios.png>


#### Event Storming:

No contexto do Domain-Driven Design (DDD), Event Storming é uma técnica de modelagem colaborativa e visual que visa explorar e entender o domínio de um sistema por meio da identificação de eventos de domínio significativos. É uma ferramenta poderosa para capturar conhecimento compartilhado e colaborar com as partes interessadas, incluindo especialistas de domínio, desenvolvedores e outros membros da equipe.

O Event Storming é realizado em uma sessão de workshop envolvendo todas as partes interessadas relevantes. Durante a sessão, os participantes mapeiam o fluxo de eventos do sistema em um quadro branco ou em uma parede usando notas adesivas coloridas. Essas notas adesivas representam eventos de domínio, com cada evento capturando uma mudança de estado significativa dentro do domínio. Os eventos são organizados sequencialmente para criar um fluxo de eventos que reflete as interações e as regras de negócio do sistema.

Além dos eventos, o Event Storming também pode incluir outras informações relevantes, como comandos, agregados, políticas, restrições e outras anotações que ajudam a capturar o conhecimento do domínio e a compreensão do sistema.

[Link para Miro](https://miro.com/app/board/uXjVMG0U7lM=/)

> Cliente

<img width=600 src=Documentacao/EventStorming-CadastroDeClientes-30-06-23.png>

> Produto

<img width=600 src=Documentacao/EventStorming-CadastroDeProdutos-30-06-23.png>

> Pedido

<img width=600 src=Documentacao/EventStorming-CadastroDePedidos-30-06-23.png>

> Administrativo

<img width=600 src=Documentacao/EventStorming-AcessoAdministrativo-30-06-23.png>


### Documentação de Desenvolvimento do Projeto

#### Tecnologias:

1. Java (17 LTS);
2. Spring Boot (v3.1.0);
3. Spring Mail;
4. Spring Data JPA;
5. Spring Doc;
6. Bean Validation;
7. Hibernate Envers;
8. Model Mapper (v3.1.1);
9. Commons Lang3;
10. Lombok;
11. DevTools;
12. Zxing (2.0.0);
13. PostgreSQL;
14. JavaFaker (1.0.2);

#### Ferramentas:

1. Intellij Idea (programação);
2. Maven (gerenciamento de dependências);
3. Git (versionamento);
4. GitHub (repositório);
5. Postman (testes manuais);
6. StarUML (Diagramas);
7. Egon.io (Linguagem Pictográfica);
8. Miro (Event Storming);
9. ChatGPT (pesquisa);
10. YouTube (pesquisa);
11. Google (pesquisa).

#### Diagramas

##### Diagrama Entidade Relacionamento - DER
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/BancoDeDados-29-06-23.png>


### Documentação de Utilização do Projeto;

#### Documentação

[Clique para ver a documentação via Swagger/OpenAPI](http://localhost:8080/swagger-ui/index.html#/)

Obs: rode o container ou a aplicação anteriormente para ver a documentação Swagger. 

#### Arquivo de requisições do Postman

Disponibilizado o Script do Postman para os testes manuais. Esse Script pode ser baixado e importado no Posman. Nele estão definidos os endpoints e respectivos Jsons. Clique logo abaixo para ir até a pasta onde está o Script, o arquivo foi nomeado: TechChallenge.postman_collection.json. Porém, é necessário criar uma variável de ambiente chamada {{baseUrl}} com o endpoint: http://localhost:8080 ou, uma alternativa, é substituir a variável de ambiente pelo http://localhost:8080.

[Clique Aqui! Para ver a pasta do Script.](https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/tree/master/Documentacao)

#### Verbos, EndPoints e Jsons

```
CLIENTES

GET
http://localhost:8080/api/v1/clientes
É possível pesquisar todos ou por ID, Nome, CPF e Email. Esse endpoint foi construído com o uso de filtros de Specification e a resposta é paginada.

POST
http://localhost:8080/api/v1/clientes
Importante cadastrar o Cliente com email real para receber as notificações de acompanhamento do Pedido. Toda alteração de status de Pedido gera notificação no email.
{
    "nome":"Robert Martin",
    "cpf":"12883391009",
    "email":"coloque_seu_email_aqui@gmail.com"
}

PUT
http://localhost:8080/api/v1/clientes/1
{
    "nome":"Robert Atualizado Martin",
    "cpf":"78616571086",
    "email":"coloque_seu_email_aqui@gmail.com"
}

DELETE
http://localhost:8080/api/v1/clientes/1
```

```
PRODUTOS

GET
http://localhost:8080/api/v1/produtos
É possível pesquisar todos ou por ID, Categoria, Nome, Descrição e Preço. Esse endpoint foi construído com o uso de filtros de Specification e a resposta é paginada.

POST
http://localhost:8080/api/v1/produtos
{
    "categoria": "BEBIDA",
    "nome":"Coca-Cola 3L",
    "descricao":"Refrigerante",
    "preco": 15.00
}

POST
http://localhost:8080/api/v1/produtos
{
    "categoria": "LANCHE",
    "nome":"Hamburguer Filé de Peixe",
    "descricao":"Pão de hamburguer com gergelin e filé de peixe, maionese, queijo, tomate e alface",
    "preco": 30.00
}

POST
http://localhost:8080/api/v1/produtos
{
    "categoria": "SOBREMESA",
    "nome":"Pudim",
    "descricao":"Leite integral, ovos de galinha caipira, baunilha, açúcas e água.",
    "preco": 20.00
}

PUT
http://localhost:8080/api/v1/produtos/3
{
    "categoria": "SOBREMESA",
    "nome":"Pudim ao Coco",
    "descricao":"Leite integral, ovos de galinha caipira, baunilha, açúcar, coco e água.",
    "preco": 22.50
}

DELETE
http://localhost:8080/api/v1/produtos/3
```

```
PEDIDOS

GET
http://localhost:8080/api/v1/pedidos
É possível pesquisar todos ou por ID, StatusPedidos, Cliente e FormaPagamento. Esse endpoint foi construído com o uso de filtros de Specification e a resposta é paginada.

POST
http://localhost:8080/api/v1/pedidos
Json de Pedido com Cliente logado/identificado por ID. É possível fazer pedido de três formas. Com Cliente logado na aplicação enviando ID no Json, mas também de outras duas formas. Sem o Cliente estar logado, mas informando o CPF. E também sem informar o Cliente e, dessa forma, o Pedido é acompanhado apenas pelo seu código/chave primária.
{
  "cliente": {
      "id": 1
  },
  "itensPedido": [
      {
          "produto": {
              "id": 1
          },
          "quantidade": 2
      }
  ],
  "formaPagamento": "PIX"
}

POST
http://localhost:8080/api/v1/pedidos
Json de Pedido com Cliente não logado, mas identificado por CPF.
{
  "cliente": {
      "cpf":"55985127001"
  },
  "itensPedido": [
      {
          "produto": {
              "id": 2
          },
          "quantidade": 3
      }
  ],
  "formaPagamento": "CREDITO"
}

POST
http://localhost:8080/api/v1/pedidos
Json de Pedido sem Cliente. É possível fazer Pedido sem identificação do Cliente. Porém, não receberá notificações via e-mail informando o status do Pedido.
{
  "itensPedido": [
      {
          "produto": {
              "id": 3
          },
          "quantidade": 2
      },
      {
          "produto": {
              "id": 2
          },
          "quantidade": 1
      },
            {
          "produto": {
              "id": 1
          },
          "quantidade": 1
      }
  ],
  "formaPagamento": "DEBITO"
}

PUT
http://localhost:8080/api/v1/pedidos/3
Ao atualizar o Pedido 3, por exemplo, que é um Pedido sem cliente identificado, é possível também, além de alterar os Itens, adicionar o Cliente.
{
  "itensPedido": [
      {
          "produto": {
              "id": 1
          },
          "quantidade": 1
      }
  ],
  "formaPagamento": "DINHEIRO"
}

DELETE
http://localhost:8080/api/v1/pedidos/3
Essa funcionalidade não apaga o pedido. Ela apenas cancela o Pedido e o Pagamento aberto. Somente Pedido com Status Aberto podem ser cancelados.
```

```
FOTOS

PUT
http://localhost:8080/api/v1/fotos/1
A imagem abaixo mostra como enviar o arquivo da Foto e sua respectiva descrição (limitado a PNG e JPEG). 
```
<img width=600 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/Postman-coca-cola.png>

```
GET
http://localhost:8080/api/v1/fotos/1 
A imagem abaixo mostra como consultar o registro da Foto no banco de dados. Não é consultar a Foto/imagem, mas apenas seus dados registrados.
```
<img width=600 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/Postman-consultarPorId.png>

```
GET
http://localhost:8080/api/v1/fotos/1
A imagem abaixo mostra como consultar a foto/imagem num diretório no Volume do container. Aqui se consulta a imagem mesmo (limitado a PNG e JPEG).
```
<img width=600 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/Postman-consultarFotoPorId.png>

```
DELETE
http://localhost:8080/api/v1/fotos/2 
Esse delete apaga o registro da imagem no banco de dados e apaga a imagem armazenada num diretório no Volume do container. 
```

```
PAGAMENTOS

GET
http://localhost:8080/api/v1/pagamentos
É possível pesquisar todos ou por ID, StatusPagamento e nomeImagemQRCode. Esse endpoint foi construído com o uso de filtros de Specification e a resposta é paginada.

GET
http://localhost:8080/api/v1/pagamentos/1?Accept=image/png
Endpoint para buscar o QRCode por ID. Os QRCodes são armazenados em PNG num diretório no Volume do container. 

PUT
http://localhost:8080/api/v1/pagamentos/status/1
Esse endpoint consulta o Gateway de Pagamento para verificar se o Pedido foi pago. E, em caso de pago, é confirmado o Pagamento e o Pedido avança de RECEBIDO para PREPARACAO.
```

```
COPA

PUT
http://localhost:8080/api/v1/copa/status-pronto/1
Esse endpoint altera o status do Pedido para PRONTO. Toda alteração de status gera notificação via e-mail. Então é importante cadastrar o Cliente com email real para ver as notificações.

PUT
http://localhost:8080/api/v1/copa/status-finalizado/1
Esse endpoint altera o status do Pedido para FINALIZADO. Um Pedido é finalizado após a retirada dele pelo Cliente.
```

```
EMAILS

POST
http://localhost:8080/api/v1/emails
Endpoint para enviar alguma notificação aleatório ou promoção. 
{
    "ownerRef": "Fulano",
    "emailFrom": "techchallenge6@gmail.com",
    "emailTo": "<coloque-aqui-teu-email>",
    "subject": "Teste - Envio de Email",
    "text": "Teste de Envio de Email via Spring Email",
    "pedido": {
        "id": 1
    }
}
```
```
INDICADORES

GET
http://localhost:8080/api/v1/admin/indicadores
Esse endpoint gera indicadores administrativos. Neste caso, estão prontos os indicadores de Pagamentos.
```

#### Como testar

```
1. Clone o projeto;
  
2. Digite os comandos abaixo, no terminal do projeto em sua IDE, para construir o container:
   2.1. mvn clean package -DskipTests
   2.2. docker build -t devnet .
   2.3. docker compose up -d --build
  
3. Abra o Postman;
   3.1. Faça o download do Script do Postman. [Clique Aqui! Para ver a pasta do Script.](https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/tree/master/Documentacao)
   3.2. Importe esse Script para o Postman
   3.3. Faça os testes. (não precisa startar a aplicação na IDE. Só certifique-se do container estar startado.)

4. Documentação Swagger
  4.1 Você pode olhar a documentação Swagger para lhe auxiliar. Link disponível mais acima nesta documentação.
```

### Autoria

[Junior Martins](https://www.linkedin.com/in/juniorsmartins/)
