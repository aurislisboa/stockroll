package br.com.usystem.stockroll.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

import br.com.usystem.stockroll.service.JasperService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class JasperController {
  
    @Autowired
    private JasperService service;

    @GetMapping("/relatorio")
    public String abrir() {
        return "relatorio";
    }

    @GetMapping("/relatorio/produto")
        public void exibirRelatorio01(HttpServletResponse response) throws IOException {

        byte[] bytes = service.exportarPDF();
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-disposition", "inline; filename=relatorio-produto.pdf" );
            response.getOutputStream().write(bytes);
    }










}
