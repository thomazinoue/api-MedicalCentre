package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Component
public class ValidadorCancelaComAntecedencia implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void valida(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var hours = Duration.between(agora, consulta.getData()).toHours();

        if(hours < 24){
            throw new ValidacaoException("Consulta nao pode ser cancelada com menos de 24hrs de antecedencia da consulta.");
        }
    }
}
