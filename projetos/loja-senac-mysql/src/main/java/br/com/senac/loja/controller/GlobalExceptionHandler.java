package br.com.senac.loja.controller;

import br.com.senac.loja.service.RegistroNaoEncontradoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public String tratarRegistroNaoEncontrado(
            RegistroNaoEncontradoException exception,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("erro", exception.getMessage());
        return "redirect:/produtos";
    }
}
