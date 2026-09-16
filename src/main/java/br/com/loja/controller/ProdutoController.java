package br.com.loja.controller;

import br.com.loja.dao.ProdutoDao;
import br.com.loja.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoDao produtoDao;

    public ProdutoController(ProdutoDao produtoDao){
        this.produtoDao = produtoDao;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(
            @RequestBody Produto produto){

        if(produto.getNome()== null || produto.getNome().isBlank() || produto.getNome().trim().length() > 100){
            return ResponseEntity.badRequest().body("Informe um nome com até 100 caracteres.");
        }

        BigDecimal preco = produto.getPreco();

        if(preco.signum() <= 0 || preco.compareTo(new BigDecimal("99999999.99")) > 0 || preco.stripTrailingZeros().scale() > 2 ){
            return ResponseEntity.badRequest().body("Informe um preço positivo com até duas casas decimais");
        }
        produto.setNome(produto.getNome().trim());
        produtoDao.inserir(produto);
        return ResponseEntity.ok().body("Produto cadastrado com sucesso");
    }

}
