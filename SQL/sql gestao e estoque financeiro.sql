-- Copiando estrutura do banco de dados para pooii
CREATE DATABASE IF NOT EXISTS `pooii`;
USE `pooii`;

-- Copiando estrutura para tabela pooii.pessoa
CREATE TABLE IF NOT EXISTS `pessoa` (
  `pessoa_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `tipo_pessoa` enum('F','J') NOT NULL,
  `cpf_cnpj` char(14) NOT NULL,
  `telefone` char(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pessoa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Copiando estrutura para tabela pooii.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) NOT NULL,
  `preco_custo` decimal(10,2) DEFAULT NULL,
  `preco_venda` decimal(10,2) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`produto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Copiando estrutura para tabela pooii.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `pessoa_id` int(11) NOT NULL,
  `data_cadastro` date NOT NULL,
  PRIMARY KEY (`cliente_id`),
  KEY `fk_pessoa_cliente1_idx` (`pessoa_id`),
  CONSTRAINT `fk_pessoa_cliente1` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`pessoa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela pooii.fornecedor
CREATE TABLE IF NOT EXISTS `fornecedor` (
  `fornecedor_id` int(11) NOT NULL AUTO_INCREMENT,
  `pessoa_id` int(11) NOT NULL,
  `data_cadastro` date NOT NULL,
  PRIMARY KEY (`fornecedor_id`),
  KEY `fk_pessoa_fornecedor1_idx` (`pessoa_id`),
  CONSTRAINT `fk_pessoa_fornecedor1` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`pessoa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.

-- Copiando estrutura para tabela pooii.movimentacao
CREATE TABLE IF NOT EXISTS `movimentacao` (
  `movimentacao_id` int(11) NOT NULL AUTO_INCREMENT,
  `produto_id` int(11) NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `tipo` enum('E','S') NOT NULL,
  `data_movimentacao` date NOT NULL,
  `status` enum('A','Q') NOT NULL DEFAULT 'A',
  `quantidade_movimentacao` int(11) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`movimentacao_id`),
  KEY `fk_produto_movimentacao_idx` (`produto_id`),
  KEY `fk_pessoa_movimentacao1_idx` (`pessoa_id`),
  CONSTRAINT `fk_pessoa_movimentacao1` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`pessoa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_movimentacao` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`produto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Copiando estrutura para procedure pooii.p_delete_cliente
DELIMITER //
CREATE PROCEDURE `p_delete_cliente`(
    in p_pessoa_id int
)
proc: begin

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    # controle de transação
    start transaction;

    # remove registro
    delete from cliente where pessoa_id = p_pessoa_id;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_pessoa_id as id, 'erro' as type, 'Não foi possível remover cliente.' as msg;
    else
        commit;
        select p_pessoa_id as id, 'sucesso' as type, 'Cliente removido.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_delete_fornecedor
DELIMITER //
CREATE PROCEDURE `p_delete_fornecedor`(
    in p_pessoa_id int
)
proc: begin

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    # controle de transação
    start transaction;

    # remove registro
    delete from fornecedor where pessoa_id = p_pessoa_id;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_pessoa_id as id, 'erro' as type, 'Não foi possível remover fornecedor.' as msg;
    else
        commit;
        select p_pessoa_id as id, 'sucesso' as type, 'Fornecedor removido.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_delete_movimentacao
DELIMITER //
CREATE PROCEDURE `p_delete_movimentacao`(
    in p_movimentacao_id int
)
proc: begin

    declare xProdutoId int default 0;
    declare xQuantidade int default 0;
    declare xTipoMovimentacao char(1) default '';
    declare xStatus char default '';

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    select produto_id, tipo, quantidade_movimentacao, status from movimentacao where movimentacao_id = p_movimentacao_id into xProdutoId, xTipoMovimentacao, xQuantidade, xStatus;

    if xStatus = 'Q' then
        select p_movimentacao_id as id, 'erro' as type, 'Não é permitido remover movimentações quitadas.' as msg;
        leave proc;
    end if;

    # controle de transação
    start transaction;

    if xTipoMovimentacao = 'S' then

        set sql_safe_updates = 0;
        update produto set quantidade = quantidade + xQuantidade where produto_id = xProdutoId;

    elseif p_tipo = 'E' then

        set sql_safe_updates = 0;
        update produto set quantidade = quantidade - xQuantidade where produto_id = xProdutoId;

    end if;

    # remove registro
    delete from movimentacao where movimentacao_id = p_movimentacao_id;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_movimentacao_id as id, 'erro' as type, 'Não foi possível remover a movimentação.' as msg;
    else
        commit;
        select p_movimentacao_id as id, 'sucesso' as type, 'Movimentação removido.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_delete_produto
DELIMITER //
CREATE PROCEDURE `p_delete_produto`(
    in p_produto_id int
)
proc: begin

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    # controle de transação
    start transaction;

    # remove registro de produto
    delete from produto where produto_id = p_produto_id;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_produto_id as id, 'erro' as type, 'Não foi possível remover o produto.' as msg;
    else
        commit;
        select p_produto_id as id, 'sucesso' as type, 'Produto removido.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_salve_cliente
DELIMITER //
CREATE PROCEDURE `p_salve_cliente`(
    in p_pessoa_id int,
    in p_nome varchar(100),
    in p_tipo_pessoa enum('F', 'J'),
    in p_cpf_cnpj char(14),
    in p_telefone char(11),
    in p_email varchar(100)
)
proc: begin

    # valida pelo documento a duplicação
    declare xPessoaId int default 0;

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    if p_nome = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo nome é obrigatório' as msg;
        leave proc;
    end if;

    if p_tipo_pessoa = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo tipo de pessoa é obrigatório' as msg;
        leave proc;
    end if;

    if p_cpf_cnpj = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo documento é obrigatório' as msg;
        leave proc;
    end if;

    if p_telefone = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo telefone é obrigatório' as msg;
        leave proc;
    end if;

    if p_email = '' then
        set p_email = null;
    end if;

    select pessoa_id from pessoa where cpf_cnpj = p_cpf_cnpj into xPessoaId;

    if xPessoaId <> 0 and p_pessoa_id = -1 then
        select p_pessoa_id as id, 'erro' as type, 'Cliente já cadastrado no sistema' as msg;
        leave proc;
    end if;

    # controle de transação
    start transaction;

    # se o valor passado para chave primária for igual a -1
    if p_pessoa_id = -1 then

        select pessoa_id from pessoa where cpf_cnpj = p_cpf_cnpj into p_pessoa_id;

        if p_pessoa_id = -1 then
            # insere registro
            insert into pessoa (nome, tipo_pessoa, cpf_cnpj, telefone, email)
            values (p_nome, p_tipo_pessoa, p_cpf_cnpj, p_telefone, p_email);
        end if;

        # recupera o id gerado na transação
        select last_insert_id() into p_pessoa_id;

        # insere cliente
        insert into cliente (pessoa_id, data_cadastro) values (p_pessoa_id, curdate());

    else

        # atualiza o registro
        update pessoa set nome = p_nome, tipo_pessoa = p_tipo_pessoa, cpf_cnpj = p_cpf_cnpj, telefone = p_telefone, email = p_email
        where pessoa_id = p_pessoa_id;

    end if;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_pessoa_id as id, 'erro' as type, 'Não foi possível registrar cliente.' as msg;
    else
        commit;
        select p_pessoa_id as id, 'sucesso' as type, 'Cliente registrado.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_salve_fornecedor
DELIMITER //
CREATE PROCEDURE `p_salve_fornecedor`(
    in p_pessoa_id int,
    in p_nome varchar(100),
    in p_tipo_pessoa enum('F', 'J'),
    in p_cpf_cnpj char(14),
    in p_telefone char(11),
    in p_email varchar(100)
)
proc: begin

    # valida pelo documento a duplicação
    declare xPessoaId int default 0;

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    if p_nome = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo nome é obrigatório' as msg;
        leave proc;
    end if;

    if p_tipo_pessoa = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo tipo de pessoa é obrigatório' as msg;
        leave proc;
    end if;

    if p_cpf_cnpj = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo documento é obrigatório' as msg;
        leave proc;
    end if;

    if p_telefone = '' then
        select p_pessoa_id as id, 'erro' as type, 'O campo telefone é obrigatório' as msg;
        leave proc;
    end if;

    if p_email = '' then
        set p_email = null;
    end if;

    select pessoa_id from pessoa where cpf_cnpj = p_cpf_cnpj into xPessoaId;

    if xPessoaId <> 0 and p_pessoa_id = -1 then
        select p_pessoa_id as id, 'erro' as type, 'Fornecedor já cadastrado no sistema' as msg;
        leave proc;
    end if;

    # controle de transação
    start transaction;

    # se o valor passado para chave primária for igual a -1
    if p_pessoa_id = -1 then

        select pessoa_id from pessoa where cpf_cnpj = p_cpf_cnpj into p_pessoa_id;

        if p_pessoa_id = -1 then
            # insere registro
            insert into pessoa (nome, tipo_pessoa, cpf_cnpj, telefone, email)
            values (p_nome, p_tipo_pessoa, p_cpf_cnpj, p_telefone, p_email);
        end if;

        # recupera o id gerado na transação
        select last_insert_id() into p_pessoa_id;

        # insere fornecedor
        insert into fornecedor (pessoa_id, data_cadastro) values (p_pessoa_id, curdate());

    else

        # atualiza o registro
        update pessoa set nome = p_nome, tipo_pessoa = p_tipo_pessoa, cpf_cnpj = p_cpf_cnpj, telefone = p_telefone, email = p_email
        where pessoa_id = p_pessoa_id;

    end if;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_pessoa_id as id, 'erro' as type, 'Não foi possível registrar fornecedor.' as msg;
    else
        commit;
        select p_pessoa_id as id, 'sucesso' as type, 'Fornecedor registrado.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_salve_movimentacao
DELIMITER //
CREATE PROCEDURE `p_salve_movimentacao`(
    in p_movimentacao_id int,
    in p_produto_id int,
    in p_pessoa_id int,
    in p_tipo enum('E', 'S'),
    in p_status enum('A', 'Q'),
    in p_quantidade_movimentacao int,
    in p_valor decimal(10,2)
)
proc: begin

    declare xEstoque int default 0;
    declare xQuantidadeMovimentacao int default 0;
    declare xDiferenca int default 0;
    declare xTipo char default '';
    declare xStatus char default '';

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    # valida campos obrigatórios
    if p_produto_id = -1 then
        select p_movimentacao_id as id, 'erro' as type, 'O campo produto é obrigatório' as msg;
        leave proc;
    end if;

    if p_tipo = '' then
        select p_movimentacao_id as id, 'erro' as type, 'O campo tipo é obrigatório' as msg;
        leave proc;
    end if;

    if p_status = '' then
        select p_movimentacao_id as id, 'erro' as type, 'O campo status é obrigatório' as msg;
        leave proc;
    end if;

    if p_quantidade_movimentacao < 0 then
        select p_movimentacao_id as id, 'erro' as type, 'Campo quantidade inválido' as msg;
        leave proc;
    end if;

    if p_valor < 0 then
        select p_movimentacao_id as id, 'erro' as type, 'Campo valor inválido' as msg;
        leave proc;
    end if;

    # verifica se há estoque para a movimentação
    if p_tipo = 'S' then

        select quantidade from produto where produto_id = p_produto_id into xEstoque;

        select quantidade_movimentacao from movimentacao where movimentacao_id = p_movimentacao_id into xQuantidadeMovimentacao;

        if xEstoque < p_quantidade_movimentacao and xQuantidadeMovimentacao <> p_quantidade_movimentacao then
            select p_movimentacao_id as id, 'erro' as type, 'Produto sem estoque para essa movimentação' as msg;
            leave proc;
        end if;

    end if;

    # controle de transação
    start transaction;

    # se o valor passado para chave primária for igual a -1
    if p_movimentacao_id = -1 then

        # insere registro
        insert into movimentacao (produto_id, pessoa_id, tipo, data_movimentacao, status, quantidade_movimentacao, valor)
        values (p_produto_id, p_pessoa_id, p_tipo, current_timestamp, p_status, p_quantidade_movimentacao, p_valor);

        # recupera o id gerado na transação
        select last_insert_id() into p_movimentacao_id;

        # caso seja uma saída dá baixa no estoque
        if p_tipo = 'S' then

            set sql_safe_updates = 0;
            update produto set quantidade = quantidade - p_quantidade_movimentacao where produto_id = p_produto_id;

        elseif p_tipo = 'E' then

            set sql_safe_updates = 0;
            update produto set quantidade = quantidade + p_quantidade_movimentacao where produto_id = p_produto_id;

        end if;

    else

        # recupera a quantidade e o tipo de movimentação
        select quantidade_movimentacao, tipo, status from movimentacao where movimentacao_id = p_movimentacao_id into xQuantidadeMovimentacao, xTipo, xStatus;

        if xStatus = 'Q' then
            select p_movimentacao_id as id, 'erro' as type, 'Não é permitido mudar informações de movimentações quitadas.' as msg;
            leave proc;
        end if;

        # caso o tipo permaneça mas a quantidade modifique
        if xTipo = p_tipo and xQuantidadeMovimentacao <> p_quantidade_movimentacao then
            select (xQuantidadeMovimentacao - p_quantidade_movimentacao) into xDiferenca;
            update movimentacao set quantidade_movimentacao = p_quantidade_movimentacao where movimentacao_id = p_movimentacao_id;
            update produto set quantidade = quantidade + xDiferenca where produto_id = p_produto_id;
        end if;

        # caso o tipo modiifique mas a quantidade permaneça
        if xTipo <> p_tipo and xQuantidadeMovimentacao = p_quantidade_movimentacao then
            update produto set quantidade = quantidade + p_quantidade_movimentacao where produto_id = p_produto_id;
        end if;

        # atualiza a movimentação
        update movimentacao set produto_id = p_produto_id, pessoa_id = p_pessoa_id, tipo = p_tipo, data_movimentacao = current_timestamp, 
            status = p_status, quantidade_movimentacao = p_quantidade_movimentacao, valor = p_valor
        where movimentacao_id = p_movimentacao_id;

    end if;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_movimentacao_id as id, 'erro' as type, 'Não foi possível registrar a movimentação.' as msg;
    else
        commit;
        select p_movimentacao_id as id, 'sucesso' as type, 'Movimentação registrada.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para procedure pooii.p_salve_produto
DELIMITER //
CREATE PROCEDURE `p_salve_produto`(
    in p_produto_id int,
    in p_descricao varchar(200),
    in p_preco_custo decimal(10,2),
    in p_preco_venda decimal(10,2),
    in p_quantidade int
)
proc: begin

    # controle de execução na procedure
    declare exc smallint default 0;

    # manipulador de exceção
    declare continue handler for sqlexception set exc = 1;

    # valida campos obrigatórios
    if p_descricao = '' then
        select p_produto_id as id, 'erro' as type, 'O campo descrição é obrigatório' as msg;
        leave proc;
    end if;

    if p_quantidade < 0 then
        select p_produto_id as id, 'erro' as type, 'O campo quantidade deve ser maior ou igual a zero' as msg;
        leave proc;
    end if;

    # controle de transação
    start transaction;

    # se o valor passado para chave primária for igual a -1
    if p_produto_id = -1 then

        # insere registro de produto
        insert into produto (descricao, preco_custo, preco_venda, quantidade)
        values (p_descricao, p_preco_custo, p_preco_venda, p_quantidade);

        # recupera o id gerado na transação
        select last_insert_id() into p_produto_id;

    else

        # atualiza o registro
        update produto set descricao = p_descricao, preco_custo = p_preco_custo, preco_venda = p_preco_venda, quantidade = p_quantidade
        where produto_id = p_produto_id;

    end if;

    # caso ocorra uma exceção
    if exc = 1 then
        rollback;
        select p_produto_id as id, 'erro' as type, 'Não foi possível registrar o produto.' as msg;
    else
        commit;
        select p_produto_id as id, 'sucesso' as type, 'Produto registrado.' as msg;
    end if;

end//
DELIMITER ;

-- Copiando estrutura para view pooii.v_cliente
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `v_cliente` (
	`pessoa_id` INT(11) NOT NULL,
	`nome` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`tipo_pessoa` ENUM('F','J') NOT NULL COLLATE 'latin1_swedish_ci',
	`cpf_cnpj` CHAR(14) NOT NULL COLLATE 'latin1_swedish_ci',
	`telefone` CHAR(11) NOT NULL COLLATE 'latin1_swedish_ci',
	`email` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`data_cadastro` DATE NOT NULL
) ENGINE=MyISAM;

-- Copiando estrutura para view pooii.v_fornecedor
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `v_fornecedor` (
	`pessoa_id` INT(11) NOT NULL,
	`nome` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`tipo_pessoa` ENUM('F','J') NOT NULL COLLATE 'latin1_swedish_ci',
	`cpf_cnpj` CHAR(14) NOT NULL COLLATE 'latin1_swedish_ci',
	`telefone` CHAR(11) NOT NULL COLLATE 'latin1_swedish_ci',
	`email` VARCHAR(100) NULL COLLATE 'latin1_swedish_ci',
	`data_cadastro` DATE NOT NULL
) ENGINE=MyISAM;

-- Copiando estrutura para view pooii.v_movimentacao
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `v_movimentacao` (
	`movimentacao_id` INT(11) NOT NULL,
	`produto_id` INT(11) NOT NULL,
	`produto` VARCHAR(200) NOT NULL COLLATE 'latin1_swedish_ci',
	`pessoa_id` INT(11) NOT NULL,
	`nome` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`tipo` ENUM('E','S') NOT NULL COLLATE 'latin1_swedish_ci',
	`data_movimentacao` DATE NOT NULL,
	`status` ENUM('A','Q') NOT NULL COLLATE 'latin1_swedish_ci',
	`quantidade_movimentacao` INT(11) NOT NULL,
	`valor` DECIMAL(10,2) NOT NULL
) ENGINE=MyISAM;

-- Copiando estrutura para view pooii.v_cliente
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `v_cliente`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_cliente` AS select `p`.`pessoa_id` AS `pessoa_id`,`p`.`nome` AS `nome`,`p`.`tipo_pessoa` AS `tipo_pessoa`,`p`.`cpf_cnpj` AS `cpf_cnpj`,`p`.`telefone` AS `telefone`,`p`.`email` AS `email`,`c`.`data_cadastro` AS `data_cadastro` from (`pessoa` `p` join `cliente` `c` on((`p`.`pessoa_id` = `c`.`pessoa_id`))) order by `p`.`nome`;

-- Copiando estrutura para view pooii.v_fornecedor
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `v_fornecedor`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_fornecedor` AS select `p`.`pessoa_id` AS `pessoa_id`,`p`.`nome` AS `nome`,`p`.`tipo_pessoa` AS `tipo_pessoa`,`p`.`cpf_cnpj` AS `cpf_cnpj`,`p`.`telefone` AS `telefone`,`p`.`email` AS `email`,`c`.`data_cadastro` AS `data_cadastro` from (`pessoa` `p` join `fornecedor` `c` on((`p`.`pessoa_id` = `c`.`pessoa_id`))) order by `p`.`nome`;

-- Copiando estrutura para view pooii.v_movimentacao
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `v_movimentacao`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_movimentacao` AS select `m`.`movimentacao_id` AS `movimentacao_id`,`m`.`produto_id` AS `produto_id`,`p`.`descricao` AS `produto`,`m`.`pessoa_id` AS `pessoa_id`,`pe`.`nome` AS `nome`,`m`.`tipo` AS `tipo`,`m`.`data_movimentacao` AS `data_movimentacao`,`m`.`status` AS `status`,`m`.`quantidade_movimentacao` AS `quantidade_movimentacao`,`m`.`valor` AS `valor` from ((`movimentacao` `m` join `produto` `p` on((`p`.`produto_id` = `m`.`produto_id`))) join `pessoa` `pe` on((`pe`.`pessoa_id` = `m`.`pessoa_id`))) order by `m`.`movimentacao_id` desc;
