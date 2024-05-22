package br.com.usystem.stockroll.service;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.EstoqueId;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Motivo;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.model.Usuario;
import br.com.usystem.stockroll.repository.EstoqueRepository;
import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.repository.MovimentacaoRepository;
import br.com.usystem.stockroll.repository.UsuarioRepository;


@Service
public class MovimentacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired 
    private EstoqueRepository estoqueRepository; // deu erro, porque a movimentação guardou a informação da transação anterior.
  



    /* 
     *  Serviço responsável por cadastrar um produto no na tabela Lote,
     *  criar um registro na tabela Movimentação 
     *  e cadastrar um novo lote na tabela Estoque. 
     */

  public Movimentacao salvarMovimentacao(Lote lote, Principal principal) {   // Recebe os dados do lote e o Usuário logado
           
            /*  Primeiro é salvo o Lote  */

            loteRepository.save(lote);          // cadastra um novo lote na tabela Lote.
            
            /*
            *  Depois é definido os campos pora inserir uma Movimentação de Entrada no CD - Principal.
            *  O código abaixo é uma cópia descarada da Action 'cadastrar' de MovimentacaoController
            *  'movimentacao/cadastrar/entrada'
            */

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();
        Movimentacao movimentacao = new Movimentacao();
                movimentacao.setUsuario(usuario);
                movimentacao.setDataMovimentacao(LocalDateTime.now());
                movimentacao.setTipoMovimentacao("Entrada");
                movimentacao.setMotivo(new Motivo(1, "-"));        
                movimentacao.setLocal(new Local(1, "CD.Principal"));

                // ------------- Lote------------------
                movimentacao.setLote(lote);                                 // lote que acabou de ser cadastrado.
                movimentacao.setQuantidade(lote.getQuantidade());           // quantidade de produtos do lote que acabou de ser cadastrado.
                movimentacao.setValorUnitario(lote.getValorUnitario());     

            movimentacaoRepository.save(movimentacao);

            /*  Cadastrar Lote na tabela Estoque  */
            
            EstoqueId estoqueId = new EstoqueId();
                    estoqueId.setLocal(new Local(1, "CD - Pricipal"));
                    estoqueId.setLote(lote);

            Estoque estoque = new Estoque();
                    estoque.setId(estoqueId);
                    estoque.setQuantidade(lote.getQuantidade());

            // Estoque estoque = new Estoque();
                    // estoque.setLocal(new Local(1, "CD - Pricipal"));
                    // estoque.setLote(lote);
                    // estoque.setQuantidade(lote.getQuantidade());


            estoqueRepository.save(estoque);

          return movimentacao;
    }


    /* 
     * Exemplo de como são enviado os dados. 
    */

    /*
     
    Lote(id=7, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba), vencimento=2025-05-05, quantidade=50, valorUnitario=5.05)
    
    Movimentacao(
        id=null, 
        local=Local(id=1, nome=CD.Principal), 
        usuario=Usuario(id=4, nome=admin, email=admin@gmail.com, senha=$2a$10$QkPjr9.Jj8KPL6cTF2PzA, perfil=GESTOR, cadastro=2024-04-10T10:28:12), 
        lote=Lote(id=7, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba), vencimento=2025-05-05, quantidade=50, valorUnitario=5.05),          
        motivo=Motivo(id=1, nome=-), 
        dataMovimentacao=2024-05-16, 
        quantidade=50, 
        valorUnitario=5.05, 
        tipoMovimentacao=Entrada)

     */
    























} // End.
