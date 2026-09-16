package br.com.loja.controller;

import br.com.loja.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @GetMapping("/api/produtos")  //Endpoint
    public String produtos(){

        return "Aqui serão exibidos produtos";
    }
}
