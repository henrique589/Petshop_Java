package br.com.petshop;

import br.com.petshop.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
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

        try {
            Consulta consulta = new Consulta("Dra. Ana");
            Agendamento agendamento = new Agendamento(rex, consulta, LocalDateTime.now().plusDays(1));
            System.out.println("[OK] 21. " + agendamento.getStatus() + " " + agendamento.getValor());
        } catch (IllegalArgumentException e) {
            System.out.println("[FALHOU] 21. Erro na criação do agendamento " + e.getMessage());
        }

        // 22. Criar agendamento com data passada
        try {
            Banho banho = new Banho();
            Agendamento agendamentoPassado = new Agendamento(rex, banho, LocalDateTime.now().minusDays(1));
            System.out.println("[FALHOU] 22. Criou agendamento com data passada. " + agendamentoPassado);
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] 22. Pegou erro na criação do agendamento com data passada " + e.getMessage());
        }

        Consulta consulta = new Consulta("Dra. Ana");
        Agendamento agendamento = new Agendamento(rex, consulta, LocalDateTime.now().plusDays(1));

        // 23. Concluir agendamento
        try {
            agendamento.concluir();
            System.out.println("[OK] 23. Agendamento concluído " + agendamento);
        } catch (IllegalStateException e) {
            System.out.println("[FALHOU] 23. Erro na conclusão do agendamento " + e.getMessage());
        }

        // 24. Cancelar agendamento concluído
        Agendamento concluido = new Agendamento(rex, consulta, LocalDateTime.now().plusDays(1));
        concluido.concluir();

        try {
            concluido.cancelar();      // só a ação testada
            System.out.println("[FALHOU] 24. ...");
        } catch (IllegalStateException e) {
            System.out.println("[OK] 24. ...");
        }
    }
}