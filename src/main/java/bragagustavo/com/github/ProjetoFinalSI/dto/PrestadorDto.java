package bragagustavo.com.github.ProjetoFinalSI.dto;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@PrestadorUpdate
public class PrestadorDto implements Serializable {

    private static final Long seriazlVerisionUID = 1L;

    private int id;

    @NotEmpty(message = "Nome nao pode ser vazio")
    @Length(min = 5, max = 125, message = "O nome deve ter entre 5 e 125 caracteres")
    private String nome;

    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    private String telefone;

    public PrestadorDto(int id, String nome, String email, String telefone, String cpf) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    @CPF
    private String cpf;


    public PrestadorDto(Prestador prestador) {

        this.id = prestador.getId();
        this.nome = prestador.getNome();
        this.email = prestador.getEmail();
        this.telefone = prestador.getTelefone();
        this.cpf = prestador.getCpf();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
