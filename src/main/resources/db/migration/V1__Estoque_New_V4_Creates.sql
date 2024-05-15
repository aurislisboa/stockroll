
-- ////////////////////////////////////////// CREATES ///////////////////////////////////////////

-- ///////// VERSÃO 4_ /////////////

-- DROP DATABASE Estoque_New_V4;
-- CREATE DATABASE Estoque_New_V4;
-- USE Estoque_New_V4;


-- --------------------- LOCAL_ARMAGENAGEM -----------------------------

CREATE TABLE Local_Estoque
(
	id_local INT AUTO_INCREMENT PRIMARY KEY,
	nome_local	VARCHAR(200) NOT NULL,
	ativo	BIT NOT NULL DEFAULT 1 
);

-- -------------------------- USUÁRIO ----------------------------------

CREATE TABLE Usuario
(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	-- id_local INT NOT NULL,
	nome	VARCHAR(200) NOT NULL,
	email	VARCHAR(100) NOT NULL,
	senha	VARCHAR(250) NOT NULL,
	perfil  ENUM('GESTOR', 'USUARIO') NOT NULL DEFAULT 'Usuario',
	cadastro DATETIME NOT NULL DEFAULT NOW(),
	ativo	BIT NOT NULL DEFAULT 1 
);

	-- relacionamento com a tabela Local_Estoque.
	-- ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_LocalEstoque FOREIGN KEY(id_local) REFERENCES Local_Estoque(id_local);

	-- proibido usuário com nomes idênticos.
	ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario_Nome UNIQUE(nome);

	-- proibido usuário com e-mails idênticos.
	ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario_Email UNIQUE(email);

	-- perfis de acesso ao sistema.
	ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Perfil CHECK(perfil IN('Gestor', 'Usuario'));


-- ------------------------ PRODUTO ----------------------------------

CREATE TABLE Produto
(
	id_produto INT AUTO_INCREMENT PRIMARY KEY,
	cod_barra VARCHAR(100),
	nome_produto VARCHAR(255) NOT NULL,
  ativo	BIT NOT NULL DEFAULT 1 
	-- descricao VARCHAR(255) DEFAULT '',
	-- id_categoria INT NOT NULL,
	-- imagem_produto IMAGE, 
);

-- cod_barra não se repete.
ALTER TABLE Produto ADD CONSTRAINT UQ_Produto_cod_barra UNIQUE(cod_barra);

-- nome_produto não se repete.
ALTER TABLE Produto ADD CONSTRAINT UQ_Produto_nome_produto UNIQUE(nome_produto);


-- ------------------- QTD_PRODUTO_POR_LOCAL ----------------------------

CREATE TABLE Qtd_Por_Local
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_local INT NOT NULL,
	id_produto INT NOT NULL,
  qtd_min INT NOT NULL,
	qtd_ideal INT NOT NULL
);

-- relacionamento com a tabela Local_Estoque:
ALTER TABLE Qtd_Por_Local ADD CONSTRAINT FK_QtdPorLocal_LocalEstoque FOREIGN KEY(id_local) REFERENCES Local_Estoque(id_local);

-- relacionamento com a tabela Produto:
ALTER TABLE Qtd_Por_Local ADD CONSTRAINT FK_QtdPorLocal_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);

-- campo ÚNICO Composto:
ALTER TABLE Qtd_Por_Local ADD CONSTRAINT UQ_QtdPorLocal UNIQUE(id_local, id_produto);

-- confere se o valor digitado é maior que '0', não existe qtd_min negativo.
-- ALTER TABLE Qtd_Prod_Por_Local ADD CONSTRAINT CK_QtdProdPorLocal_qtd_min CHECK(qtd_min >= 0);

-- confere se o valor digitado é maior que '0', não existe qtd_ideal negativo.
-- ALTER TABLE Qtd_Prod_Por_Local ADD CONSTRAINT CK_QtdProdPorLocal_qtd_ideal CHECK(qtd_ideal >= 0);

-- --------------------------- LOTE -----------------------------------

CREATE TABLE Lote 
(
	id_lote INT AUTO_INCREMENT PRIMARY KEY,
	id_produto INT NOT NULL,
	-- nome_lote VARCHAR(20) NOT NULL,
	data_vencimento	DATE NOT NULL,
	-- valor_unitario DECIMAL(8,2) NOT NULL DEFAULT '0.00',
	ativo	BIT NOT NULL DEFAULT 1 
);

