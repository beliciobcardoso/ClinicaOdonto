
CREATE TABLE IF NOT EXISTS Endereco (
id int auto_increment primary key,
rua varchar(100) not null,
numero varchar(4) not null,
complemento varchar(100) not null,
bairro varchar(100) not null,
cidade varchar(100) not null,
estado varchar(100) not null
);

CREATE TABLE IF NOT EXISTS Dentista (
id int auto_increment primary key,
);

CREATE TABLE IF NOT EXISTS Consulta (
id int auto_increment primary key,
);

CREATE TABLE IF NOT EXISTS Paciente (
id int auto_increment primary key,
);

CREATE TABLE IF NOT EXISTS Usuario (
id int auto_increment primary key,
);