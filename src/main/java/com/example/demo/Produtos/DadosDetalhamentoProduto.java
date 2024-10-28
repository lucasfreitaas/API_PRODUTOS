package com.example.demo.Produtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DadosDetalhamentoProduto(
        Long id,
        String nome,
        double preco,
        String descricao,
        @Enumerated(EnumType.STRING)
        Marca marca,
        String lote,
        String fornecedor,
        boolean ativo
) {
    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(),
                produto.getMarca(), produto.getLote(), produto.getFornecedor(), produto.isAtivo());
    }
}
