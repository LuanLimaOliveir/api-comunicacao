package com.luizalabs.apicomunicacao.repository;

import com.luizalabs.apicomunicacao.entity.LogEnvioMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEnvioMensagemRepository extends JpaRepository<LogEnvioMensagem, Integer> {
    List<LogEnvioMensagem> findAllByAgendamento_Id(Integer idAgendamento);
    void deleteAllByAgendamento_Id(Integer idAgendamento);
}
