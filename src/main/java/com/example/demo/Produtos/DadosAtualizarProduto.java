package com.example.demo.Produtos;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
        @NotNull
        Long id,
        String nome,
        double preco,
        String descricao,
        String lote,
        Marca marca
) {
}
