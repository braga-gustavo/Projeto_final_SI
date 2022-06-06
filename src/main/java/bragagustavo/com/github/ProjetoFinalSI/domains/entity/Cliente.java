package bragagustavo.com.github.ProjetoFinalSI.domains.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome é OBRIGATORIO")
    private String nome;

    @Column(name = "cpf")
    @NotEmpty(message = "Campo cpf é OBRIGATORIO")
    @CPF(message = "Informe um CPF Válido")
    private String cpf;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "senha")
    private String senha;

    @JsonIgnore
    @OneToMany
    private List<Servico> servicos;


    public Cliente(Integer identidade, String nome, String cpf, String email, String telefone) {
        this.id = identidade;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }


    public Cliente(Integer id, String nome, String cpf, String email, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }


}


