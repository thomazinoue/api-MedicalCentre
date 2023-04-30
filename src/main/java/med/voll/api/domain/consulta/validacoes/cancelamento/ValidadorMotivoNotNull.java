package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoNotNull implements ValidadorCancelamentoConsulta{
    @Override
    public void valida(DadosCancelamentoConsulta dados) {
        if(dados.motivoCancelamento() == null){
            throw new ValidacaoException("Motivo do cancelamento tem que ser preenchido.");
        }
    }
}
