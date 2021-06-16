package com.luizalabs.apicomunicacao.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.StatusEnvio;
import com.luizalabs.apicomunicacao.entity.TipoComunicacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LogEnvioMensagemDTO {
    private Integer id;
    private Integer idAgendamento;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataProcesaamento;
    private String descricaoStatusEnvio;
    private Integer codigoStatus;
}
