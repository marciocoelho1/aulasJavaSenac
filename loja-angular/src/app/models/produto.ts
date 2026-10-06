export interface Categoria{
    id: number;
    nome: string;
    descricao: string;

}

export interface Produto {
    id: number;
    nome: string;
    descricao: string;
    preco: number;
    quantidade: number;
    categoria: Categoria;
}

export interface ProdutoRequest{

    nome: string;
    descricao: string;
    preco: number;
    quantidade: number;
    categoriaId: number;

}