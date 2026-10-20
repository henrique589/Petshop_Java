package br.com.petshop.model;

public class Pet implements Entidade{
    private Long id;
    private String nome;
    private final Especie especie;
    private final Porte porte;

    public Pet(String nome, Especie especie, Porte porte) {
        validarNome(nome);
        if (especie == null) {
            throw new IllegalArgumentException("A espécie não pode ser nula!");
        }
        if (porte == null) {
            throw new IllegalArgumentException("O porte não pode ser nulo!");
        }
        this.nome = nome;
        this.especie = especie;
        this.porte = porte;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do pet é obrigatório");
        }
    }

    public void alterarNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Porte getPorte() {
        return porte;
    }

    @Override
    public String toString() {
        return "Pet{id=" + id + ", nome='" + nome + "', especie=" + especie + ", porte=" + porte + "}";
    }
}
