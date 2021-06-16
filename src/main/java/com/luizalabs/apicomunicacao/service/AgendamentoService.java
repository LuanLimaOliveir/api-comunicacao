package com.luizalabs.apicomunicacao.service;

import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;

public interface AgendamentoService {
    Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO);
}
