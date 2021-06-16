package com.luizalabs.apicomunicacao.controller.v1;

import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.entity.dto.LogEnvioMensagemDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Agendar o envio de comunicação", response = AgendamentoDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = ""),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDTO criarAgendamento(@Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        return this.agendamentoService.criarAgendamento(agendamentoDTO).toDTO();
    }

    @ApiOperation(value = "Consultar situação do agendamento", response = LogEnvioMensagemDTO[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
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

    @ApiOperation(value = "Excluir agendamento para envio de comunicação")
    @ApiResponses(value = { @ApiResponse(code = 204, message = ""),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
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
