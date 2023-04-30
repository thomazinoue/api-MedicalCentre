package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaMesmaDataHora implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void valida(DadosAgendaConsulta dados){
        var existeConsulta = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),  dados.data());

        if(existeConsulta){
           throw new ValidacaoException("Medico ja possui consulta no horario selecionado");
        }
    }
}
