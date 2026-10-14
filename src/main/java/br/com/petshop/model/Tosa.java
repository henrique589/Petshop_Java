package br.com.petshop.model;

import java.math.BigDecimal;

public class Tosa extends Servico {
    private static final BigDecimal PRECO_BASE = new BigDecimal("60.00");
    private static final BigDecimal ADICIONAL_GRANDE = new BigDecimal("30.00");

    public Tosa() {
        super("Tosa");
    }

    private BigDecimal adicionalPorPorte(Pet pet) {
        if (pet.getPorte() == Porte.GRANDE) {
            return ADICIONAL_GRANDE;
        }
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal calcularValor(Pet pet) {
        return PRECO_BASE.add(adicionalPorPorte(pet));
    }
}
