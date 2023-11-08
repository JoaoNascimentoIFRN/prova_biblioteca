create table usuario (
    id int primary key auto_increment,
    nome varchar(250) not null,
    data_registro datetime
);

create table emprestimo (
    id int primary key auto_increment,
    data_emprestimo datetime,
    data_devolucao datetime,
    livro_id int not null,
    usuario_id int not null,
    foreign key (livro_id) references livro(id),
    foreign key (usuario_id) references usuario(id)
);