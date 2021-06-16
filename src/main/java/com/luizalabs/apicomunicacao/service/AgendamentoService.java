package com.luizalabs.apicomunicacao.service;

import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.LogEnvioMensagem;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;

import java.util.List;

public interface AgendamentoService {
    Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO);
    List<LogEnvioMensagem> consultarAgendamento(Integer idAgendamento);
    void excluirAgendamento(Integer idAgendamento);
}
