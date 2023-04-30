package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteInativo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void valida(DadosAgendaConsulta dados){
        var paciente = repository.findAtivoById(dados.idPaciente());
        if (!paciente){
            throw new ValidacaoException("Paciente nao esta ativo no sistema.");
        }
    }
}
