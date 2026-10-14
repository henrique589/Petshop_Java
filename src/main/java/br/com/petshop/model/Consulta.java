package br.com.petshop.model;

import java.math.BigDecimal;

public class Consulta extends Servico {
    private static final BigDecimal PRECO_BASE = new BigDecimal("150.00");
    private String nomeVeterinario;

    public Consulta(String nomeVeterinario) {
        super("Consulta");
        validarObrigatorio(nomeVeterinario, "nome do veterinário");
        this.nomeVeterinario = nomeVeterinario;
    }

    @Override
    protected BigDecimal calcularValor(Pet pet) {
        return PRECO_BASE;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void alterarVeterinario(String nome) {
        validarObrigatorio(nome, "nome do veterinário");
        this.nomeVeterinario = nome;
    }

    @Override
    public String toString() {
        return "Servico{nome='" + getNome() + "', veterinário='" + nomeVeterinario + "'}";
    }
}
