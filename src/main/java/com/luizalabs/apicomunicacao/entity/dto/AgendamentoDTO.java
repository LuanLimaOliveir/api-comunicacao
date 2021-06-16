package com.luizalabs.apicomunicacao.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.TipoComunicacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AgendamentoDTO {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAgendamento;
    private String destinatario;
    private String mensagem;
    private Integer idTipoComunicacao;
    private String descricaoTipoComunicacao;

    public Agendamento toModel() {
        var tipoComunicacao = new TipoComunicacao();
        tipoComunicacao.setId(this.idTipoComunicacao);

        return Agendamento.builder()
                .id(this.id)
                .dataAgendamento(this.dataAgendamento)
                .destinatario(this.destinatario)
                .mensagem(this.mensagem)
                .tipoComunicacao(tipoComunicacao)
                .build();
    }
}
