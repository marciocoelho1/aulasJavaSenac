package br.com.senac.loja.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "Informe o nome do produto.")
        @Size(max = 100)
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull( message = "Informe o preço.")
        @DecimalMin(
                value = "0.01",
                message = "O preço deve ser maior que zero."
        )
        BigDecimal preco,

        @NotNull(message = "Informe a quantidade.")
        @Min(
                value = 0,
                message = "A quantidade não pode ser negativa."
        )
        Integer quantidade,

        @NotNull (message = "Informe a categoria")
        Long categoriaId

) {

}
