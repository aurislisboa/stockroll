package br.com.usystem.stockroll.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.repository.ProdutoRepository;
import br.com.usystem.stockroll.service.LoteService;
import br.com.usystem.stockroll.service.MovimentacaoService;

@Controller
@RequestMapping("/lote")
public class LoteController {
    
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;
    
    @Autowired
    private LoteService loteService;



    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("lote/listar");
            modelAndView.addObject("lotes", loteService.buscarTodosLotes());

        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("lote/detalhar");

        modelAndView.addObject("lote", loteService.buscarLotePorId(id));
        return modelAndView;
    }



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("lote/formulario");
        
        modelAndView.addObject("lote", new Lote());
        modelAndView.addObject("produtos", produtoRepository.findAll(Sort.by("nome")));
        return modelAndView;
    }

    
    /* Action Original para Cadastrar um Produto no Lote */

    // @PostMapping("cadastrar")
    // public String cadastrar(Lote lote) {

    //     loteRepository.save(lote);
    //     return "redirect:/lote";
    // }



    @PostMapping("cadastrar")
    public String cadastrar(Lote lote, Principal principal) {   // Recebe os dados do lote e o Usuário logado

            movimentacaoService.salvarMovimentacao(lote, principal);    
        
        return "redirect:/lote";
    }


    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("lote/formulario");
        
        modelAndView.addObject("lote", loteService.buscarLotePorId(id));
        modelAndView.addObject("produtos", produtoRepository.findAll(Sort.by("nome")));
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(Lote lote) {
        loteService.salvarLote(lote);
        
        return "redirect:/lote";
    }



    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        loteService.excluirLotePorId(id);
        
        return "redirect:/lote";
    }



}
