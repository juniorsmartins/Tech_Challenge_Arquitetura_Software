# Tech_Challenge

Nome: Tech-Challenge-DevNet;  
  
Descrição: aplicação desenvolvida para compor nota no processo avaliativo da pós-graduação em Arquitetura de Software na Fiap/Alura. E visa construir simulacro de aplicação para uma Lanchonete, nomeada DevNet, para praticar conhecimentos relacionados a arquitetura. Portanto, esse projeto segue requisitos estabelecidos pelo processo avaliativo.

## Documentação por fase do projeto

Fase 1: [Clique aqui](https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/README-FASE1.md)  
Fase 2: logo abaixo!  
Fase 3: em construção  
Fase 4: em construção  
Fase 5: em construção  
  
## Índice
 
1. Desenvolvimento
    1. Tecnologias;
    2. Ferramentas;
    3. Diagramas.
  
2. Utilização
    1. Como testar;
    2. Arquivo de requisições do Postman;
    3. Documentação Swagger/OpenAPI;
  
4. Autoria.

### Desenvolvimento

#### Tecnologias:

1. Java (17 LTS);
2. Spring Boot (v3.1.0);
3. Spring Mail;
4. Spring Data JPA;
5. Spring Doc;
6. Spring Cloud Kubernetes;
7. Spring Actuator;
8. Bean Validation;
9. Hibernate Envers;
10. Model Mapper (v3.1.1);
11. Commons Lang3;
12. Lombok;
13. DevTools;
14. Zxing (2.0.0);
15. PostgreSQL;
16. JavaFaker (1.0.2);
17. Docker;
18. Kubernetes.

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
10. Bard.google (pesquisa);
11. YouTube (pesquisa);
12. Google (pesquisa).

#### Diagramas

##### Diagrama de Classe

CLIENTES (como era)  
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/DiagramaClasses-Cliente-09-07-23.png>

CLIENTES (como ficou - atual)  
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/DiagramaClasses-Cliente-11-07-23.png>

PRODUTOS (como era)  
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/DiagramaClasses-Produto-09-07-23.png>  

PRODUTOS (como ficou - atual)   
<img width=800 src=https://github.com/juniorsmartins/Tech_Challenge_Arquitetura_Software/blob/master/Documentacao/DiagramaClasses-Produto-12-07-23.png>  


### Utilização

#### Como testar

```
1. Clone o projeto;

2. Certifique-se de ter ambiente de desenvolvimento instalado;

3. Certifique-se de startar Docker e Minikube;

4. Abra o projeto na IDE e depois abra o terminal no mesmo diretório do arquivo Dockerfile;

5. Digite os seguintes comandos no terminal (no diretório do Dockerfile);
> kubectl apply -f ./k8s-infra/.
> kubectl apply -f ./k8s-database/.
> kubectl apply -f ./k8s-app/.
> kubectl get all
> aguarde até todos os pods estarem criados
> minikube service devnet-app --url

6. Use a url gerada para fazer as requisições via Postman.
```

#### Arquivo de requisições do Postman

#### Documentação Swagger/OpenAPI
  
  
### Autoria

[Junior Martins](https://www.linkedin.com/in/juniorsmartins/)
