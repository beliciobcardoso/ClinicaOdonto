
# Clinica Odonto

Projeto do trabalho integrador de backend 1 da Digital House
<h1 align="center">Integrantes: Ana Carolina, Patrícia, Belicio, Rosana, Vitor Mateus e Lucas Bernardo</h1>
- Nele, estão implementados os métodos CRUD, interligados aos serviços de FrontEnd.</br>
- Permite a administração de dados odontológicos, onde é possível adicionar e modificar os
dados dos dentistas como: registro de nome, sobrenome, matrícula, endereço.</br>
- Administração de pacientes: é possivel buscar, registrar, mofificar e excluir pacientes novos, bem como os seus dados armazenados: RG, CPF, Endereço, data de alta(procedimento cirurgico, registro e mercação de novas consultas). </br>   

[comment]: # (temos mesmo data de alta?)
- Possibilidade de login do usuário, sendo ele paciente ou dentista. </br>

[comment]: # (- Nele é possível o paciente fazer o Login em seu sistema de acesso (entrada, saída)
- Adminitração de sistema de sistema de consulta: é possível que um paciente marque uma consulta com um dentista em um horário e data específicados, desde que estejam na agenda do dentista.</br>

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMP0wZTwhLady0zN98r0q-ssaG8n8C_tIoqmyUVE7w&s"  alt="Microsoft-Integration-Azure-Stencils-Pack" style="max-width: 100%; height: 100%">


# Requisitos Técnicos


- Camada de entidade de negócios: são as classes Java do nosso negócio modeladas por meio do paradigma orientado a objetos;
- Camada de acesso a dados(Repositório): são as classes que se encarregam de acessar o banco de dados;
- Camada de dados(Banco de dados): é o banco de dados do nosso sistema modelado por meio de um modelo entidade-relacionamento. Usamos a base H2 por sua praticidade;
- Camada de negócio: são as classes de serviço que se encarregam de desaclopar o acesso aos dados da visão;
- Camada de apresentação: estas são as telas da Web que teremos que  desenvolver usando o Framework Spring Boot MVC com os controladores e uma dessas duas
opções:HTML +JavaScript ou React para a visualização;

# Avanços
- O trabalho contou com a metodologia ágil Kanban, para organização das Sprints da equipe, e seus respectivos trabalhos, e organização das tarefas  em ToDo, Doing e Done de modo que que a equipe pôde trabalhar de forma produtiva, condizente, bem produtiva e organizada.


# Sprint 1

Foi construído o diagrama UML das classes que foram necessárias para o projeto
de integração, bem como tudo relacionado às tabelas do banco de dados
relacional, que foi usados para persistir os dados e as telas HTML, com
seus estilos. Criação das entidades de modelo. Criação dos testes unitários das classes de serviço do projeto.


# Sprint 2

Aplicação do uso de Maven para gerenciamento de dependências. 
Construção das classes DAO(camada de acesso a dados com JDBC)e das classes de serviço
(camada de negócios) para cada uma das entidades do projeto, por meio desta foi compreendido
o fundamento de tudo o que foi constrído com testes Unitários;

# Sprint 3

Refatoração da camada persistência para poder acessar e recuperar dados por meio de uma ORM. 
Criados os mapeamentos e dado início à substituição das classes DAO pelas Repository, que cumprem a mesma função de maneira mais otimizada e menos verbosa.
Construção da API, por meio do desenvolvimento das classes controladoras.
Integração com a camada de apresentação, implementadas em HTML, por meio do JavaScript;

# Sprint 4

Adição de um login do Spring Security no projeto, bem como a conclusão da substituição
dos DAOs pelos repositorys para acesso e persistência no banco de dados.
Conclusão dos testes de usário para salvar, alterar e login no cadastro.
