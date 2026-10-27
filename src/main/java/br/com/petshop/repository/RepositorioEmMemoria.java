package br.com.petshop.repository;

import br.com.petshop.model.Entidade;

import java.util.*;

public class RepositorioEmMemoria<T extends Entidade> implements Repositorio<T> {
    private Long proximoId;
    private final Map<Long, T> dados;

    public RepositorioEmMemoria() {
        this.proximoId = 1L;
        this.dados = new LinkedHashMap<>();
    }

    @Override
    public T salvar(T entidade) {
        validarObrigatorio(entidade);
        if (entidade.getId() == null) {
            entidade.setId(proximoId++);
        } else if (!dados.containsKey(entidade.getId())) {
            throw new IllegalArgumentException("Não existe registro com id " + entidade.getId());
        }
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<T> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return List.copyOf(dados.values());
    }

    @Override
    public void remover(Long id) {
        if (!dados.containsKey(id)) {
            throw new IllegalArgumentException("Não é possível remover um 'id' não cadastrado!");
        }
        dados.remove(id);
    }

    private void validarObrigatorio(T entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Objeto inválido!");
        }
    }

}


