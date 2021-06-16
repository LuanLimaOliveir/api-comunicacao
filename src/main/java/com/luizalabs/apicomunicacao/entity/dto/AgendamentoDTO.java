package com.luizalabs.apicomunicacao.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.TipoComunicacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AgendamentoDTO {
    private Integer id;
    @NotNull(message = "Data de agendamento é obrigátorio")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAgendamento;
    @NotNull(message = "Destinatário é obrigátorio")
    private String destinatario;
    @NotNull(message = "Mensagem é obrigátorio")
    private String mensagem;
    @NotNull(message = "ID do tipo de comunicação é obrigátorio")
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
