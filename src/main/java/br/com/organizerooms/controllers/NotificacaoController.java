package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.EnviaEmailDTO;
import br.com.organizerooms.dto.NotificacaoDTO;
import br.com.organizerooms.dto.PessoaDTO;
import br.com.organizerooms.models.Notificacao;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.utils.EnviaEmail;
import br.com.organizerooms.services.NotificacaoService;
import java.util.ArrayList;
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
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    NotificacaoService notificacaoService;

    @Autowired
    EnviaEmail enviaEmail;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> alterarNotificacao(@RequestBody NotificacaoDTO notificacao) {
        Notificacao nnotificacao = new Notificacao(notificacao);
        NotificacaoDTO notificacaoDTO = new NotificacaoDTO(notificacaoService.persiste(nnotificacao));

        Boolean retorno = false;
        if (!notificacaoDTO.isNotAtiva()) {
            retorno = true;
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarPorId(@PathVariable String id) {
        Notificacao notificacao = notificacaoService.buscaPorId(Long.parseLong(id));
        Response response = new Response(notificacao);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/pessoa/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarPorPessoa(@PathVariable String id) {
        Pessoa pessoa = new Pessoa();
        pessoa.setPesId(Long.parseLong(id));
        List<Notificacao> notificacao = notificacaoService.buscaPorPessoaAtivas(pessoa);
        Response response = new Response(notificacao);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/enviaEmail")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> enviarEmail(@RequestBody ArrayList<NotificacaoDTO> notificacaoDTO) {
  
        Boolean retorno = false;
        if (notificacaoDTO != null) {
            for (int i = 0; i < notificacaoDTO.size(); i++) {

                if (notificacaoDTO.get(i).getEnviaEmail() != null) {
                    retorno = enviaEmail.enviar(
                            notificacaoDTO.get(i).getEnviaEmail().getDestinatario(),
                            notificacaoDTO.get(i).getEnviaEmail().getAssunto(),
                            notificacaoDTO.get(i).getEnviaEmail().getMensagem()
                    );

                    Notificacao newNoti = new Notificacao(notificacaoDTO.get(i));

                    newNoti.setNotEnviado(retorno);
                    NotificacaoDTO newNotiDTO = new NotificacaoDTO(notificacaoService.persiste(newNoti));
                }
            }
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }
}
