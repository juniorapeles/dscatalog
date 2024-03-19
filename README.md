# Bootcamp Spring Java: Desenvolvimento Fullstack com React

Este repositório contém os projetos desenvolvidos durante o Bootcamp Spring Java, focado em desenvolvimento web fullstack com Spring Boot no backend e React no frontend.

## Descrição do Projeto

O projeto principal desenvolvido neste Bootcamp é o DSCatalog, uma aplicação de catálogo de produtos que permite aos usuários visualizarem, pesquisarem e gerenciarem produtos. A aplicação é composta por um backend desenvolvido em Spring Boot, que utiliza o banco de dados H2 para ambiente de desenvolvimento e PostgreSQL para produção, e um frontend em React.

## Tecnologias Utilizadas

- Spring Boot
- React
- H2 Database
- PostgreSQL
- DTOs (Data Transfer Objects)
- Segurança com Spring Security
- Docker
- Testes Unitários e Automatizados com JUnit Jupiter e Mockito

## Funcionalidades Implementadas

- CRUD de produtos
- CRUD de categorias
- Autenticação de usuários
- Autorização de acesso
- Paginação de resultados
- Integração com banco de dados PostgreSQL
- Desenvolvimento de APIs RESTful
- Utilização de DTOs para comunicação entre backend e frontend

## Configuração do Ambiente

Para executar a aplicação localmente, siga as instruções abaixo:

### Backend (Spring Boot)

1. Clone este repositório.
2. Navegue até o diretório `backend`.
3. Execute o comando `./mvnw spring-boot:run` para iniciar o servidor backend.

### Frontend (React)

1. Navegue até o diretório `frontend`.
2. Execute o comando `npm install` para instalar as dependências.
3. Execute o comando `npm start` para iniciar o servidor de desenvolvimento do React.

### Banco de Dados

- O banco de dados H2 é configurado automaticamente para ambiente de desenvolvimento.
- Para configurar o banco de dados PostgreSQL, edite as configurações em `application.properties` no backend.

## Docker

A aplicação pode ser facilmente containerizada usando Docker. Utilize os arquivos `Dockerfile` fornecidos no backend e no frontend para criar as imagens Docker correspondentes.

## Considerações Finais

Este Bootcamp proporcionou uma excelente oportunidade para aprimorar habilidades em desenvolvimento web fullstack, utilizando tecnologias modernas como Spring Boot, React e Docker. A experiência adquirida ao desenvolver o DSCatalog preparou-me para enfrentar desafios no mundo real e me tornar um desenvolvedor mais competente.
