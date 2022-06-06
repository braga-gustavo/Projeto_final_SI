package bragagustavo.com.github.ProjetoFinalSI.dto;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@PrestadorUpdate
public class PrestadorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;


    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    private String telefone;

    @CPF
    private String cpf;


    public PrestadorDto() {
    }

    public PrestadorDto(Integer id, String nome, String email, String telefone, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public PrestadorDto(Prestador prestador) {
        this.id = prestador.getId();
        this.nome = prestador.getNome();
        this.email = prestador.getEmail();
        this.telefone = prestador.getTelefone();
        this.cpf = prestador.getCpf();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

