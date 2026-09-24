package br.com.loja.controller;

import br.com.loja.model.Produto;
import br.com.loja.repository.CategoriaRepository;
import br.com.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository){
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute(
                "produtos",
                produtoRepository.findAll()
        );
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String abrirFormulario(Model model){
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto){
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }
}
