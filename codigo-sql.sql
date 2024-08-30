-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.18-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para stockroll
CREATE DATABASE IF NOT EXISTS `stockroll` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stockroll`;

-- Copiando estrutura para tabela stockroll.estoque_tracking
CREATE TABLE IF NOT EXISTS `estoque_tracking` (
  `id_local` int(11) NOT NULL,
  `id_lote` int(11) NOT NULL,
  `qtd_estoque` int(11) DEFAULT 0,
  PRIMARY KEY (`id_local`,`id_lote`),
  KEY `FK_Estoque_Lote` (`id_lote`),
  KEY `IDX_Estoque` (`id_local`,`id_lote`),
  CONSTRAINT `FK_Estoque_LocalEstoque` FOREIGN KEY (`id_local`) REFERENCES `local_estoque` (`id_local`),
  CONSTRAINT `FK_Estoque_Lote` FOREIGN KEY (`id_lote`) REFERENCES `lote` (`id_lote`),
  CONSTRAINT `CK_Estoque_qtd_estoque` CHECK (`qtd_estoque` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.estoque_tracking: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `estoque_tracking` DISABLE KEYS */;
INSERT INTO `estoque_tracking` (`id_local`, `id_lote`, `qtd_estoque`) VALUES
	(1, 1, 54),
	(1, 2, 8),
	(1, 3, 100),
	(1, 4, 50),
	(1, 5, 500),
	(1, 6, 500),
	(2, 1, 0),
	(3, 1, 7);
/*!40000 ALTER TABLE `estoque_tracking` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.flyway_schema_history
CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.flyway_schema_history: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'StockRoll Creates', 'SQL', 'V1__StockRoll_Creates.sql', -326896923, 'root', '2024-06-28 16:17:58', 16686, 1),
	(2, '2', 'StockRoll Inserts', 'SQL', 'V2__StockRoll_Inserts.sql', -828241090, 'root', '2024-06-28 16:17:59', 517, 1),
	(3, '3', 'StockRoll Views', 'SQL', 'V3__StockRoll_Views.sql', -1133812141, 'root', '2024-06-28 16:17:59', 50, 1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.local_estoque
CREATE TABLE IF NOT EXISTS `local_estoque` (
  `id_local` int(11) NOT NULL AUTO_INCREMENT,
  `nome_local` varchar(200) NOT NULL,
  `desativado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.local_estoque: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `local_estoque` DISABLE KEYS */;
INSERT INTO `local_estoque` (`id_local`, `nome_local`, `desativado`) VALUES
	(1, 'CD - Principal', b'0'),
	(2, 'Tatuapé', b'0'),
	(3, 'Aricanduva', b'0');
/*!40000 ALTER TABLE `local_estoque` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.lote
CREATE TABLE IF NOT EXISTS `lote` (
  `id_lote` int(11) NOT NULL AUTO_INCREMENT,
  `id_produto` int(11) NOT NULL,
  `data_vencimento` date NOT NULL,
  `valor_unitario` decimal(8,2) DEFAULT 0.00,
  `desativado` bit(1) NOT NULL DEFAULT b'0',
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_lote`),
  KEY `FK_Lote_Produto` (`id_produto`),
  KEY `IDX_Lote` (`data_vencimento`),
  CONSTRAINT `FK_Lote_Produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.lote: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` (`id_lote`, `id_produto`, `data_vencimento`, `valor_unitario`, `desativado`, `quantidade`) VALUES
	(1, 27, '2028-08-08', 8.00, b'0', 80),
	(2, 2, '2028-02-20', 2.00, b'0', 8),
	(3, 2, '2025-10-10', 1.00, b'0', 100),
	(4, 1, '2052-05-05', 2.00, b'0', 50),
	(5, 27, '2025-05-05', 5.00, b'0', 500),
	(6, 27, '2025-05-05', 5.00, b'0', 500);
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.motivo
CREATE TABLE IF NOT EXISTS `motivo` (
  `id_motivo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_motivo` varchar(100) NOT NULL,
  `tipo` bit(1) NOT NULL DEFAULT b'0',
  `desativado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_motivo`),
  UNIQUE KEY `UQ_Motivo_descricao` (`nome_motivo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.motivo: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `motivo` DISABLE KEYS */;
INSERT INTO `motivo` (`id_motivo`, `nome_motivo`, `tipo`, `desativado`) VALUES
	(1, 'Saída', b'0', b'0'),
	(2, 'Descarte', b'0', b'0'),
	(3, 'Empréstimo', b'0', b'0'),
	(4, 'Perda', b'0', b'0');
