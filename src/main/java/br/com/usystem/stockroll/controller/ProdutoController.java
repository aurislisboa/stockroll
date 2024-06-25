package br.com.usystem.stockroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.dto.ProdutoForm;
import br.com.usystem.stockroll.mappers.ProdutoMapper;
import br.com.usystem.stockroll.model.Produto;
import br.com.usystem.stockroll.repository.ProdutoRepository;
import br.com.usystem.stockroll.service.QtdPorLocalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private QtdPorLocalService qtdPorLocalService;

    @Autowired
    private ProdutoMapper mapper;



    public Page<Produto> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.produtoRepository.findAll(pageable);
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;   
        Page<Produto> page = findPaginated(pageNo, pageSize);
        List<Produto> produtos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("produtos", produtos);

        return "/produto/listar";
    }


    @GetMapping
    public String listar(Model model) {
        
        return findPaginated(1, model);
    }


    // @GetMapping
    // public ModelAndView listar() {
    //     ModelAndView modelAndView = new ModelAndView("/produto/listar.html");

    //     List<Produto> produtos = produtoRepository.findAll();
    //     modelAndView.addObject("produtos", produtos);
    //     return modelAndView;
    // }




    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("/produto/detalhar.html");

        Produto produto = produtoRepository.getReferenceById(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("produto/formulario");
        modelAndView.addObject("form", new ProdutoForm());

        return modelAndView;
    }


    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ProdutoForm form, BindingResult result) {

        if (result.hasErrors()) {                                      // se houver erro no preenchimento do formulário.
            return "produto/formulario";                               // retorna para o mesmo formulário.
        } 
                    
        var produto = mapper.toModel(form);                            // converte o formulário para produto.
        Produto produtoSalvo = produtoRepository.save(produto);
        qtdPorLocalService.adicionaQuantidaeMinimaEIdeal(produtoSalvo);        

        return "redirect:/lote/cadastrar";
    }


  

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("produto/formulario");

        Produto produto = produtoRepository.getReferenceById(id);
        var form = mapper.toForm(produto);
        modelAndView.addObject("form", form);

        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editar(@Valid @ModelAttribute("form") ProdutoForm form, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produto");
        if (result.hasErrors()) {
            modelAndView.setViewName("produto/formulario");                     // outra maneira de redirecionar.
            return modelAndView;
        }            
        
        var produto = mapper.toModel(form);
            // produto.setId(id);
        produtoRepository.save(produto);

        return modelAndView;
    }



    @GetMapping("/excluir/{id}")
    public ModelAndView exluir(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produto");

        produtoRepository.deleteById(id);
        return modelAndView;
    }

}
