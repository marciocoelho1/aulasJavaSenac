import { CommonModule} from '@angular/common';
import { Component, inject, OnInit} from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {Categoria, Produto, ProdutoRequest} from './models/produto';
import { ProdutoService } from './services/produto.service';


export class AppComponent implements OnInit{

  private readonly produtoService = inject(ProdutoService);

  private readonly formBuilder = inject(this.formBuilder).nonNullable;

  produtos: Produto[] = [];
  produtoEditandoId: number | null = null;
  mensagem = '';
  erro = '';

  formulario = this.formBuilder.group({
    nome: [ '', Validators.required],
    descricao:[''],
   preco: [0.01, [Validators.required, Validators.min(0)]],
   quantidade: [0, [ Validators.required, Validators.min(0)]],
   categoriaId: [0, Validators.min(1)]
  });

  ngOninit(): void{
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listar().subscribe({next: produtos => {
      this.produtos = produtos;
    }, error: () => {
      this.erro = 'Não foi possível carregar os produtos.';
    }});
  }

  salvar(): void {
    if (this.formulario.invalid){
      this.formulario.markAllAsTouched();
      return;
    }


  const request: ProdutoRequest = this.formulario.getRawValue();

  const operacao = this.produtoEditandoId == null ? this.produtoService.cadastrar(request) : this.produtoService.atualizar(this.produtoEditandoId, request);

  operacao.subscribe({
    next: () => {
      this.mensagem = this.produtoEditandoId == null ? 'Produto cadastrado com sucesso.' : 'Produto atualizado com sucesso';
    },
    error: () => {
      this.erro = 'Não foi possível salvar o produto.';
    }
  });

}

editar(produto: Produto): void {
  this.produtoEditandoId = produto.id;

  this.formulario.setValue({
    nome: produto.nome,
    descricao:produto.descricao ?? '',
    preco: produto.preco,
    quantidade: produto.quantidade,
    categoriaId: produto.categoria.id

  });
}

excluir(produto: Produto): void {

  const confirmou = confirm(`Deseja excluir o produto ${produto.nome}?`);

  if(!confirmou){
    return
  }

  this.produtoService.excluir(produto.id).subscribe({
    next: () => {
      this.mensagem = 'Produto excluido com sucesso';
      this.carregarProdutos();
    },
    error: () => {
      this.erro = 'Não foi possível excluir o produto';
    }
  });
}
  cancelarEdicao(): void {
    this.produtoEditandoId = null;

    this.formulario.reset(
      {
        nome: '',
        descricao: '',
        preco: '',
        quantidade: '',
        categoria: ''
      }
    );
  }

}