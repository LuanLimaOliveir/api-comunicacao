package com.luizalabs.apicomunicacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_log_envio_mensagem", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LogEnvioMensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cd_agendamento")
    private Agendamento agendamento;

    @Column(name = "dt_procesaamento")
    private LocalDateTime dataProcesaamento;

    @ManyToOne
    @JoinColumn(name = "cd_status_envio")
    private StatusEnvio statusEnvio;
}