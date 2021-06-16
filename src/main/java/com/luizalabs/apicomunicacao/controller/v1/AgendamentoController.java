package com.luizalabs.apicomunicacao.controller.v1;

import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.entity.dto.LogEnvioMensagemDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoDTO criarAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDTO) {
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

    @DeleteMapping("{idAgendamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAgendamento(@PathVariable Integer idAgendamento) {
        this.agendamentoService.excluirAgendamento(idAgendamento);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        var errorsConstraintViolation = new HashMap<String, String>();

        exception.getFieldErrors().stream().
                forEach( e -> errorsConstraintViolation.put(e.getField(), e.getDefaultMessage()));

        return errorsConstraintViolation;
    }
}
