package br.com.senac.loja.controller;

import br.com.senac.loja.model.Categoria;
import br.com.senac.loja.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscar(id));
        return "categorias/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("categoria") Categoria categoria,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "categorias/formulario";
        }

        try {
            boolean nova = categoria.getId() == null;
            categoriaService.salvar(categoria);
            redirectAttributes.addFlashAttribute(
                "sucesso",
                nova
                    ? "Categoria cadastrada com sucesso."
                    : "Categoria atualizada com sucesso."
            );
            return "redirect:/categorias";
        } catch (IllegalArgumentException exception) {
            bindingResult.rejectValue("nome", "categoria.duplicada", exception.getMessage());
            return "categorias/formulario";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id,
                          RedirectAttributes redirectAttributes) {
        try {
            categoriaService.excluir(id);
            redirectAttributes.addFlashAttribute(
                "sucesso",
                "Categoria excluída com sucesso."
            );
        } catch (IllegalStateException exception) {
            redirectAttributes.addFlashAttribute("erro", exception.getMessage());
        }

        return "redirect:/categorias";
    }
}
