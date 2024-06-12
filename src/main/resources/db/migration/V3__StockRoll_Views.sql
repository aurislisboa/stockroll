CREATE VIEW vw_Produto AS 
SELECT id_produto, cod_barra, nome_produto
FROM Produto
WHERE desativado = 0;