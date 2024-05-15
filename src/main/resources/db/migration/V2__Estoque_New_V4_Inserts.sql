-- -------------------------- LOCAL_ESTOQUE ------------------------------
INSERT INTO `Local_Estoque` (`nome_local`, `ativo`) VALUES ('CD.Principal', b'1');
INSERT INTO `Local_Estoque` (`nome_local`, `ativo`) VALUES ('Tatuapé', b'1');
INSERT INTO `Local_Estoque` (`nome_local`, `ativo`) VALUES ('Aricanduva', b'1');
INSERT INTO `Local_Estoque` (`nome_local`, `ativo`) VALUES ('Center Norte', b'1');

-- -------------------------- USUARIO ------------------------------

INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`, `ativo`) VALUES ('Renato Henrique Fernão', 'renato@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'GESTOR', '2024-02-25 02:18:17', b'1');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`, `ativo`) VALUES ('João Ribeiro de Souza',  'jose@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:32', b'1');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`, `ativo`) VALUES ('Maria da Silva', 'maria@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:53', b'1');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`, `ativo`) VALUES ('admin', 'admin@gmail.com', '$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq', 'GESTOR', '2024-04-10 10:28:12', b'1');

-- -------------------------- PRODUTO ------------------------------

INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('7898024396291', 'Nutela Pote Grande', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('3045140105502', 'Chocolate Milka', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('0000000000000', 'Leite Condensado', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1000000000000', 'Doce de Leite', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2000000000000', 'Leite Ninho', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('3000000000000', 'Roll Maltine', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('4000000000000', 'Bolacha Oreo', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('5000000000000', 'Nesquick', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('6000000000000', 'Chocolate Bis', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('7000000000000', 'Chocolate Sonho de Valsa', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('8000000000000', 'Fini', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('9000000000000', 'Granulado Colorido', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1100000000000', 'Tubete', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1200000000000', 'Castanha Moída', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1300000000000', 'Casquinha Cone', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1400000000000', 'Casquinha Cesta', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1500000000000', 'Casquinha Taco', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1600000000000', 'Copo Ice Cream Roll', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1700000000000', 'Copo Gelato', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1800000000000', 'Copo Milk Shake', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('1900000000000', 'Morango', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2100000000000', 'Framboesa', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2200000000000', 'Mirtilo', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2300000000000', 'Banana', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2400000000000', 'Limão', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2500000000000', 'Leite', b'1');
INSERT INTO `produto` (`cod_barra`, `nome_produto`, `ativo`) VALUES ('2600000000000', 'Chantily', b'1');

-- -------------------------- QTD POR LOCAL ------------------------------

INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '1', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '2', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '3', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '4', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '5', '10', '50');

INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '1', '5', '25');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '2', '5', '25');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '3', '5', '25');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '4', '5', '25');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '5', '5', '25');

-- --------------------------- LOTE ------------------------------
    
INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `ativo`) VALUES ('1', '2024-12-01', b'1');
INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `ativo`) VALUES ('2', '2025-01-01', b'1');    
INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `ativo`) VALUES ('3', '2025-02-01', b'1'); 

-- ----------------------- MOTIVO SAÍDA --------------------------

INSERT INTO `motivo` (`nome_motivo`) VALUES ('-');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Empréstimo');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Descarte');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Perda');

-- ---------------------- MOVIMENTACAO ----------------------------

INSERT INTO `Movimentacao` (`id_mov`, `id_local`, `id_usuario`, `id_lote`, `id_motivo`,  `data_mov`, `qtd_produto`, `valor_unitario`, `tipo_mov`) 
                     VALUES (NULL, '1', '1', '1', '1','2024-05-29', '40', '25.00',  'Entrada');

-- ------------------- ESTOQUE TRACKING ----------------------------

INSERT INTO `Estoque_Tracking` (`id_local`, `id_lote`, `qtd_estoque`,  `data_vencimento`) VALUES ('1', '1', '40', '2024-12-01');