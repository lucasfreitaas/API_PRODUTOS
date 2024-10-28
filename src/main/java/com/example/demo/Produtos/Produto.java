package com.example.demo.Produtos;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "Produto")
@Entity(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Produto(DadosCadastroProduto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
        this.marca = dados.marca();
        this.lote = dados.lote();
        this.fornecedor = dados.fornecedor();
    }

    private String nome;
    private double preco;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Marca marca;
    private String lote;
    private String fornecedor;
    private boolean ativo;

    public void atualizarInformacoes(@Valid DadosAtualizarProduto dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.preco() > 0){
            this.preco = dados.preco();
        }
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if (dados.lote() != null){
            this.lote = dados.lote();
        }
        if(dados.marca() != null){
            this.marca = dados.marca();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
