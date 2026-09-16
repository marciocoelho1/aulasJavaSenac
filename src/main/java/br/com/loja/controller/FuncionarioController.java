package br.com.loja.controller;

import br.com.loja.model.Funcionario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FuncionarioController {

    @GetMapping("/funcionarios")
    public String listar(Model model) {
        Funcionario funcionario1 = new Funcionario(1, "Carlos Silva", "Desenvolvedor", "2 anos", "A vencer");
        Funcionario funcionario2 = new Funcionario(2, "Ana Souza", "Gerente de Projetos", "5 anos", "Pagas");
        Funcionario funcionario3 = new Funcionario(3, "João Pedro", "Analista de Suporte", "1 ano", "A vencer");
        Funcionario funcionario4 = new Funcionario(4, "Mariana Oliveira", "Designer UI/UX", "3 anos", "Pagas");

        List<Funcionario> funcionarios = List.of(funcionario1, funcionario2, funcionario3, funcionario4);

        model.addAttribute("funcionarios", funcionarios);

        return "funcionarios";
    }
}
