package br.com.petshop.service;

import br.com.petshop.exception.RecursoNaoEncontradoException;
import br.com.petshop.model.*;
import br.com.petshop.repository.Repositorio;
import br.com.petshop.repository.RepositorioEmMemoria;

import java.time.LocalDateTime;
import java.util.List;

public class PetShopService {
    private final Repositorio<Tutor> dadosTutor;
    private final Repositorio<Pet> dadosPet;
    private final Repositorio<Agendamento> dadosAgendamento;

    public PetShopService() {
        this.dadosTutor = new RepositorioEmMemoria<>();
        this.dadosPet = new RepositorioEmMemoria<>();
        this.dadosAgendamento = new RepositorioEmMemoria<>();
    }

    public Tutor cadastrarTutor(String nome, String sobrenome, String telefone, String email) {
        return this.dadosTutor.salvar(new Tutor(nome, sobrenome, telefone, email));

    }

    public Pet cadastrarPet(Long tutorId, String nome, Especie especie, Porte porte) {
        Tutor tutor = this.dadosTutor.buscarPorId(tutorId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tutor não encontrado!"));
        Pet novoPet = new Pet(nome, especie, porte);
        tutor.adicionarPet(novoPet);
        return this.dadosPet.salvar(novoPet);
    }

    public Agendamento agendar(Long petId, Servico servico, LocalDateTime dataHora) {
        Pet pet = this.dadosPet.buscarPorId(petId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pet não encontrado!"));
        Agendamento novoAgendamento = new Agendamento(pet, servico, dataHora);
        return this.dadosAgendamento.salvar(novoAgendamento);
    }

    public Agendamento concluirAgendamento(Long id) {
        Agendamento agendamento = this.dadosAgendamento.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado!"));
        agendamento.concluir();
        return agendamento;
    }

    public Agendamento cancelarAgendamento(Long id) {
        Agendamento agendamento = this.dadosAgendamento.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado!"));
        agendamento.cancelar();
        return agendamento;
    }

    public List<Tutor> listarTutores() {
        return this.dadosTutor.listarTodos();
    }

    public List<Agendamento> listarAgendamentos() {
        return this.dadosAgendamento.listarTodos();
    }

}
