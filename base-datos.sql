CREATE DATABASE cursos;
USE cursos;

drop database cursos;
-- TABLA CATEGORIA
CREATE TABLE categoria (
    id int PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

select * from categoria;

drop table cursos;

-- TABLA CURSO
CREATE TABLE curso (
    id int PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    descripcion VARCHAR(255),
    categoria_id int NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

select * from cursos;
-- TABLA ESTUDIANTE
CREATE TABLE estudiante (
    id int PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL 
);

-- TABLA INTERMEDIA (N:N)
CREATE TABLE estudiantes_curso (
    estudiante_id int,
    cursos_id int,
    PRIMARY KEY (estudiante_id, cursos_id),
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (cursos_id) REFERENCES cursos(id)
);

select * from estudiantes_curso;