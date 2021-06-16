package com.luizalabs.apicomunicacao.service.impl;

import com.luizalabs.apicomunicacao.entity.Agendamento;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.repository.AgendamentoRepository;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO) {
        var agendamento = agendamentoDTO.toModel();
        agendamento.setDataCadastro(LocalDateTime.now());

        return this.agendamentoRepository.save(agendamento);
    }
}
