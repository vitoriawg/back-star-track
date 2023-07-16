create table usuarios(
    codigo serial primary key,
    nome varchar(255),
    email varchar(255),
    senha varchar(255)
);

insert into usuario (nome, email, senha) values ('VITOR FRAPORTI ROSMANN', 'VITORFRAPORTI@HOTMAIL.COM', '1234');

create table produtos (
    codigo serial primary key,
    nome varchar(255),
    valor decimal(10,2)
);

insert into produtos (nome, valor) values ('DESINFETANTE', 17.33);
insert into produtos (nome, valor) values ('DOWNY CONCENTRADO', 28.75);
insert into produtos (nome, valor) values ('SABÃO EM PÓ', 23.00);

create table lotes (
    codigo serial primary key,
    lote bigint,
    codigo_produto integer references produtos(codigo),
    data_validade date,
    quantidade integer
);

insert into lotes (lote, codigo_produto, data_validade, quantidade) values (52345, 1, '2021-12-31', 10);
insert into lotes (lote, codigo_produto, data_validade, quantidade) values (46734, 2, '2021-12-31', 10);