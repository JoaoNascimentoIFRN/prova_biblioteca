create table livro (
    id int primary key auto_increment,
    titulo varchar(300) not null,
    isbn varchar(100) not null,
    ano_publicacao int,
    quantidade_copias_disponiveis int
);
create table autor (
    id int primary key auto_increment,
    nome varchar(300) not null,
    nacionalidade varchar(200)    
);
create table livro_autor (
    id int primary key auto_increment,
    livro_id int not null,
    autor_id int not null,
    foreign key (livro_id) references livro(id),
    foreign key (autor_id) references autor(id) 
);