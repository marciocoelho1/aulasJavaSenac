import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Categoria, Produto, ProdutoRequest } from '../models/produto';

@Injectable({
    providedIn: 'root'
})

export class ProdutoService {

    private readonly http = inject(HttpClient);

    private readonly apiProdutos = 'http://localhost:8080/api/produtos';

    listar(): Observable <Produto[]>{
        return this.http.get<Produto[]>(this.apiProdutos);
    }

    cadastrar(produto: ProdutoRequest): Observable<Produto> {
        return this.http.post<Produto>(this.apiProdutos, produto);
    }

    atualizar(id: number, produto: ProdutoRequest): Observable<Produto>{
        return this.http.put<Produto>('this.apiProdutos/${id}', produto);
    }

    excluir(id: number): Observable<void>{
        return this.http.delete<void>('${this.apiProdutos}/${id}');
    }
}
