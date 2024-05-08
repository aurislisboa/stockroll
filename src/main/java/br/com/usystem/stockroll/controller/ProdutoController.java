// package br.com.usystem.stockroll.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.ModelAndView;

// import br.com.usystem.stockroll.models.Produto;
// import br.com.usystem.stockroll.repositories.ProdutoRepository;


// import java.util.List;

// @Controller
// @RequestMapping("/produto")
// public class ProdutoController {

//     @Autowired
//     private ProdutoRepository produtoRepository;



//     public Page<Produto> findPaginated(int pageNo, int pageSize) {
//         Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

//         return this.produtoRepository.findAll(pageable);
//     }


//     @GetMapping("/page/{pageNo}")
//     public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
//         int pageSize = 5;   
//         Page<Produto> page = findPaginated(pageNo, pageSize);
//         List<Produto> produtos = page.getContent();

//         model.addAttribute("currentPage", pageNo);
//         model.addAttribute("totalPages", page.getTotalPages());
//         model.addAttribute("totalItems", page.getTotalElements());
//         model.addAttribute("produtos", produtos);

//         return "/produto/listar";
//     }


//     @GetMapping
//     public String listar(Model model) {
        
//         return findPaginated(1, model);
//     }


//     // @GetMapping
//     // public ModelAndView listar() {
//     //     ModelAndView modelAndView = new ModelAndView("/produto/listar.html");

//     //     List<Produto> produtos = produtoRepository.findAll();
//     //     modelAndView.addObject("produtos", produtos);
//     //     return modelAndView;
//     // }




//     @GetMapping("/{id}")
//     public ModelAndView detalhar(@PathVariable Long id) {
//         ModelAndView modelAndView = new ModelAndView("/produto/detalhar.html");

//         Produto produto = produtoRepository.getReferenceById(id);
//         modelAndView.addObject("produto", produto);
//         return modelAndView;
//     }



//     @GetMapping("/cadastrar")
//     public ModelAndView cadastrar() {
//         ModelAndView modelAndView = new ModelAndView("produto/formulario");
//         modelAndView.addObject("produto", new Produto());

//         return modelAndView;
//     }


//     @PostMapping("/cadastrar")
//     public ModelAndView cadastrar(Produto produto) {
//         ModelAndView modelAndView = new ModelAndView("redirect:/produto");
       
//         System.out.printf("\n\n --------\n\n %s \n\n-------- \n\n", produto);

//         if (produto.getQtdAtualEstoque() == null) {
//             produto.setQtdAtualEstoque(0);
//         }

//         produtoRepository.save(produto);

//         return modelAndView;
//     }



//     @GetMapping("/{id}/editar")
//     public ModelAndView editar(@PathVariable Long id) {
//         ModelAndView modelAndView = new ModelAndView("produto/formulario");

//         Produto produto = produtoRepository.getReferenceById(id);
//         modelAndView.addObject("produto", produto);
//         return modelAndView;
//     }

//     @PostMapping("/{id}/editar")
//     public ModelAndView editar(Produto produto) {
//         ModelAndView modelAndView = new ModelAndView("redirect:/produto");
//         //System.out.println(produto);
//         produtoRepository.save(produto);
//         return modelAndView;
//     }



//     @GetMapping("/{id}/excluir")
//     public ModelAndView exluir(@PathVariable Long id) {
//         ModelAndView modelAndView = new ModelAndView("redirect:/produto");
//         produtoRepository.deleteById(id);

//         return modelAndView;
//     }



// }
