package bragagustavo.com.github.ProjetoFinalSI.domains.entity;

import bragagustavo.com.github.ProjetoFinalSI.domains.enums.StatusServico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "servico")
public class Servicos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "programador_id")
    private Programador programador;

    @Column(name = "titulo_servico")
    private String tituloServico;

    @Column(name = "descricao_servico")
    private String descricaoServico;

    @Column(name = "data_servico")
    private LocalDateTime dataServico;

    @Column(name = "orcamento_servico", precision = 28, scale = 2)
    private Double orcamentoServico;

    @Column(name = "status servico")
    private StatusServico statusServico;

    public Servicos(Integer id, Cliente cliente, Programador programador, String tituloServico, String descricaoServico, LocalDateTime dataServico, Double orcamentoServico, StatusServico statusServico) {

        this.id = id;
        this.cliente = cliente;
        this.programador = programador;
        this.tituloServico = tituloServico;
        this.descricaoServico = descricaoServico;
        this.dataServico = dataServico;
        this.orcamentoServico = orcamentoServico;
        this.statusServico = statusServico;
    }

    public Servicos(Integer id, Cliente cliente, String tituloServico, String descricaoServico, LocalDateTime dataServico, Double orcamentoServico, StatusServico statusServico) {

        this.id = id;
        this.cliente = cliente;
        this.tituloServico = tituloServico;
        this.descricaoServico = descricaoServico;
        this.dataServico = dataServico;
        this.orcamentoServico = orcamentoServico;
        this.statusServico = statusServico;
    }
}
