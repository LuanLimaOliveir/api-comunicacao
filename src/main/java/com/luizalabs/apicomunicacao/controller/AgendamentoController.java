package com.luizalabs.apicomunicacao.controller;

import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.entity.dto.LogEnvioMensagemDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoDTO criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        return this.agendamentoService.criarAgendamento(agendamentoDTO).toDTO();
    }

    @GetMapping("{idAgendamento}")
    @ResponseStatus(HttpStatus.OK)
    public List<LogEnvioMensagemDTO> consultarAgendamento(@PathVariable Integer idAgendamento) {
        return this.agendamentoService.consultarAgendamento(idAgendamento).stream().
                map(l -> LogEnvioMensagemDTO.builder().
                        id(l.getId()).
                        idAgendamento(l.getAgendamento().getId()).
                        dataProcesaamento(l.getDataProcesaamento()).
                        descricaoStatusEnvio(l.getStatusEnvio().getDescricao()).
                        codigoStatus(l.getStatusEnvio().getCodigo()).
                        build()).
                collect(Collectors.toList());
    }
}
