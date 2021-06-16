package com.luizalabs.apicomunicacao.controller;

import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
