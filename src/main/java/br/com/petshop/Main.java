package br.com.petshop;

import br.com.petshop.model.Especie;
import br.com.petshop.model.Pet;
import br.com.petshop.model.Porte;

public class Main {
    public static void main(String[] args) {
        try {
            Pet pet1 = new Pet("Zico", Especie.CACHORRO, Porte.MEDIO);
            System.out.println(pet1);
            pet1.alterarNome("");
            System.out.println(pet1);
        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }

        Pet pet2 = new Pet("Tequila", Especie.CACHORRO, Porte.PEQUENO);
        System.out.println(pet2);

    }
}
