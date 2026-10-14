package br.com.petshop.model;

import java.math.BigDecimal;

public abstract class Servico {
    private final String nome;

    protected Servico(String nome) {
        validarObrigatorio(nome, "nome do serviço");
        this.nome = nome;
    }

    protected void validarObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório!");
        }
    }

    private void validarPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet inválido! ");
        }
    }

    // Aplicação do TEMPLATE METHOD, a classe abstrata controla o fluxo
    // e as classes filhas redefinem ou customizam passos específicos.
    public final BigDecimal calcularPreco(Pet pet) {
        validarPet(pet);
        return calcularValor(pet);
    }

    protected abstract BigDecimal calcularValor(Pet pet);

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
