package com.luizalabs.apicomunicacao;

import com.luizalabs.apicomunicacao.controller.v1.AgendamentoController;
import com.luizalabs.apicomunicacao.entity.LogEnvioMensagem;
import com.luizalabs.apicomunicacao.entity.StatusEnvio;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@WebMvcTest(AgendamentoController.class)
public class AgendamentoControllerTest {

    @MockBean
    private AgendamentoService agendamentoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void criarAgendamento() throws Exception {
        String json = "{" +
                "\"dataAgendamento\": \"2021-06-16 17:33:00\"," +
                "\"destinatario\": \"luanlima.oliveir@gmail.com\"," +
                "\"mensagem\": \"Olá Luan, bem-vindo(a) ao Gmail!\"," +
                "\"idTipoComunicacao\": 1" +
                "}";

        AgendamentoDTO agendamentoDTO = AgendamentoDTO.builder()
                    .id(70)
                    .dataAgendamento(LocalDateTime.now())
                    .destinatario("luanlima.oliveir@gmail.com")
                    .mensagem("Olá Luan, bem-vindo(a) ao Gmail!")
                    .idTipoComunicacao(1)
                    .descricaoTipoComunicacao("email")
                    .build();

        when(agendamentoService.criarAgendamento(any())).thenReturn(agendamentoDTO.toModel());

        this.mockMvc
                .perform(
                        post("/api/v1/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(70)))
                .andExpect(jsonPath("$.destinatario", is("luanlima.oliveir@gmail.com")))
                .andExpect(jsonPath("$.idTipoComunicacao", is(1)));
    }

    @Test
    public void consultarAgendamento() throws Exception {
        AgendamentoDTO agendamentoDTO = AgendamentoDTO.builder()
                .id(71)
                .dataAgendamento(LocalDateTime.now())
                .destinatario("luanlima.oliveir@gmail.com")
                .mensagem("Olá Luan, bem-vindo(a) ao Gmail!")
                .idTipoComunicacao(1)
                .descricaoTipoComunicacao("email")
                .build();

        LogEnvioMensagem log1 = LogEnvioMensagem.builder()
                .id(1)
                .agendamento(agendamentoDTO.toModel())
                .dataProcesaamento(LocalDateTime.now())
                .statusEnvio(
                    StatusEnvio.builder()
                    .id(2)
                    .descricao("error")
                    .codigo(500)
                    .build()
                ).build();

        LogEnvioMensagem log2 = LogEnvioMensagem.builder()
                .id(2)
                .agendamento(agendamentoDTO.toModel())
                .dataProcesaamento(LocalDateTime.now())
                .statusEnvio(
                    StatusEnvio.builder()
                    .id(1)
                    .descricao("success")
                    .codigo(200)
                    .build()
                ).build();

        when(agendamentoService.consultarAgendamento(71)).thenReturn(Arrays.asList(log1, log2));

        this.mockMvc
                .perform(
                        get("/api/v1/agendamento/{idAgendamento}", 71)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].idAgendamento", is(71)))
                .andExpect(jsonPath("$[0].codigoStatus", is(500)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].idAgendamento", is(71)))
                .andExpect(jsonPath("$[1].codigoStatus", is(200)));
    }
}
