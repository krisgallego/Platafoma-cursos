create database cursos;
USE cursos;

drop database cursos;
-- tabla categoria
create table categoria (
    id int primary key AUTO_INCREMENT,
    nombre varchar(100) not null
);

select * from categoria;

drop table cursos;

-- tabla curso
create table curso (
    id int primary key AUTO_INCREMENT,
    titulo varchar(150) not null,
    descripcion varchar(255),
    categoria_id int not null,
    foreign key (categoria_id) references categoria(id)
);

select * from cursos;
-- tabla estudiante
create table estudiante (
    id int primary key AUTO_INCREMENT,
    nombre varchar(150) not null,
    email varchar(150) not null 
);

-- tabla intermedia
create table estudiantes_curso (
    estudiante_id int,
    cursos_id int,
    primary key (estudiante_id, cursos_id),
    foreign key (estudiante_id) references estudiante(id),
    foreign key (cursos_id) references cursos(id)
);

select * from estudiantes_curso;