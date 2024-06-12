-- -------------------------- LOCAL_ESTOQUE ------------------------------
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('CD - Principal');
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Tatuapé');
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Aricanduva');
-- INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Center Norte');

-- -------------------------- USUARIO ------------------------------

INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('Renato Henrique Fernão', 'renato@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'GESTOR', '2024-02-25 02:18:17');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('João Ribeiro de Souza',  'jose@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:32');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('Maria da Silva', 'maria@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:53');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('admin', 'admin@gmail.com', '$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq', 'GESTOR', '2024-04-10 10:28:12');

-- -- -------------------------- PRODUTO ------------------------------

-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7898024396291', 'Nutela Pote Grande');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('3045140105502', 'Chocolate Milka');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('0000000000000', 'Leite Condensado');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1000000000000', 'Doce de Leite');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2000000000000', 'Leite Ninho');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('3000000000000', 'Roll Maltine');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('4000000000000', 'Bolacha Oreo');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('5000000000000', 'Nesquick');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('6000000000000', 'Chocolate Bis');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7000000000000', 'Chocolate Sonho de Valsa');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('8000000000000', 'Fini');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('9000000000000', 'Granulado Colorido');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1100000000000', 'Tubete');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1200000000000', 'Castanha Moída');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1300000000000', 'Casquinha Cone');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1400000000000', 'Casquinha Cesta');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1500000000000', 'Casquinha Taco');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1600000000000', 'Copo Ice Cream Roll');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1700000000000', 'Copo Gelato');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1800000000000', 'Copo Milk Shake');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1900000000000', 'Morango');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2100000000000', 'Framboesa');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2200000000000', 'Mirtilo');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2300000000000', 'Banana');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2400000000000', 'Limão');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2500000000000', 'Leite');
-- INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2600000000000', 'Chantily');

-- -- -------------------------- QTD POR LOCAL ------------------------------

-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '1', '10', '50');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '2', '10', '50');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '3', '10', '50');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '4', '10', '50');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '5', '10', '50');

-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '1', '5', '25');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '2', '5', '25');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '3', '5', '25');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '4', '5', '25');
-- INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '5', '5', '25');

-- -- --------------------------- LOTE ------------------------------
    
-- INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `valor_unitario`) VALUES ('1', '2024-12-01', '5.00');
-- INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `valor_unitario`) VALUES ('2', '2025-01-01', '15.00');    
-- INSERT INTO `Lote` (`id_produto`, `data_vencimento`, `valor_unitario`) VALUES ('3', '2025-02-01', '25.00'); 


-- -- ----------------------- MOTIVOS --------------------------

INSERT INTO `motivo` (`nome_motivo`) VALUES ('-');
-- INSERT INTO `motivo` (`nome_motivo`) VALUES ('Empréstimo');
-- INSERT INTO `motivo` (`nome_motivo`) VALUES ('Descarte');
-- INSERT INTO `motivo` (`nome_motivo`) VALUES ('Perda');
-- INSERT INTO `motivo` (`nome_motivo`) VALUES ('Devolução');


-- -- ---------------------- MOVIMENTACAO ----------------------------

-- INSERT INTO `Movimentacao` (`id_mov`, `id_local`, `id_usuario`, `id_lote`, `id_motivo`,  `data_mov`, `qtd_produto`, `valor_unitario`, `tipo_mov`) VALUES (NULL, '1', '1', '1', '1','2024-05-29', '40', '25.00',  'Entrada');

-- -- ------------------- ESTOQUE TRACKING ----------------------------

-- INSERT INTO `Estoque_Tracking` (`id_local`, `id_lote`, `qtd_estoque`) VALUES ('1', '1', '40');