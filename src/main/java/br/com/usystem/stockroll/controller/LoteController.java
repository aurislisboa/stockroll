package br.com.usystem.stockroll.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.usystem.stockroll.dto.AlertDTO;
import br.com.usystem.stockroll.dto.LoteForm;
import br.com.usystem.stockroll.mappers.LoteMapper;
import br.com.usystem.stockroll.mappers.ProdutoMapper;
import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.model.Produto;
import br.com.usystem.stockroll.repository.ProdutoRepository;
import br.com.usystem.stockroll.service.EstoqueService;
import br.com.usystem.stockroll.service.LoteService;
import br.com.usystem.stockroll.service.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/lote")
public class LoteController {
    
    
    private ProdutoRepository produtoRepository;
    private MovimentacaoService movimentacaoService;
    private LoteService loteService;
    private EstoqueService estoqueService;
    private LoteMapper mapper;


    @ModelAttribute("produtos")
    public List<Produto> getProdutos() {
        return produtoRepository.findAll(Sort.by("nome"));     // popular a select
    }
    

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




    @GetMapping("/consulta")
    public String consulta() {
        return "lote/consulta";
    }



    @PostMapping("/barcode")
    public ModelAndView consulta(@RequestParam("codigoBarra") String codigoBarra, Model model, RedirectAttributes attr) {
        var produto = produtoRepository.findByCodigoBarra(codigoBarra);
        
        if (produto == null) {
            AlertDTO alert = new AlertDTO("Produto não foi localizado!", "error");
            attr.addFlashAttribute("alert", alert);
            ModelAndView modelAndView = new ModelAndView("redirect:/lote/consulta");
            return modelAndView;
        }
        

        var modelAndView = new ModelAndView("lote/formulario");
        var form = new LoteForm();
            form.setProduto(produto);
        modelAndView.addObject("form", form);

        return modelAndView;
    }


    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("lote/formulario");
        
        modelAndView.addObject("form", new LoteForm());
        // modelAndView.addObject("movimentacao", new Movimentacao());

        return modelAndView;
    }


    @PostMapping("cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") LoteForm form, BindingResult result, Principal principal, RedirectAttributes attr) {   // Recebe os dados do lote e o Usuário logado

        if(result.hasErrors()) return "lote/formulario";                // confere se há erros de validação do formulário.

        var lote = mapper.toModel(form);                                // converte de form para Lote
        Lote loteNovo = loteService.salvarLote(lote);                   // salva o Lote e devolve o objeto cadastrado.
        movimentacaoService.cadastrarMovimentacao(loteNovo, principal);   
        
        AlertDTO alert = new AlertDTO("Lote cadastrado com sucesso!", "success");
        attr.addFlashAttribute("alert", alert);
        
        return "redirect:/lote";
    }


    
  @GetMapping("editar/{id}")
  public ModelAndView editar(@PathVariable Integer id) {
    var modelAndView = new ModelAndView("lote/editar");
        //modelAndView.addObject("estoque", estoqueRepository.findByLocalAndLoteId(localId, loteId));
        Lote lote = loteService.buscarLotePorId(id);
        var form = mapper.toForm(lote);
        modelAndView.addObject("form", form);        
 
        return modelAndView;
    }


    @PostMapping("editar/{id}")
    public String editar(@Valid @ModelAttribute("form") LoteForm form, BindingResult result) { 

        if(result.hasErrors()) return "lote/editar";        // se houver erro no formulário retorna para view editar.
        
        Lote lote = mapper.toModel(form);
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
