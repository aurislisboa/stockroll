
-- ////////////////////////////////////////// CREATES ///////////////////////////////////////////
 -- USE master;
 -- DROP DATABASE EstoqueV4_prod5_TCC;

-- CREATE DATABASE EstoqueV4_prod5_TCC;


GO
USE EstoqueV4_prod5_TCC;


-- ------------------------ USU�RIO ----------------------------------
	-- incluir campo 'cadastro' com a data de inclus�o no sistema.

GO
CREATE TABLE Usuario
(
	id_usuario INT IDENTITY,
	nome	VARCHAR(200) NOT NULL,
	senha	VARCHAR(250) NOT NULL,
	perfil  VARCHAR(50) NOT NULL,
	ativo	BIT NOT NULL,
);

-- chave prim�ria
ALTER TABLE Usuario ADD CONSTRAINT PK_Usuario PRIMARY KEY (id_usuario);

-- n�o existe usu�rio com nomes id�nticos.
ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario UNIQUE(nome);

-- existe dois tipos de acesso ao sistema
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_perfil CHECK(perfil IN('Gestor', 'Usuario'));

-- caso n�o preencha o perfil, ser� atrib�do o perfil Usu�rio como padr�o:
ALTER TABLE Usuario ADD CONSTRAINT DF_Usuario_perfil DEFAULT 'Usuario' FOR perfil;

-- o usu�rio ter� o perfil ativo no momento da cria��o.
ALTER TABLE Usuario ADD CONSTRAINT DF_Usuario_ativo DEFAULT 1 FOR ativo;



-- ------------------------ PRODUTO ----------------------------------

-- provavelmente ser� preciso deixar o cod_barra como UNIQUE, porque um c�digo � �nico. (OK)
-- O campo imagem_produto poder� ser alterado para VARCHAR e conter apenas o endere�o do local onde a imagem est� salva.
-- cod_produto --> � um valor que o usu�rio digita para identificar um determinado produto.

GO
CREATE TABLE Produto
(
	id_produto INT IDENTITY,
	cod_barra VARCHAR(100),
	nome_produto VARCHAR(255) NOT NULL,
	estoque_min SMALLINT NOT NULL,
	estoque_ideal SMALLINT NOT NULL,
	qtd_estoque SMALLINT,
	-- cod_produto VARCHAR(255),
	-- valor_unitario DECIMAL(6,2),
	-- data_vencimento DATE,
	-- descricao VARCHAR(255) DEFAULT '',
	-- id_categoria INT NOT NULL,
	-- imagem_produto IMAGE, 
	-- id_unidade SMALLINT NOT NULL,

	CONSTRAINT PK_Produto PRIMARY KEY(id_produto),
	-- CONSTRAINT CK_Produto_valor_unit CHECK(valor_unitario >= 0),
	-- CONSTRAINT UQ_Produto_cod_barra UNIQUE(cod_barra),
	-- CONSTRAINT FK_Produto_Categoria FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria),
	-- CONSTRAINT FK_Produto_Unidade FOREIGN KEY(id_unidade) REFERENCES Unidade(id_unidade)
);

-- na cria��o da tabela � atribu�do '0' para a quantidade do estoque.
ALTER TABLE Produto ADD CONSTRAINT DF_Produto_qtd_estoque DEFAULT 0 FOR qtd_estoque;





-- ------------------------ ESTOQUE TRACKING ----------------------------------
-- Tabela respons�vel por rastrear a quantidade de produtos no Estoque.
-- Na tabela Estoque existe somente uma �NICA refer�ncia para Produto. O id_produto � digitado manualmente.
-- E id_produto � Primary Key, no caso � um-para-um, porque o id_produto n�o se repete. 
/*
GO
CREATE TABLE EstoqueTracking
(
	id_produto INT NOT NULL,
	qtd_estoque INT DEFAULT 0,
	qtd_quiosque INT DEFAULT 0,

	CONSTRAINT PK_EstoqueTracking PRIMARY KEY(id_produto),
	CONSTRAINT FK_EstoqueTracking_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto)
);

*/


-- ------------------------ MOVIMENTA��O ESTOQUE ------------------------------

GO
CREATE TABLE MovimentacaoEstoque 
(
	id_mov INT IDENTITY,
	data_mov DATETIME NOT NULL,
	id_usuario INT NOT NULL,
	id_produto INT NOT NULL,
	qtd_produto INT NOT NULL,
	valor_unitario DECIMAL(6,2),
	tipo_mov VARCHAR(7) NOT NULL,
	-- data_vencimento DATE,
	-- local_armazenagem VARCHAR(100) NOT NULL, (n�o precisa)
);

-- defini��o da chave prim�ria:
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT PK_MovimentacaoEstoque PRIMARY KEY(id_mov);

-- relacionamento com a tabela Usu�rio:
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT FK_MovimentacaoEstoque_Usuario FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario);

-- relacionamento com a tabela Produto:
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT FK_MovimentacaoEstoque_Produto FOREIGN KEY(id_produto) REFERENCES Produto(id_produto);

-- caso n�o preencha a data, ser� atribu�do a data atual para a movimenta��o:
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT DF_MovimentacaoEstoque_data_mov DEFAULT GETDATE() FOR data_mov;

-- caso n�o preencha uma quantidade, ser� atribu�do '0'
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT DF_MovimentacaoEstoque_qtd_produto DEFAULT 0 FOR qtd_produto;

-- confere se o valor digitado � maior que '0', n�o existe entrada negativa no estoque.
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_qtd_produto CHECK(qtd_produto > 0);

-- o pre�o do produto precisa ser positivo.
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_Produto_valor_unitario CHECK(valor_unitario >= 0);

-- caso n�o preencha o tipo, ser� atrib�do 'Entrada' como padr�o:
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT DF_MovimentacaoEstoque_tipo_mov DEFAULT 'Entrada' FOR tipo_mov;

-- confere se a palavra corresponde exatamente.
ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_tipo_mov CHECK(tipo_mov IN('Entrada','Saida'));

-- confere se a palavra corresponde exatamente.
-- ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT CK_MovimentacaoEstoque_local_armazenagem CHECK(local_armazenagem IN('Estoque','Quiosque'));

-- caso n�o preencha o local, ser� atrib�do Estoque como padr�o:
-- ALTER TABLE MovimentacaoEstoque ADD CONSTRAINT DF_MovimentacaoEstoque_local_armazenagem DEFAULT 'Estoque' FOR local_armazenagem;

	
	
	
-- ------------------------ MOVIMENTA��O QUIOSQUE ------------------------------

	