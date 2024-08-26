package br.insper.aposta.aposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornarPartidaDTO {
    private String nomeMandante;
    private String nomeVisitante;
    private Integer placarMandante;
    private Integer placarVisitante;
    private String status;

    public String getStatus() {
        return status;
    }

    public String getResultado() {
        if (status.equals("REALIZADA")) {
            if (placarMandante > placarVisitante) {
                return "VITORIA_MANDANTE";
            } else if (placarMandante < placarVisitante) {
                return "VITORIA_VISITANTE";
            } else {
                return "EMPATE";
            }
        }
        return "NAO_REALIZADA";
    }
}
