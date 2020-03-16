package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.AgendamentoDTO;
import br.com.organizerooms.dto.MensagemDTO;
import br.com.organizerooms.models.Mensagem;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.services.MensagemService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    MensagemService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> adicionar(@RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagem = new Mensagem(mensagemDTO);
        MensagemDTO nMensagemDTO = new MensagemDTO(service.persiste(mensagem));
        Response response = new Response(nMensagemDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> buscarTodos() {
        List<Mensagem> mensagens = service.buscaTodos();
        List<MensagemDTO> msgDTO = mensagens.stream().map(obj -> new MensagemDTO(obj)).collect(Collectors.toList());
        Response response = new Response(msgDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarPorTipo(@PathVariable String id) {
        Mensagem mensagem = service.buscaPorTipo(Integer.parseInt(id));
        Response response = new Response(new MensagemDTO(mensagem));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/msgsPadrao")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> buscarMensagensPadrao() {
        List<Mensagem> mensagens = getMensagensPadrao();
        List<MensagemDTO> msgDTO = mensagens.stream().map(obj -> new MensagemDTO(obj)).collect(Collectors.toList());
        Response response = new Response(msgDTO);
        return ResponseEntity.ok().body(response);
    }

    public List<Mensagem> getMensagensPadrao() {

        List<Mensagem> msgsPadrao = new ArrayList<>();

        Mensagem msg = new Mensagem(
                1L,
                "Assunto E-mail de Agendamento",
                "Nova Reunião Marcada por [RESERVA_RESPONSAVEL].",
                1,
                null,
                0L);

        Mensagem msg2 = new Mensagem(
                2L,
                "Assunto E-mail retirado da Reunião",
                "Retirado da Reunião Marcada por [RESERVA_RESPONSAVEL].",
                2,
                null,
                0L);

        Mensagem msg3 = new Mensagem(
                3L,
                "E-mail agendamento para Participante Comum",
                "[SAUDACAO] <b>[PARTICIPANTE_NOME]</b><br/><br/>Você é uma pessoa Obrigatória na nova reunião marcada por [RESERVA_RESPONSAVEL] na data <b>[RESERVA_DATA]</b> no período das ",
                3,
                null,
                0L);

        Mensagem msg4 = new Mensagem(
                4L,
                "E-mail agendamento para Participante Obrigatório",
                "[SAUDACAO] <b>[PARTICIPANTE_NOME]</b><br/><br/>Você é uma pessoa Obrigatória na nova reunião marcada por [RESERVA_RESPONSAVEL] na data <b>[RESERVA_DATA]</b> no período das [RESERVA_HORA_INICIO] às [RESERVA_HORA_FIM].",
                4,
                null,
                0L);

        Mensagem msg5 = new Mensagem(
                5L,
                "E-mail retirado da Reunião",
                "[SAUDACAO] <b>[PARTICIPANTE_NOME]</b><br/><br/>Você foi retirado da reunião marcada por [RESERVA_RESPONSAVEL] na data <b>RESERVA_DATA]</b> no período das [RESERVA_HORA_INICIO] às [RESERVA_HORA_FIM], referente a [RESERVA_ASSUNTO].",
                5,
                null,
                0L);

        msgsPadrao.add(msg);
        msgsPadrao.add(msg2);
        msgsPadrao.add(msg3);
        msgsPadrao.add(msg4);
        msgsPadrao.add(msg5);

        return msgsPadrao;
    }
}
