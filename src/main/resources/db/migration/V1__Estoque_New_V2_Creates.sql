
-- ////////////////////////////////////////// CREATES ///////////////////////////////////////////
 -- USE master;
 -- DROP DATABASE Estoque_New;

-- CREATE DATABASE Estoque_New_V2;
-- USE Estoque_New_V2;


-- ------------------------ USUÁRIO ----------------------------------

CREATE TABLE Usuario
(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	nome	VARCHAR(200) NOT NULL,
	email	VARCHAR(100) NOT NULL,
	senha	VARCHAR(250) NOT NULL,
	perfil  ENUM('GESTOR', 'USUARIO') NOT NULL DEFAULT 'Usuario',
	cadastro DATETIME NOT NULL DEFAULT NOW(),
	ativo	BIT NOT NULL DEFAULT 1 
);

	-- proibido usuário com nomes idênticos.
	ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario_Nome UNIQUE(nome);

	-- proibido usuário com e-mails idênticos.
	ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario_Email UNIQUE(email);

	-- somente esses perfis podem acessar ao sistema.
	ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Perfil CHECK(perfil IN('Gestor', 'Usuario'));


-- ------------------------ PRODUTO ----------------------------------

CREATE TABLE Produto
(
	id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
	cod_barra VARCHAR(100),
	nome_produto VARCHAR(255) NOT NULL,
	estoque_min INT NOT NULL,
	estoque_ideal INT NOT NULL,
	qtd_estoque INT NOT NULL DEFAULT 0,
    ativo	BIT NOT NULL DEFAULT 1 
	-- valor_unitario DECIMAL(8,2) NOT NULL DEFAULT '0.00',
	-- cod_produto VARCHAR(255),
	-- valor_unitario DECIMAL(6,2),
	-- data_vencimento DATE,
	-- descricao VARCHAR(255) DEFAULT '',
	-- id_categoria INT NOT NULL,
	-- imagem_produto IMAGE, 
	-- id_unidade INT NOT NULL,

	-- provavelmente será preciso deixar o cod_barra como UNIQUE, porque um código é único.
	-- CONSTRAINT UQ_Produto_cod_barra UNIQUE(cod_barra),
	-- CONSTRAINT FK_Produto_Categoria FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria),
	-- CONSTRAINT FK_Produto_Unidade FOREIGN KEY(id_unidade) REFERENCES Unidade(id_unidade)
);


-- ------------------------ LOTE ----------------------------------

CREATE TABLE Lote 
(
	id_lote INT AUTO_INCREMENT PRIMARY KEY,
	data_vencimento	DATETIME NOT NULL,
	numero_lote	VARCHAR(20)
);



-- ------------------------ MOTIVO SAIDA ------------------------------

CREATE TABLE Motivo_Saida
(
	id_motivo INT AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(150) 
);

ALTER TABLE Motivo_Saida ADD CONSTRAINT UQ_Motivo_Saida_descricao UNIQUE(descricao);



-- ------------------------ MOVIMENTAÇÃO ESTOQUE ------------------------------

CREATE TABLE Mov_Estoque 
(
	id_mov BIGINT AUTO_INCREMENT PRIMARY KEY,
	data_mov DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	id_usuario INT NOT NULL,
	id_produto BIGINT NOT NULL,
	qtd_produto INT NOT NULL DEFAULT 0,
	valor_unitario DECIMAL(8,2) NOT NULL DEFAULT '0.00',
	tipo_mov VARCHAR(7) NOT NULL DEFAULT 'Entrada',
	id_motivo INT NOT NULL,
	id_lote INT
);

	-- relacionamento com a tabela Usuário:
	ALTER TABLE Mov_Estoque ADD CONSTRAINT FK_MovEstoque_Usuario FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario);

	-- relacionamento com a tabela Produto:
	ALTER TABLE Mov_Estoque ADD CONSTRAINT FK_MovEstoque_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);

	-- relacionamento com a tabela lote:
	ALTER TABLE Mov_Estoque ADD CONSTRAINT FK_MovEstoque_Lote FOREIGN KEY(id_lote) REFERENCES Lote(id_lote);
	
	-- confere se o valor digitado é maior que '0', não existe entrada negativa no estoque.
	ALTER TABLE Mov_Estoque ADD CONSTRAINT CK_MovEstoque_qtd_produto CHECK(qtd_produto > 0);
	
	-- o preço do produto deve ser positivo.
	ALTER TABLE Mov_Estoque ADD CONSTRAINT CK_MovEstoque_valor_unitario CHECK(valor_unitario >= 0);

	-- relacionamento com a tabela Motivo_Saida:
	ALTER TABLE Mov_Estoque ADD CONSTRAINT FK_MovEstoque_Motivo_Saida FOREIGN KEY(id_motivo) REFERENCES Motivo_Saida(id_motivo);

	-- confere se a palavra corresponde exatamente.
	ALTER TABLE Mov_Estoque ADD CONSTRAINT CK_MovEstoque_tipo_mov CHECK(tipo_mov IN('Entrada','Saida'));
	
	-- confere se a palavra corresponde exatamente.
	-- ALTER TABLE Mov_Estoque ADD CONSTRAINT CK_Estoque_local_armazenagem CHECK(local_armazenagem IN('Estoque','Quiosque'));

	-- caso não preencha o local, será atribído Estoque como padrão:
	-- ALTER TABLE Mov_Estoque ADD CONSTRAINT DF_Estoque_local_armazenagem DEFAULT 'Estoque' FOR local_armazenagem;




-- ------------------------ MOVIMENTAÇÃO QUIOSQUE ------------------------------





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


	