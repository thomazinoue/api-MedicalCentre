package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsulta agendaDeConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agenda(@RequestBody @Valid DadosAgendaConsulta dados){
        var dto = agendaDeConsulta.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancela(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agendaDeConsulta.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
