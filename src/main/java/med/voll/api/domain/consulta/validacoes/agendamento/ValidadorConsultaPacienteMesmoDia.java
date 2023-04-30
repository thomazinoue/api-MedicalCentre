package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaPacienteMesmoDia implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void valida(DadosAgendaConsulta dados){
        var dataInicio = dados.data().withHour(7);
        var dataFinal = dados.data().withHour(18);
        var exiteConsultaPaciente = repository.existsByPacienteIdAndDataBetween(dados.idMedico(), dataInicio, dataFinal);

        if(exiteConsultaPaciente){
            throw new ValidacaoException("Paciente possui consulta no mesmo dia selecionado.");
        }
    }
}