/*!40000 ALTER TABLE `motivo` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.movimentacao
CREATE TABLE IF NOT EXISTS `movimentacao` (
  `id_mov` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_local` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_lote` int(11) NOT NULL,
  `id_motivo` int(11) NOT NULL,
  `data_mov` datetime NOT NULL DEFAULT current_timestamp(),
  `qtd_produto` int(11) NOT NULL DEFAULT 1,
  `valor_unitario` decimal(8,2) DEFAULT 0.00,
  `tipo_mov` varchar(13) NOT NULL DEFAULT 'Entrada',
  PRIMARY KEY (`id_mov`),
  KEY `FK_Movimentacao_Usuario` (`id_usuario`),
  KEY `FK_Movimentacao_LocalEstoque` (`id_local`),
  KEY `FK_Movimentacao_Lote` (`id_lote`),
  KEY `FK_Movimentacao_Motivo` (`id_motivo`),
  CONSTRAINT `FK_Movimentacao_LocalEstoque` FOREIGN KEY (`id_local`) REFERENCES `local_estoque` (`id_local`),
  CONSTRAINT `FK_Movimentacao_Lote` FOREIGN KEY (`id_lote`) REFERENCES `lote` (`id_lote`),
  CONSTRAINT `FK_Movimentacao_Motivo` FOREIGN KEY (`id_motivo`) REFERENCES `motivo` (`id_motivo`),
  CONSTRAINT `FK_Movimentacao_Usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `CK_Movimentacao_qtd_produto` CHECK (`qtd_produto` >= 0),
  CONSTRAINT `CK_Movimentacao_tipo_mov` CHECK (`tipo_mov` in ('Entrada','Saida','Transferência'))
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.movimentacao: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `movimentacao` DISABLE KEYS */;
INSERT INTO `movimentacao` (`id_mov`, `id_local`, `id_usuario`, `id_lote`, `id_motivo`, `data_mov`, `qtd_produto`, `valor_unitario`, `tipo_mov`) VALUES
	(1, 1, 4, 1, 1, '2024-06-28 16:18:56', 80, 8.00, 'Entrada'),
	(2, 2, 4, 1, 1, '2024-06-28 16:19:15', 8, 8.00, 'Transferência'),
	(3, 3, 4, 1, 1, '2024-06-28 16:19:26', 8, 8.00, 'Transferência'),
	(8, 2, 4, 1, 1, '2024-06-28 16:28:06', 8, 8.00, 'Saida'),
	(9, 2, 4, 1, 1, '2024-06-28 16:28:38', 10, 8.00, 'Transferência'),
	(10, 2, 4, 1, 2, '2024-06-28 16:28:47', 5, 8.00, 'Saida'),
	(11, 3, 4, 1, 2, '2024-06-28 16:34:18', 1, 8.00, 'Saida'),
	(12, 1, 1, 2, 1, '2024-06-28 16:52:00', 8, 2.00, 'Entrada'),
	(13, 1, 4, 3, 1, '2024-06-28 16:59:27', 100, 1.00, 'Entrada'),
	(14, 1, 4, 4, 1, '2024-06-28 16:59:57', 50, 2.00, 'Entrada'),
	(15, 1, 4, 5, 1, '2024-06-28 17:03:09', 500, 5.00, 'Entrada'),
	(16, 1, 4, 6, 1, '2024-06-28 17:03:12', 500, 5.00, 'Entrada'),
	(18, 2, 4, 1, 1, '2024-06-28 17:11:24', 5, 8.00, 'Saida');
