package com.example.demo.Produtos;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        @NotNull
        double preco,
        String descricao,
        @Enumerated
        Marca marca,
        @NotBlank
        String lote,
        String fornecedor,
        boolean ativo
) {


}
