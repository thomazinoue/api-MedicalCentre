package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalahamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
        public DadosDetalahamentoConsulta(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
        }
}