/*!40000 ALTER TABLE `movimentacao` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `cod_barra` varchar(100) NOT NULL,
  `nome_produto` varchar(255) NOT NULL,
  `desativado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_produto`),
  UNIQUE KEY `UQ_Produto_cod_barra` (`cod_barra`),
  UNIQUE KEY `UQ_Produto_nome_produto` (`nome_produto`),
  CONSTRAINT `CK_Produto_cod_barra` CHECK (`cod_barra` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.produto: ~27 rows (aproximadamente)
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`id_produto`, `cod_barra`, `nome_produto`, `desativado`) VALUES
	(1, '7898024396291', 'Nutela Pote Grande', b'0'),
	(2, '3045140105502', 'Chocolate Milka', b'0'),
	(3, '7891000250019', 'Leite Condensado Moça', b'0'),
	(4, '7891110071942', 'Doce de Leite Mu-Mu 350g', b'0'),
	(5, '7891000259047', 'Leite Ninho Sachê 175g', b'0'),
	(6, '7897309408391', 'Roll Maltine Ovomaltine 90g Branco', b'0'),
	(7, '7622300830090', 'Bolacha Oreo Multipack 144g', b'0'),
	(8, '7891000385500', 'Nesquick Morango 200g', b'0'),
	(9, '7896072000027', 'Chocolate Bis Lacta 126g', b'0'),
	(10, '7891000100104', 'Bombom Sonho de Valsa 1kg', b'0'),
	(11, '7896024790011', 'Fini Tubes 80g', b'0'),
	(12, '7898406000010', 'Granulado Colorido Dr. Oetker 50g', b'0'),
	(13, '7891962000012', 'Tubete Bauducco 45g', b'0'),
	(14, '7896181900013', 'Castanha Moída 100g', b'0'),
	(15, '1300000000000', 'Casquinha Cone 12 Unid.', b'0'),
	(16, '1400000000000', 'Casquinha Cesta', b'0'),
	(17, '1500000000000', 'Casquinha Taco', b'0'),
	(18, '1600000000000', 'Copo Ice Cream Roll 500ml', b'0'),
	(19, '1700000000000', 'Copo Gelato 300ml', b'0'),
	(20, '1800000000000', 'Copo Milk Shake 500ml', b'0'),
	(21, '1900000000000', 'Morango', b'0'),
	(22, '2100000000000', 'Framboesa', b'0'),
	(23, '2200000000000', 'Mirtilo', b'0'),
	(24, '2300000000000', 'Banana', b'0'),
	(25, '2400000000000', 'Limão', b'0'),
	(26, '7896005800016', 'Chantily Pronto Fleischmann 250g', b'0'),
	(27, '7891000307045', 'Nescafé Suave', b'0');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.qtd_por_local
CREATE TABLE IF NOT EXISTS `qtd_por_local` (
  `id_local` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `qtd_min` int(11) DEFAULT NULL,
  `qtd_ideal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_local`,`id_produto`),
  KEY `IDX_QtdPorLocal` (`id_local`,`id_produto`),
  KEY `FK_QtdPorLocal_Produto` (`id_produto`),
  CONSTRAINT `FK_QtdPorLocal_LocalEstoque` FOREIGN KEY (`id_local`) REFERENCES `local_estoque` (`id_local`),
  CONSTRAINT `FK_QtdPorLocal_Produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `CK_QtdPorLocal_qtd_min` CHECK (`qtd_min` >= 0),
  CONSTRAINT `CK_QtdPorLocal_qtd_ideal` CHECK (`qtd_ideal` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.qtd_por_local: ~29 rows (aproximadamente)
/*!40000 ALTER TABLE `qtd_por_local` DISABLE KEYS */;
INSERT INTO `qtd_por_local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES
	(1, 1, 10, 50),
	(1, 2, 10, 50),
	(1, 3, 10, 50),
	(1, 4, 10, 50),
	(1, 5, 10, 50),
	(1, 6, 10, 50),
	(1, 7, 10, 50),
	(1, 8, 10, 50),
	(1, 9, 10, 50),
	(1, 10, 10, 50),
	(1, 11, 10, 50),
	(1, 12, 10, 50),
	(1, 13, 10, 50),
	(1, 14, 10, 50),
	(1, 15, 10, 50),
	(1, 16, 10, 50),
	(1, 17, 10, 50),
	(1, 18, 10, 50),
	(1, 19, 10, 50),
	(1, 20, 10, 50),
	(1, 21, 10, 50),
	(1, 22, 10, 50),
	(1, 23, 10, 50),
	(1, 24, 10, 50),
	(1, 25, 10, 50),
	(1, 26, 10, 50),
	(1, 27, 10, 100),
	(2, 27, 10, 100),
	(3, 27, 10, 100);
/*!40000 ALTER TABLE `qtd_por_local` ENABLE KEYS */;

-- Copiando estrutura para tabela stockroll.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(250) NOT NULL,
  `perfil` enum('GESTOR','USUARIO') NOT NULL DEFAULT 'USUARIO',
  `cadastro` datetime NOT NULL DEFAULT current_timestamp(),
  `desativado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UQ_Usuario_Email` (`email`),
  CONSTRAINT `CK_Usuario_Perfil` CHECK (`perfil` in ('Gestor','Usuario'))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela stockroll.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `nome`, `email`, `senha`, `perfil`, `cadastro`, `desativado`) VALUES
	(1, 'Cláudia Araújo', 'claudia@gmail.com', '$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq', 'GESTOR', '2024-04-10 10:28:12', b'0'),
	(2, 'Renato Henrique', 'renato@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'GESTOR', '2024-02-25 02:18:17', b'0'),
	(3, 'João Ribeiro', 'jose@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:32', b'0'),
	(4, 'Maria da Silva', 'maria@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:53', b'0');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para view stockroll.vw_produto
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vw_produto` (
	`id_produto` INT(11) NOT NULL,
	`cod_barra` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
	`nome_produto` VARCHAR(255) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view stockroll.vw_produto
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vw_produto`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vw_produto` AS SELECT id_produto, cod_barra, nome_produto
FROM Produto
WHERE desativado = 0 ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
