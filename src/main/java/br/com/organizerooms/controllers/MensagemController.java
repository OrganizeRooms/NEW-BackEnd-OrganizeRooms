package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.MensagemDTO;
import br.com.organizerooms.models.Mensagem;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.services.MensagemService;
import java.util.List;
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
        Response response = new Response(mensagens);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarPorTipo(@PathVariable String id) {
        Mensagem mensagem = service.buscaPorTipo(Integer.parseInt(id));
        Response response = new Response(mensagem);
        return ResponseEntity.ok().body(response);
    }
}
