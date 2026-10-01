package br.com.senac.loja.controller;

import br.com.senac.loja.form.ProdutoForm;
import br.com.senac.loja.service.CategoriaService;
import br.com.senac.loja.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService,
                             CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listar());
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        prepararFormulario(model, new ProdutoForm());
        return "produtos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ProdutoForm form = ProdutoForm.from(produtoService.buscar(id));
        prepararFormulario(model, form);
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("produtoForm") ProdutoForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            prepararFormulario(model, form);
            return "produtos/formulario";
        }

        produtoService.salvar(form);
        redirectAttributes.addFlashAttribute(
            "sucesso",
            form.getId() == null
                ? "Produto cadastrado com sucesso."
                : "Produto atualizado com sucesso."
        );

        return "redirect:/produtos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id,
                          RedirectAttributes redirectAttributes) {
        produtoService.excluir(id);
        redirectAttributes.addFlashAttribute(
            "sucesso",
            "Produto excluído com sucesso."
        );
        return "redirect:/produtos";
    }

    private void prepararFormulario(Model model, ProdutoForm form) {
        model.addAttribute("produtoForm", form);
        model.addAttribute("categorias", categoriaService.listar());
    }
}
