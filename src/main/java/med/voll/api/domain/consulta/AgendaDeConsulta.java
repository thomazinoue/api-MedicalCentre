package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadorAgendamentoDeConsultas;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadorCancelamentoConsultas;

     public DadosDetalahamentoConsulta agendar(DadosAgendaConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente nao encontrado!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Medico nao encontrado!");
        }

         validadorAgendamentoDeConsultas.forEach(a->a.valida(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = encontraMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Nao existe medico disponivel nesta data.");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalahamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado nao existe!");
        }

        validadorCancelamentoConsultas.forEach(a->a.valida(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }

    private Medico encontraMedico(DadosAgendaConsulta dados) {

        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        } else {
            if(dados.especialidade() != null){
                return medicoRepository.buscaMedicoAleatorioNaData(dados.especialidade(), dados.data());
            } else {
                throw new ValidacaoException("Selecione uma especialidade quando medico nao for escolhido!");
            }
        }
    }
}
