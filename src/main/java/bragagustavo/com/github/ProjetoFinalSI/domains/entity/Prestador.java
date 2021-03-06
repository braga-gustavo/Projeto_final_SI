package bragagustavo.com.github.ProjetoFinalSI.domains.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "prestador")
public class Prestador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "Campo nome é OBRIGATÓRIO!")
    private String nome;

    @CPF(message = "Informe um CPF válido")
    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @JsonIgnore
    @Column(name = "senha")
    private String senha;

    public Prestador(Integer id, String nome, String cpf, String email, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Prestador(Integer id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
}