-- relacionamento com a tabela Produto:
ALTER TABLE Lote ADD CONSTRAINT FK_Lote_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);

-- nome_lote não se repete:
-- ALTER TABLE Lote ADD CONSTRAINT UQ_Lote_nome_lote UNIQUE(nome_lote);


-- ------------------------- MOTIVO -----------------------------

CREATE TABLE Motivo
(
	id_motivo INT AUTO_INCREMENT PRIMARY KEY,
	nome_motivo VARCHAR(100) NOT NULL,
	ativo	BIT NOT NULL DEFAULT 1 
);

ALTER TABLE Motivo ADD CONSTRAINT UQ_Motivo_descricao UNIQUE(nome_motivo);



-- ------------------------ MOVIMENTAÇÃO ------------------------------

CREATE TABLE Movimentacao 
(
	id_mov BIGINT AUTO_INCREMENT PRIMARY KEY,
	id_local INT NOT NULL,
	id_usuario INT NOT NULL,
	id_lote INT NOT NULL,
	-- id_produto INT NOT NULL,
	id_motivo INT NOT NULL,
	data_mov DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	qtd_produto INT NOT NULL DEFAULT 1,
	valor_unitario DECIMAL(8,2),
	tipo_mov VARCHAR(7) NOT NULL DEFAULT 'Entrada'
);

-- relacionamento com a tabela Usuário:
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Usuario FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario);

-- relacionamento com a tabela Local_Estoque:
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_LocalEstoque FOREIGN KEY(id_local) REFERENCES Local_Estoque(id_local);

-- relacionamento com a tabela lote:
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Lote FOREIGN KEY(id_lote) REFERENCES Lote(id_lote);

-- relacionamento com a tabela Produto:
-- ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);

-- relacionamento com a tabela Motivo:
ALTER TABLE Movimentacao ADD CONSTRAINT FK_Movimentacao_Motivo FOREIGN KEY(id_motivo) REFERENCES Motivo(id_motivo);

-- confere se o valor digitado é maior que '0', não existe entrada negativa no estoque.
ALTER TABLE Movimentacao ADD CONSTRAINT CK_Movimentacao_qtd_produto CHECK(qtd_produto > 0);

-- o preço do produto deve ser positivo.
-- ALTER TABLE Movimentacao ADD CONSTRAINT CK_Movimentacao_valor_unitario CHECK(valor_unitario >= 0);

-- confere se a palavra corresponde exatamente.
ALTER TABLE Movimentacao ADD CONSTRAINT CK_Movimentacao_tipo_mov CHECK(tipo_mov IN('Entrada','Saida'));

-- confere se a palavra corresponde exatamente.
-- ALTER TABLE Mov_Estoque ADD CONSTRAINT CK_Estoque_local_armazenagem CHECK(local_armazenagem IN('Estoque','Quiosque'));

-- caso não preencha o local, será atribído Estoque como padrão:
-- ALTER TABLE Mov_Estoque ADD CONSTRAINT DF_Estoque_local_armazenagem DEFAULT 'Estoque' FOR local_armazenagem;



-- --------------------- ESTOQUE TRACKING ------------------------------
-- Tabela responsável por rastrear a quantidade de produtos no Estoque.
-- Na tabela Estoque existe somente uma ÚNICA referência para Produto. O id_produto é digitado manualmente.


CREATE TABLE Estoque_Tracking
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_local INT NOT NULL,
	id_lote INT NOT NULL,
	-- id_produto INT NOT NULL,
	qtd_estoque INT DEFAULT 0,
	data_vencimento	DATE NOT NULL,
	
    CONSTRAINT FK_EstoqueTracking_LocalEstoque FOREIGN KEY(id_local) REFERENCES Local_Estoque(id_local),
		CONSTRAINT FK_EstoqueTracking_Lote FOREIGN KEY(id_lote) REFERENCES Lote(id_lote),
    -- CONSTRAINT FK_EstoqueTracking_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto),
    CONSTRAINT UQ_EstoqueTracking UNIQUE(id_lote, id_local)
);