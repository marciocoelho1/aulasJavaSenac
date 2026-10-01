package br.com.senac.loja.api;

import br.com.senac.loja.dto.ProdutoRequest;
import br.com.senac.loja.dto.ProdutoResponse;
import br.com.senac.loja.form.ProdutoForm;
import br.com.senac.loja.model.Produto;
import br.com.senac.loja.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoResponse> list(){
        return produtoService.listar().stream().map(ProdutoResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id){
        return ProdutoResponse.from(produtoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(
            @Valid @RequestBody ProdutoRequest request){

        Produto produto = produtoService.salvar(converterParaForm(null, request));
        ProdutoResponse response = ProdutoResponse.from(produto);

        return ResponseEntity.created(URI.create("/api/produtos/" + produto.getId())).body(response);
    }

    private ProdutoForm converterParaForm (Long id, ProdutoRequest produtoRequest){
        ProdutoForm form = new ProdutoForm();
        form.setId(id);
        form.setNome(produtoRequest.nome());
        form.setDescricao(produtoRequest.descricao());
        form.setPreco(produtoRequest.preco());
        form.setQuantidade(produtoRequest.quantidade());
        form.setCategoriaId(produtoRequest.categoriaId());
        return form;
    }
}
