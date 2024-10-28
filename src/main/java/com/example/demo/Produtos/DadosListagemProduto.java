package com.example.demo.Produtos;

public record DadosListagemProduto(
        Long id,
        String nome,
        double preco,
        String descricao,
        Marca marca,
        String lote,
        String fornecedor
) {
    public DadosListagemProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(),
                produto.getMarca(), produto.getLote(), produto.getFornecedor());
    }
}
