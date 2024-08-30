-- -------------------------- LOCAL_ESTOQUE ------------------------------
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('CD - Principal');
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Tatuapé');
INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Aricanduva');
-- INSERT INTO `Local_Estoque` (`nome_local`) VALUES ('Center Norte');

-- -------------------------- USUARIO ------------------------------

INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('Cláudia Araújo', 'claudia@gmail.com', '$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq', 'GESTOR', '2024-04-10 10:28:12');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('Renato Henrique', 'renato@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'GESTOR', '2024-02-25 02:18:17');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('João Ribeiro',  'jose@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:32');
INSERT INTO `usuario` (`nome`, `email`, `senha`, `perfil`, `cadastro`) VALUES ('Maria da Silva', 'maria@gmail.com', '$2a$10$vaAXbvt4UZZ7ZbtB279nyeJemnFeDkbbBuC5HZl2fadI3wbOo9wQe', 'USUARIO', '2024-02-25 02:18:53');

-- -- -------------------------- PRODUTO ------------------------------

INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7898024396291', 'Nutela Pote Grande');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('3045140105502', 'Chocolate Milka');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891000250019', 'Leite Condensado Moça');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891110071942', 'Doce de Leite Mu-Mu 350g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891000259047', 'Leite Ninho Sachê 175g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7897309408391', 'Roll Maltine Ovomaltine 90g Branco');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7622300830090', 'Bolacha Oreo Multipack 144g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891000385500', 'Nesquick Morango 200g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7896072000027', 'Chocolate Bis Lacta 126g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891000100104', 'Bombom Sonho de Valsa 1kg');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7896024790011', 'Fini Tubes 80g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7898406000010', 'Granulado Colorido Dr. Oetker 50g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7891962000012', 'Tubete Bauducco 45g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7896181900013', 'Castanha Moída 100g');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1300000000000', 'Casquinha Cone 12 Unid.');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1400000000000', 'Casquinha Cesta');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1500000000000', 'Casquinha Taco');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1600000000000', 'Copo Ice Cream Roll 500ml');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1700000000000', 'Copo Gelato 300ml');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1800000000000', 'Copo Milk Shake 500ml');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('1900000000000', 'Morango');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2100000000000', 'Framboesa');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2200000000000', 'Mirtilo');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2300000000000', 'Banana');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('2400000000000', 'Limão');
INSERT INTO `produto` (`cod_barra`, `nome_produto`) VALUES ('7896005800016', 'Chantily Pronto Fleischmann 250g');


-- -- -------------------------- QTD POR LOCAL ------------------------------

INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '1', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '2', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '3', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '4', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '5', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '6', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '7', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '8', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '9', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '10', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '11', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '12', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '13', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '14', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '15', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '16', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '17', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '18', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '19', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '20', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '21', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '22', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '23', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '24', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '25', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('1', '26', '10', '50');


INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '1', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '2', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '3', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '4', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '5', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '6', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '7', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '8', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '9', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '10', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '11', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '12', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '13', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '14', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '15', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '16', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '17', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '18', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '19', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '20', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '21', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '22', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '23', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '24', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '25', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('2', '26', '10', '50');



INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '1', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '2', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '3', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '4', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '5', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '6', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '7', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '8', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '9', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '10', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '11', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '12', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '13', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '14', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '15', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '16', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '17', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '18', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '19', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '20', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '21', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '22', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '23', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '24', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '25', '10', '50');
INSERT INTO `Qtd_Por_Local` (`id_local`, `id_produto`, `qtd_min`, `qtd_ideal`) VALUES ('3', '26', '10', '50');
-- -- ----------------------- MOTIVOS --------------------------

INSERT INTO `motivo` (`nome_motivo`) VALUES ('Saída');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Descarte');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Empréstimo');
INSERT INTO `motivo` (`nome_motivo`) VALUES ('Perda');
-- INSERT INTO `motivo` (`nome_motivo`) VALUES ('Devolução');


-- -- ---------------------- MOVIMENTACAO ----------------------------

-- INSERT INTO `Movimentacao` (`id_mov`, `id_local`, `id_usuario`, `id_lote`, `id_motivo`,  `data_mov`, `qtd_produto`, `valor_unitario`, `tipo_mov`) VALUES (NULL, '1', '1', '1', '1','2024-05-29', '40', '25.00',  'Entrada');

-- -- ------------------- ESTOQUE TRACKING ----------------------------

-- INSERT INTO `Estoque_Tracking` (`id_local`, `id_lote`, `qtd_estoque`) VALUES ('1', '1', '40');