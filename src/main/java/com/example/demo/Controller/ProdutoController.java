package com.example.demo.Controller;

import com.example.demo.Produtos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriComponentsBuilder){
        var produto = new Produto(dados);
        repository.save(produto);

        var uri = uriComponentsBuilder.path("/remedios/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity <List<DadosListagemProduto>> listar(){
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemProduto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listarid(@PathVariable Long id){
        Optional<Produto> produto = repository.findById(id);
        if (produto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(produto.get());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(@RequestBody @Valid DadosAtualizarProduto dados){
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        produto.ativar();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        produto.inativar();

        return ResponseEntity.noContent().build();
    }

}
