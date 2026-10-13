package br.com.petshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tutor {
    private Long id;
    private final String nome;
    private final String sobrenome;
    private final String telefone;
    private final String email;
    private final List<Pet> pets;

    // Constantes de classe para evitarem chamadas desnecessárias para cada objeto Tutor
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^(?:\\+?55\\s?)?(?:\\([1-9]{1}\\d\\)\\s?)?(?:9\\d|[2-9])\\d{3}-?\\d{4}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public Tutor(String nome, String sobrenome, String telefone, String email) {
        validarObrigatorio(nome, "nome");
        this.nome = nome;
        validarObrigatorio(sobrenome, "sobrenome");
        this.sobrenome = sobrenome;
        validarTelefone(telefone);
        this.telefone = telefone;
        validarEmail(email);
        this.email = email;
        this.pets = new ArrayList<>();
    }

    private void validarObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório!");
        }
    }

    private void validarTelefone(String telefone) {

        validarObrigatorio(telefone, "telefone");

        Matcher matcher = TELEFONE_PATTERN.matcher(telefone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de telefone inválido!");
        }
    }

    private void validarEmail(String email) {

        validarObrigatorio(email, "email");

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de email inválido!");
        }
    }

    private boolean pesquisaPet(Pet pet) {
        return this.pets.contains(pet);
    }

    public void adicionarPet(Pet pet) {
        if (pet == null){
            throw new IllegalArgumentException("Você deve cadastrar um pet válido!");
        }
        if (pesquisaPet(pet)) {
            throw new IllegalArgumentException("O pet já está cadastrado! " + pet.toString());
        }
        this.pets.add(pet);
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", pets=" + pets +
                '}';
    }

    public List<Pet> getPets() {
        return List.copyOf(pets);
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
