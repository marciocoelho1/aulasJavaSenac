package br.com.senac.loja.api;

import br.com.senac.loja.service.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(
        basePackages = "br.com.senac.loja.controller.api"
)

public class ApiExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ProblemDetail tratarNaoEncontrado(
            RegistroNaoEncontradoException exception
    ){
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());

        problema.setTitle("Registro não encontrado");
        return problema;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ProblemDetail tratarValidacao(
                MethodArgumentNotValidException exception ){

            Map<String, String> erros = new LinkedHashMap<>();
            exception.getBindingResult().getFieldErrors().forEach(erro -> erros.put(erro.getField(), erro.getDefaultMessage()));

            ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Existem campos inválidos");

            problema.setTitle("Erro de validação");
            problema.setProperty("erros", erros);
            return problema;
        }
    }