package com.luizalabs.apicomunicacao;

import com.luizalabs.apicomunicacao.controller.v1.AgendamentoController;
import com.luizalabs.apicomunicacao.entity.dto.AgendamentoDTO;
import com.luizalabs.apicomunicacao.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
