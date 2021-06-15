package com.luizalabs.apicomunicacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_comunicacao", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TipoComunicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_comunicacao")
    private Integer id;

    @Column(name = "ds_tipo_comunicacao")
    private String descricao;
}
