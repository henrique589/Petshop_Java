package br.com.petshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private final Pet pet;
    private final Servico servico;
    private final LocalDateTime dataHora;
    private StatusAgendamento status;
    private final BigDecimal valor;

    public Agendamento(Pet pet, Servico servico, LocalDateTime dataHora) {
        validarObrigatorio(pet, "Pet");
        this.pet = pet;
        validarObrigatorio(servico, "Serviço");
        this.servico = servico;
        validarObrigatorio(dataHora, "Data e hora");
        validarDataFutura(dataHora);
        this.dataHora = dataHora;
        this.status = StatusAgendamento.AGENDADO;
        this.valor = servico.calcularPreco(pet);
    }

    private static void validarObrigatorio(Object valorCampo, String campo) {
        if (valorCampo == null) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório! ");
        }
    }

    private static void validarDataFutura(LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora não pode estar no passado");
        }

    }

    public void concluir() {
        if ((status != StatusAgendamento.AGENDADO)) {
            throw new IllegalStateException("Não é possível concluir um agendamento com status " +  status);
        }
        this.status = StatusAgendamento.CONCLUIDO;
    }

    public void cancelar() {
        if ((status != StatusAgendamento.AGENDADO)) {
            throw new IllegalStateException("Não é possível cancelar um agendamento com status " +  status);
        }
        this.status = StatusAgendamento.CANCELADO;
    }

    public Long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", pet=" + pet +
                ", servico=" + servico +
                ", dataHora=" + dataHora +
                ", status=" + status +
                ", valor=" + valor +
                '}';
    }
}
