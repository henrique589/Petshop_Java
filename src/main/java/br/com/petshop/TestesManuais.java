package br.com.petshop;

import br.com.petshop.model.*;
import br.com.petshop.repository.Repositorio;
import br.com.petshop.repository.RepositorioEmMemoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TestesManuais {

    public static void main(String[] args) {
        System.out.println("=== PET ===");

        // 1. Nome vazio
        try {
            new Pet("", Especie.GATO, Porte.PEQUENO);
            System.out.println("[FALHOU] 1. Aceitou pet com nome vazio");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 1. Nome vazio recusado: " + e.getMessage());
        }

        // 2. Nome null
        try {
            new Pet(null, Especie.GATO, Porte.PEQUENO);
            System.out.println("[FALHOU] 2. Aceitou pet com nome null");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 2. Nome null recusado: " + e.getMessage());
        }

        // 3. Espécie null
        try {
            new Pet("Mel", null, Porte.PEQUENO);
            System.out.println("[FALHOU] 3. Aceitou pet sem espécie");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 3. Espécie null recusada: " + e.getMessage());
        }

        // 4. Porte null
        try {
            new Pet("Mel", Especie.GATO, null);
            System.out.println("[FALHOU] 4. Aceitou pet sem porte");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 4. Porte null recusado: " + e.getMessage());
        }

        // 5. Alterar para um nome válido
        Pet mel = new Pet("Mel", Especie.GATO, Porte.PEQUENO);
        try {
            mel.alterarNome("Amora");
            System.out.println("[OK] 5. Nome alterado para: " + mel.getNome());
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 5. Recusou um nome válido: " + e.getMessage());
        }

        // 6. Alterar para nome vazio: o nome deve continuar "Amora"
        try {
            mel.alterarNome("");
            System.out.println("[FALHOU] 6. Aceitou alterar para nome vazio");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 6. Alteração recusada: " + e.getMessage()
                    + " | nome continua: " + mel.getNome());
        }

        System.out.println("\n=== TUTOR ===");

        // 7. Nome vazio
        try {
            new Tutor("", "Azevedo", "(31)99999-9999", "henrique@email.com");
            System.out.println("[FALHOU] 7. Aceitou tutor com nome vazio");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 7. Nome vazio recusado: " + e.getMessage());
        }

        // 8. Sobrenome null
        try {
            new Tutor("Henrique", null, "(31)99999-9999", "henrique@email.com");
            System.out.println("[FALHOU] 8. Aceitou tutor sem sobrenome");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 8. Sobrenome null recusado: " + e.getMessage());
        }

        // 9. Telefone com letras
        try {
            new Tutor("Henrique", "Azevedo", "abc", "henrique@email.com");
            System.out.println("[FALHOU] 9. Aceitou telefone 'abc'");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 9. Telefone inválido recusado: " + e.getMessage());
        }

        // 10. Telefone com parêntese aberto e não fechado
        try {
            new Tutor("Henrique", "Azevedo", "(31 99999-9999", "henrique@email.com");
            System.out.println("[FALHOU] 10. Aceitou telefone '(31 99999-9999'");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 10. Telefone malformado recusado: " + e.getMessage());
        }

        // 11. Email sem domínio
        try {
            new Tutor("Henrique", "Azevedo", "(31)99999-9999", "henrique@");
            System.out.println("[FALHOU] 11. Aceitou email 'henrique@'");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 11. Email inválido recusado: " + e.getMessage());
        }

        // 12. Email null
        try {
            new Tutor("Henrique", "Azevedo", "(31)99999-9999", null);
            System.out.println("[FALHOU] 12. Aceitou tutor sem email");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 12. Email null recusado: " + e.getMessage());
        }

        System.out.println("\n=== ADICIONAR PET ===");

        Tutor tutor = new Tutor("Henrique", "Azevedo", "(31)99999-9999", "henrique@email.com");
        Pet rex = new Pet("Rex", Especie.CACHORRO, Porte.GRANDE);

        // 13. Adicionar um pet válido
        try {
            tutor.adicionarPet(rex);
            System.out.println("[OK] 13. Pet adicionado: " + rex.getNome());
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 13. Recusou um pet válido: " + e.getMessage());
        }

        // 14. Adicionar null
        try {
            tutor.adicionarPet(null);
            System.out.println("[FALHOU] 14. Aceitou adicionar null");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 14. Null recusado: " + e.getMessage());
        }

        // 15. Adicionar o MESMO objeto duas vezes
        try {
            tutor.adicionarPet(rex);
            System.out.println("[FALHOU] 15. Aceitou o mesmo pet duas vezes");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 15. Pet duplicado recusado: " + e.getMessage());
        }

        // 16. Outro pet (objeto diferente) com o mesmo nome
        // O resultado depende da sua decisão sobre a regra de nome repetido
        Pet outroRex = new Pet("Rex", Especie.CACHORRO, Porte.PEQUENO);
        try {
            tutor.adicionarPet(outroRex);
            System.out.println("[INFO] 16. Aceitou outro pet chamado Rex");
        } catch (IllegalArgumentException e) {
            System.out.println("[INFO] 16. Recusou outro pet chamado Rex: " + e.getMessage());
        }

        System.out.println("\n=== PROTEÇÃO DA LISTA ===");

        // 17. Adicionar direto na lista, sem passar pelo adicionarPet
        try {
            tutor.getPets().add(null);
            System.out.println("[FALHOU] 17. Conseguiu adicionar null direto na lista");
        } catch (UnsupportedOperationException e) {
            System.out.println("[OK] 17. Lista protegida contra add");
        }

        // 18. Apagar a lista por fora
        try {
            tutor.getPets().clear();
            System.out.println("[FALHOU] 18. Conseguiu apagar a lista por fora");
        } catch (UnsupportedOperationException e) {
            System.out.println("[OK] 18. Lista protegida contra clear");
        }

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println(tutor);
        System.out.println("\n=== SERVIÇOS ===");

        List<Servico> servicos = List.of(new Banho(), new Tosa(), new Consulta("Dra. Ana Laura"));
        // 19. Criar lista com os 3 serviços
        try {
            for (Servico s : servicos) {
                System.out.println(s + "\nValor do serviço: R$ " + s.calcularPreco(mel));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 19. Criação dos serviços: " + e.getMessage());
        }

        // 20. Criar lista com os 3 serviços e pet null

        for (Servico s : servicos) {
            try {
                s.calcularPreco(null);
                System.out.println("[FALHOU] 20. " + s.getNome() + " aceitou pet null");
            } catch (IllegalArgumentException e) {
                System.out.println("[OK] 20. " + s.getNome() + " recusou pet null: " + e.getMessage());
            }
        }

        System.out.println("\n=== AGENDAMENTO ===");

        // 21. Criar agendamento
        Consulta consulta = new Consulta("Dra. Ana");
        Agendamento agendamento = new Agendamento(rex, consulta, LocalDateTime.now().plusDays(1));
        if ((agendamento.getStatus() == StatusAgendamento.AGENDADO) && agendamento.getValor().compareTo(new BigDecimal("150")) == 0) {
            System.out.println("[OK] 21. " + agendamento.getStatus() + " " + agendamento.getValor());
        } else {
            System.out.println("[FALHOU] 21. Erro na criação do agendamento ");
        }

        // 22. Criar agendamento com data passada
        try {
            Banho banho = new Banho();
            Agendamento agendamentoPassado = new Agendamento(rex, banho, LocalDateTime.now().minusDays(1));
            System.out.println("[FALHOU] 22. Criou agendamento com data passada. " + agendamentoPassado);
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 22. Pegou erro na criação do agendamento com data passada " + e.getMessage());
        }

        // 23. Concluir agendamento
        try {
            agendamento.concluir();
            if (agendamento.getStatus() == StatusAgendamento.CONCLUIDO) {
                System.out.println("[OK] 23. Agendamento concluído " + agendamento);
            } else {
                System.out.println("[FALHOU] 23. Erro na conclusão do agendamento");
            }
        } catch (IllegalStateException e) {
            System.out.println("[FALHOU] 23. " + e.getMessage());
        }

        // 24. Cancelar agendamento concluído
        Agendamento concluido = new Agendamento(rex, consulta, LocalDateTime.now().plusDays(1));
        concluido.concluir();

        try {
            concluido.cancelar();      // só a ação testada
            System.out.println("[FALHOU] 24. Cancelou um agendamento já concluído!");
        } catch (IllegalStateException e) {
            System.out.println("[OK] 24. Não permitiu cancelar um agendamento já concluído!");
        }

        System.out.println("\n=== REPOSITÓRIO ===");

        Repositorio<Pet> pets = new RepositorioEmMemoria<>();
        // 25. Teste salvar 2 pets
        try {
            pets.salvar(rex);
            pets.salvar(mel);
            if (Objects.equals(rex.getId(), 1L) && Objects.equals(mel.getId(), 2L)) {
                System.out.println("[OK] 25. Ids atribuídos: Rex=1, Mel=2");
            } else {
                System.out.println("[FALHOU] 25. Ids errados: Rex=" + rex.getId() + ", Mel=" + mel.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 25. " + e.getMessage());
        }

        // 26. Salvando pet null
        try {
            pets.salvar(null);
            System.out.println("[FALHOU] 26. Salvou pet null! " + pets.listarTodos());
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 26. Não permitiu salvar pet null! " + e.getMessage());
        }

        // 27. Buscando o 'id' 1
        Optional<Pet> petId1 = pets.buscarPorId(rex.getId());
        if (petId1.isPresent() && petId1.get() == rex) {
            System.out.println("[OK] 27. Encontrou o Rex pelo id " + rex.getId());
        } else {
            System.out.println("[FALHOU] 27. Não encontrou o Rex");
        }

        // 28. Buscando o 'id' 99
        Optional<Pet> petId99 = pets.buscarPorId(99L);
        if (petId99.isEmpty()) {
            System.out.println("[OK] 28. Não encontrou 'id' não cadastrado");
        } else {
            System.out.println("[FALHOU] 28. Encontrou 'id' não cadastrado");
        }

        // 29. Removendo 'id' = 1
        try {
            pets.remover(rex.getId());
            if (pets.listarTodos().size() == 1 && pets.listarTodos().contains(mel)) {
                System.out.println("[OK] 29. Removeu pet 'id'=1 e só sobrou um elemento na lista");
            } else {
                System.out.println("[FALHOU] 29. Não removeu elemento de 'id'=1");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 29. " + e.getMessage());
        }

        // 30. Salvar o mesmo pet duas vezes
        Long idAntes = mel.getId();
        int tamanhoAntes = pets.listarTodos().size();
        try {
            pets.salvar(mel);
            if (Objects.equals(mel.getId(), idAntes) && pets.listarTodos().size() == tamanhoAntes) {
                System.out.println("[OK] 30. Salvar de novo manteve o id " + idAntes + " e não duplicou");
            } else {
                System.out.println("[FALHOU] 30. Id: " + mel.getId() + ", tamanho: " + pets.listarTodos().size());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 30. " + e.getMessage());
        }

        // 31. Remover um 'id' inexistente na base
        try {
            pets.remover(99L);
            System.out.println("[FALHOU] 31. Deixou remover 'id' inexistente");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 31. Pegou erro de remover 'id' inexistente " + e.getMessage());
        }

        // 32. Tentar adicionar elemento na lista
        try {
            pets.listarTodos().add(mel);
            System.out.println("[FALHOU] 32. Permitiu adicionar elemento na lista");
        } catch (UnsupportedOperationException e) {
            System.out.println("[OK] 32. Pegou erro de tentar adicionar elemento de forma 'ilegal'");
        }

        Repositorio<Tutor> tutores = new RepositorioEmMemoria<>();
        tutores.salvar(tutor);

        System.out.println(agendamento);
        System.out.println(tutor);
        System.out.println(pets.listarTodos());
    }
}
