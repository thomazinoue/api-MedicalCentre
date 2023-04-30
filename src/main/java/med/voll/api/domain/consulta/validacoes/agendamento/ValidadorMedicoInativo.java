package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository repository;

    public void valida(DadosAgendaConsulta dados){
        if(dados.idMedico() == null){
            return;
        }
        var isMedicoAtivo = repository.findAtivoById(dados.idMedico());
        if(!isMedicoAtivo){
            throw new ValidacaoException("Medico nao esta ativo no sistema");
        }
    }
}
