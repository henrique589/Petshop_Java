package br.com.petshop.model;

import java.math.BigDecimal;

public class Banho extends Servico {
    private static final BigDecimal PRECO_PEQUENO = new BigDecimal("50.00");
    private static final BigDecimal PRECO_MEDIO = new BigDecimal("70.00");
    private static final BigDecimal PRECO_GRANDE = new BigDecimal("90.00");

    public Banho() {
        super("Banho");
    }

    @Override
    protected BigDecimal calcularValor(Pet pet) {
        return switch (pet.getPorte()) {
            case PEQUENO -> PRECO_PEQUENO;
            case MEDIO -> PRECO_MEDIO;
            case GRANDE -> PRECO_GRANDE;
        };
    }

}
