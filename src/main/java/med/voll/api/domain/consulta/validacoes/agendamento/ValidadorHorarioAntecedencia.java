package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void valida(DadosAgendaConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if(diferenca < 30){
            throw new ValidacaoException("Consulta deve ser agendade com antecedencia minima de 30 minutos.");
        }
    }
}
