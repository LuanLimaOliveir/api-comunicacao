package com.luizalabs.apicomunicacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamento", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Integer id;

    @Column(name = "dt_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_comunicacao")
    private TipoComunicacao tipoComunicacao;
}