package com.luizalabs.apicomunicacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_status_envio", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatusEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_envio")
    private Integer id;

    @Column(name = "ds_status_envio")
    private String descricao;

    @Column(name = "cd_status")
    private Integer codigo;
}