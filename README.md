
# ClinicaOdonto

Projeto do trabalho integrador de backend 1 da Digital House
<h1 align="center">Integrantes: Ana Carolina, Patrícia, Belicio, Rosana, Teteu e Lucas Bernardo</h1>
- Nele foi possível criar um método CRUD que interligasse os serviços de FrontEnd que fizesse a administração de dados odontológicos, onde foi possível adicionar e modificar os
dados dos dentistas, registro de nome, sobrenome, matrícula, endereço e registro cadastrais como um todo.
- Administração de pacientes: Nele é possivel registrar, mofificar, excluir e inclui pacientes novos, bem como os seus dados armazenados:(RG,CPF,Endereço, data de alta(procedimento
cirurgico, registro e mercação de novas consultas)).
- Nele é possível o paciente fazer o Login em seu sistema de acesso (entrada, saída);
- Adminitração de sistema de sistema de consulta (É possível que o paciente seja atribuido a uma consulta, com um dentista em um horário definido e marcado previamente 
sob data e hora marcada(dateTime));

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMP0wZTwhLady0zN98r0q-ssaG8n8C_tIoqmyUVE7w&s"  alt="Microsoft-Integration-Azure-Stencils-Pack" style="max-width: 100%; height: 100%">


# Requisitos Técnicos


- Camada de entidade de negócios: São as classes Java do nosso negócio modeladas por meio do paradigma orientado a objetos;
- Camada de acesso a dados(Repositório): São as classes que se encarregam de acessar o banco de dados;
- Camada de dados(Banco de dados): É o banco de dados do nosso sistema modelado por meio de um modelo entidade-relacionamento. Usaremos a base H2 por sua praticidade;
- Camada de negócio: São as classes de serviço que se encarregam de desaclopar o acesso aos dados da visão;
- Camada de apresentação: Estas são as telas da Web que teremos que  desenvolver usando o Framework Spring Boot MVC com os controladores e uma dessas duas
opções:HTML +JavaScript ou React para a visualização;

# Avanços
- O trabalho contou com uma excelente metodologia Ágil por meio do Kanbam para organização das Sprints da equipe  e seu respectivo trabalho e organização em Sprints e tarefas  em Todo/ doing e Done em que a equipe pode trabalhar de forma produtiva, conduzente e bem produtiva organizada


# Sprint 1

Foi construído a UML  das classes que foi necessário para o projeto
de integração, bem como tudo relacionado a tabela de banco de dados
relacional que foi usados para persistir os dados e as telas HTML com
seus estilos para inseri-los. Realização de testes unitários das classes
Java no projeto.


# Sprint 2


Trabalhado com o Maven para referenciação das biliotecas e por meio dessas
foi contruido as classes DAO(camada de acesso a dados com JDBC)e as classes de serviço
(camada de negócios) para cada uma das entidades do projeto, por meio desta foi compreendido
o fundamento de tudo o que foi constrído com testes Unitários;

# Sprint 3

Por meio desta foi refatorado a camada de acesso ao banco de dados
para poder acessar e recuperar dados por meio de uma ORM. Criando os mapeamentos e as classes
de Repositório que foram substituidos pelos DAOs que cumprem a mesma função.

Foi contruido APIs durante esse Sprint(por meio do desenvolvimento dos controladores)
e a  integração com a camada de apresentação, ou seja, as telas HTML por meio do JavaScript;


# Sprint 4

Foi adicionado um login do Spring Securuty no projeto bem como a substuição
dos DAOs pelos repositorys
- Foi Concluido os testes de usuario para salvar, alterar e login no cadastro




