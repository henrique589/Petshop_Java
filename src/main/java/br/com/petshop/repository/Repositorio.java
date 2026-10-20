package br.com.petshop.repository;

import br.com.petshop.model.Entidade;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T extends Entidade>{
    public T salvar(T entidade);
    Optional<T> buscarPorId(Long id);
    List<T> listarTodos();
    void remover(Long id);
}
