package com.luizalabs.apicomunicacao.service.impl;

import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.LogEnvioMensagem;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.repository.AgendamentoRepository;
import com.luizalabs.apicomunicacao.repository.LogEnvioMensagemRepository;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final LogEnvioMensagemRepository logEnvioMensagemRepository;

    @Override
    @Transactional
    public Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO) {
        var agendamento = agendamentoDTO.toModel();
        agendamento.setDataCadastro(LocalDateTime.now());

        return this.agendamentoRepository.save(agendamento);
    }

    @Override
    public List<LogEnvioMensagem> consultarAgendamento(Integer idAgendamento) {
        return this.logEnvioMensagemRepository.findAllByAgendamento_Id(idAgendamento);
    }

    @Override
    @Transactional
    public void excluirAgendamento(Integer idAgendamento) {
        this.logEnvioMensagemRepository.deleteAllByAgendamento_Id(idAgendamento);
        this.agendamentoRepository.deleteById(idAgendamento);
    }
}
