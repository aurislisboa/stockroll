package br.com.usystem.stockroll.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;

import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.service.JasperService;
import br.com.usystem.stockroll.service.LocalService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
// incluir o /relatorio aqui!
public class JasperController {
  
    @Autowired
    private JasperService service;
    @Autowired
    private LocalService localService;



    @ModelAttribute("locais")
    public List<Local> getLocais() {
        return localService.listarTodosLocais();
    }



    @GetMapping("/relatorio")
    public String abrir() {
        return "relatorio";
    }


    @GetMapping("/relatorio/produto")
    public void exibirRelatorio01(HttpServletResponse response) throws IOException {

        byte[] bytes = service.exportarPDF("produtos");
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-disposition", "inline; filename=relatorio-produto.pdf" );
            response.getOutputStream().write(bytes);
    }



    @GetMapping("/relatorio/estoque")
    public void exibirRelatorioEstoque(@RequestParam(name = "local", required = false) Integer local, HttpServletResponse response) throws IOException {

        service.addParams("ID_LOCAL", local); 

        byte[] bytes = service.exportarPDF("estoque");
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-disposition", "inline; filename=relatorio-estoque.pdf" );
            response.getOutputStream().write(bytes);
    }








}
