-- Active: 1707624501760@@127.0.0.1@3306@estoquev4_prod5

-- ////////////////////////////////////////// CREATES ///////////////////////////////////////////
 -- USE master;
 -- DROP DATABASE EstoqueV4_prod5;

-- CREATE DATABASE EstoqueV4_prod5;


-- USE EstoqueV4_prod5;


-- ------------------------ USUÁRIO ----------------------------------
-- incluir campo 'cadastro' com a data de inclusão no sistema.

CREATE TABLE Usuario
(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	nome	VARCHAR(200) NOT NULL,
	senha	VARCHAR(250) NOT NULL,

	-- caso não preencha o perfil, será atribído o perfil Usuário como padrão:
	perfil  VARCHAR(50) NOT NULL DEFAULT 'Usuario',

	-- o usuário terá o perfil ativo no momento da criação.
	ativo	BIT NOT NULL DEFAULT 1
);

	-- não existe usuário com nomes idênticos.
	ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario UNIQUE(nome);

	-- existe dois tipos de acesso ao sistema
	ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_perfil CHECK(perfil IN('Gestor', 'Usuario'));




-- ------------------------ PRODUTO ----------------------------------

-- O campo imagem_produto poderá ser alterado para VARCHAR e conter apenas o endereço do local onde a imagem está salva.
-- cod_produto --> é um valor que o usuário digita para identificar um determinado produto.

CREATE TABLE Produto
(
	id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
	cod_barra VARCHAR(100),
	nome_produto VARCHAR(255) NOT NULL,
	estoque_min INT NOT NULL,
	estoque_ideal INT NOT NULL,
	qtd_estoque INT DEFAULT 0
	-- cod_produto VARCHAR(255),
	-- valor_unitario DECIMAL(6,2),
	-- data_vencimento DATE,
	-- descricao VARCHAR(255) DEFAULT '',
	-- id_categoria INT NOT NULL,
	-- imagem_produto IMAGE, 
	-- id_unidade INT NOT NULL,

	-- CONSTRAINT CK_Produto_valor_unit CHECK(valor_unitario >= 0),

-- provavelmente será preciso deixar o cod_barra como UNIQUE, porque um código é único. (OK)
	-- CONSTRAINT UQ_Produto_cod_barra UNIQUE(cod_barra),
	-- CONSTRAINT FK_Produto_Categoria FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria),
	-- CONSTRAINT FK_Produto_Unidade FOREIGN KEY(id_unidade) REFERENCES Unidade(id_unidade)
);






-- ------------------------ ESTOQUE TRACKING ----------------------------------
-- Tabela responsável por rastrear a quantidade de produtos no Estoque.
-- Na tabela Estoque existe somente uma ÚNICA referência para Produto. O id_produto é digitado manualmente.
-- E id_produto é Primary Key, no caso é um-para-um, porque o id_produto não se repete. 
/*
CREATE TABLE EstoqueTracking
(
	id_produto INT NOT NULL,
	qtd_estoque INT DEFAULT 0,
	qtd_quiosque INT DEFAULT 0,

	CONSTRAINT PK_EstoqueTracking PRIMARY KEY(id_produto),
	CONSTRAINT FK_EstoqueTracking_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto)
);

*/


-- ------------------------ MOVIMENTAÇÃO ESTOQUE ------------------------------

CREATE TABLE MovimentacaoEstoque 
(
	id_mov BIGINT AUTO_INCREMENT PRIMARY KEY,

	-- caso não preencha a data, será atribuído a data atual para a movimentação:
	data_mov DATETIME NOT NULL DEFAULT NOW(),
	id_usuario INT NOT NULL,
	id_produto BIGINT NOT NULL,

	-- caso não preencha uma quantidade, será atribuído '0'
	qtd_produto INT NOT NULL DEFAULT 0,
	valor_unitario DECIMAL(6,2),

	 -- caso não preencha o tipo, será atribído 'Entrada' como padrão:
	tipo_mov VARCHAR(7) NOT NULL DEFAULT 'Entrada'
	-- data_vencimento DATE,
	-- local_armazenagem VARCHAR(100) NOT NULL, (não precisa)
);
  

	-- relacionamento com a tabela Usuário:
	ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT FK_MovimentacaoEstoque_Usuario FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario);

	-- relacionamento com a tabela Produto:
	ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT FK_MovimentacaoEstoque_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);


	-- confere se o valor digitado é maior que '0', não existe entrada negativa no estoque.
	ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_qtd_produto CHECK(qtd_produto > 0);

	-- o preço do produto precisa ser positivo.
	ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_Produto_valor_unitario CHECK(valor_unitario >= 0);


	-- confere se a palavra corresponde exatamente.
	ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_tipo_mov CHECK(tipo_mov IN('Entrada','Saida'));

	-- confere se a palavra corresponde exatamente.
	-- ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_local_armazenagem CHECK(local_armazenagem IN('Estoque','Quiosque'));

	-- caso não preencha o local, será atribído Estoque como padrão:
	-- ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT DF_MovimentacaoEstoque_local_armazenagem DEFAULT 'Estoque' FOR local_armazenagem;

	
	
	
	
-- ------------------------ MOVIMENTAÇÃO QUIOSQUE ------------------------------

	