package br.com.usystem.stockroll.controller;

import java.security.Principal;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.repository.ProdutoRepository;
import br.com.usystem.stockroll.service.EstoqueService;
import br.com.usystem.stockroll.service.LoteService;
import br.com.usystem.stockroll.service.MovimentacaoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/lote")
public class LoteController {
    
    
    private ProdutoRepository produtoRepository;
    private MovimentacaoService movimentacaoService;
    private LoteService loteService;
    private EstoqueService estoqueService;
    // private EstoqueMapper mapper;
    

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("lote/listar");
            modelAndView.addObject("estoques", estoqueService.listaLotesDoCdPrincipal());
        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("lote/detalhar");
        //modelAndView.addObject("estoque", estoqueService.buscarLoteDoCdPrincipalPorId(id));   //(remover)
        modelAndView.addObject("lote", loteService.buscarLotePorId(id));
        return modelAndView;
    }



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("lote/formulario");
        
        modelAndView.addObject("lote", new Lote());
        modelAndView.addObject("produtos", produtoRepository.findAll(Sort.by("nome")));
        modelAndView.addObject("movimentacao", new Movimentacao());

        return modelAndView;
    }

    
    // @PostMapping("cadastrar")
    // public String cadastrar(Lote lote) {

    //     loteRepository.save(lote);
    //     return "redirect:/lote";
    // }



    @PostMapping("cadastrar")
    public String cadastrar(Lote lote, Principal principal) {   // Recebe os dados do lote e o Usuário logado
        Lote loteNovo = loteService.salvarLote(lote);           // salva o Lote e devolve o objeto cadastrado.
        movimentacaoService.cadastrarMovimentacao(loteNovo, principal);    
        return "redirect:/lote";
    }


    
  @GetMapping("editar/{id}")
  public ModelAndView editar(@PathVariable Integer id) {
    var modelAndView = new ModelAndView("/lote/editar");
        //modelAndView.addObject("estoque", estoqueRepository.findByLocalAndLoteId(localId, loteId));
        modelAndView.addObject("produtos", produtoRepository.findAll());        
        modelAndView.addObject("lote", loteService.buscarLotePorId(id));        
 
        return modelAndView;
    }


    @PostMapping("editar/{id}")
    public String editar(Lote lote) { 

        loteService.salvarLote(lote);
        
        // System.out.println("\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n\n"+ estoque);
        // EstoqueId estoqueId = new EstoqueId(local, lote);
        // Estoque estoque = new Estoque(estoqueId, 300);
        // estoqueService.salvarEstoque(estoque);
        //movimentacaoService.editarQuantidadeCdPrincipal(estoque);
        
        return "redirect:/lote";
    }



    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        loteService.excluirLotePorId(id);
        
        return "redirect:/lote";
    }



}
