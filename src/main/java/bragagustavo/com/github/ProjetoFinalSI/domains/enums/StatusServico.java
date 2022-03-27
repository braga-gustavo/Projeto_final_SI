package bragagustavo.com.github.ProjetoFinalSI.domains.enums;

import jdk.jshell.Snippet;

public enum StatusServico {

    ABERTO(0,"Pedido Aberto"),
    EM_ANDAMENTO(1,"Pedido em Andamento"),
    FECHADO(2,"Pedido Fechado"),
    CONCLUIDO(3,"Pedido Concluido"),
    PENDENCIA(4,"Pedido possui Pendecias");

    private int cod;
    private String descricao;

    StatusServico(int id, String descricao){
        this.cod = id;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusServico toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (StatusServico x : StatusServico.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id Inválido: " + cod);

    }
}
