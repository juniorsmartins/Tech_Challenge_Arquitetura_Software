# Tech_Challenge

```

Nome: Tech-Challenge-DevNet;

FASE 1
Data de Início: 05/2023;
Previsão de Término: 06/2023;

FASE 2
Data de Início: 07/2023;
Previsão de Término: 08/2023;

FASE 3
FASE 4
FASE 5

```
Descrição: aplicação desenvolvida para compor nota no processo avaliativo da pós-graduação em Arquitetura de Software na Fiap/Alura. E visa construir simulacro de app para uma Lanchonete, nomeada DevNet. 


## Índice

```

1. Documentação de Arquitetura do Projeto
    1. Linguagem Ubíqua;
    2. Linguagem Pictográfica (Domain Storytelling);
    3. Domínio (Subdomínio Principal, Subdomínio Genérico e Subdomínio de Suporte);
    4. Event Storming;
    5. Bounded Context e Context Maps;

2. Documentação de Desenvolvimento do Projeto;
    1. Tecnologias;
    2. Funcionalidades;
    3. Ferramentas;
    4. Diagramas;

3. Documentação de Utilização do Projeto;
    1. Documentação Swagger/OpenAPI;

```

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

> Gestão de Clientes

<img width=600 src=Documentacao/EventStorming-CadastroDeClientes.png>

> Gestão de Cardápio (produtos)

<img width=600 src=Documentacao/EventStorming-CadastroDeProdutos.png>

> Gestão de Pedidos

<img width=600 src=Documentacao/EventStorming-CadastroDePedidosIII.png>


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

### Funcionalidades: 

> - [x] Cadastrar Cliente;
> - [x] Atualizar Cliente;
> - [x] Pesquisar Cliente;
> - [x] Deletar Cliente;
> - [x] Cadastrar Produto;
> - [x] Atualizar Produto;
> - [x] Pesquisar Produto;
> - [x] Deletar Produto;
> - [x] Cadastrar Pedido;
> - [x] Atualizar Pedido;
> - [x] Pesquisar Pedido;
> - [x] Deletar Pedido;
> - [x] Cadastrar Pagamento;
> - [x] Atualizar Pagamento;
> - [x] Pesquisar Pagamento;
> - [x] Atualizar FotoProduto;
> - [x] Pesquisar FotoProduto;
> - [x] Deletar FotoProduto;
> - [x] Pesquisar Admin;
> - [x] Enviar Email;

#### Ferramentas:

1. Intellij Idea (programação);
2. Maven (gerenciamento de dependências);
3. Git e GitHub (versionamento);
4. Postman (testes manuais);
5. StarUML (Diagramas);
6. Egon.io (Linguagem Pictográfica);
7. Miro (Event Storming);
8. ChatGPT (pesquisa);
9. YouTube (pesquisa);
10. Google (pesquisa);

#### Diagramas

##### Diagrama Entidade Relacionamento - DER
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/BancoDeDados-27-06-23.png>


### Documentação de Utilização do Projeto;

#### Documentação

[Clique aqui para ver a documentação via Swagger/OpenAPI](http://localhost:8080/swagger-ui/index.html#/)

Obs: rode a aplicação antes de acessar a documentação.
