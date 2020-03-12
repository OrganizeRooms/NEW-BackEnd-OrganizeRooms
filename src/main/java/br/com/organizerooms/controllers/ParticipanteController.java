package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.ParticipanteDTO;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Participante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.organizerooms.services.ParticipanteService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Eder Jean Dias
 */
@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    ParticipanteService participanteService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarTodosParticipantes() {
        List<Participante> list = participanteService.buscarTodosParticipantes();
        List<ParticipanteDTO> listDto = list.stream().map(obj -> new ParticipanteDTO(obj)).collect(Collectors.toList());
        Response response = new Response(listDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarParticipantePorId(@PathVariable String id) {
        Participante lista = participanteService.buscarParticipantePorId(Long.parseLong(id));
        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/porAgendamento/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarParticipantePorAgendamento(@PathVariable String id) {

        List<Participante> lista = null;
        if (!id.equals("")) {
            Participante part = new Participante();
            part.setParId(Long.parseLong(id));

            lista = participanteService.buscarParticipantePorAgendamento(part);
        }

        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> adicionarParticipante(@RequestBody ParticipanteDTO participante) {
        Participante newParticipante = new Participante(participante);
        ParticipanteDTO participanteDTO = new ParticipanteDTO(participanteService.add(newParticipante));
        Response response = new Response(participanteDTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/listaParticipantes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> adicionarListaParticipantes(@RequestBody ArrayList<ParticipanteDTO> participantes) {

        Boolean retorno = false;
        try {
            if (participantes != null) {
                for (int i = 0; i < participantes.size(); i++) {
                    Participante part = new Participante(participantes.get(i));

                    participanteService.add(part);
                }
                retorno = true;
            }
        } catch (Exception e) {
            //
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/alterar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> alterarParticipante(@RequestBody ParticipanteDTO participanteDTO) {

        Participante partAlterado = new Participante();
        if (participanteDTO != null) {

            Participante part = participanteService.buscarPorAgendamentoEPessoa(participanteDTO.getParAgendamento(), participanteDTO.getParPessoa());
            if (part != null) {
                part.setParConfirmado(participanteDTO.getParConfirmado());
                partAlterado = participanteService.add(part);
            }
        }

        Response response = new Response(partAlterado);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> deletarParticipante(@PathVariable String id
    ) {

        Boolean retorno = false;
        if (!id.equals("")) {
            Participante delPart = new Participante();
            delPart.setParId(Long.parseLong(id));
            participanteService.remove(delPart);

            try {
                Participante part = participanteService.buscarParticipantePorId(Long.parseLong(id));
            } catch (Exception e) {
                retorno = true;
            }
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }

}
