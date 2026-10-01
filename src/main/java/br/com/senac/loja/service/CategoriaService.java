package br.com.senac.loja.service;

import br.com.senac.loja.model.Categoria;
import br.com.senac.loja.repository.CategoriaRepository;
import br.com.senac.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria buscar(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RegistroNaoEncontradoException(
                "Categoria não encontrada."
            ));
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        categoria.setNome(categoria.getNome().trim());

        categoriaRepository.findByNomeIgnoreCase(categoria.getNome())
            .filter(encontrada -> !encontrada.getId().equals(categoria.getId()))
            .ifPresent(encontrada -> {
                throw new IllegalArgumentException(
                    "Já existe uma categoria com esse nome."
                );
            });

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void excluir(Long id) {
        buscar(id);

        if (produtoRepository.existsByCategoriaId(id)) {
            throw new IllegalStateException(
                "A categoria possui produtos vinculados e não pode ser excluída."
            );
        }

        categoriaRepository.deleteById(id);
    }
}
