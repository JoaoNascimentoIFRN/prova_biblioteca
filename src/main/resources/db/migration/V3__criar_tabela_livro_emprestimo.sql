create table livro_emprestimo (
    id int primary key auto_increment,
    livro_id int not null,
    emprestimo_id int not null,
    foreign key (livro_id) references livro(id),
    foreign key (emprestimo_id) references emprestimo(id) 
);