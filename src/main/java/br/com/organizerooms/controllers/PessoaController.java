/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.PessoaDTO;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.services.AgendamentoService;
import br.com.organizerooms.services.ParticipanteService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.organizerooms.services.PessoaService;
import br.com.organizerooms.services.UnidadeService;
import br.com.organizerooms.utils.SenhaUtils;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Leandro Prado
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ParticipanteService participanteService;

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    SenhaUtils senhaUtils;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarTodasPessoas() {
        List<Pessoa> list = pessoaService.buscarTodasPessoas();
        List<PessoaDTO> listDto = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
        Response response = new Response(listDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> addPessoa(@RequestBody PessoaDTO pessoa) {
        Pessoa newPessoa = new Pessoa(pessoa);
        Optional<Pessoa> oldpessoa = pessoaService.buscarPessoaPorId(pessoa.getPesId());

        if (newPessoa.getPesId() == 0) {
            newPessoa.setPesSenha("senha");
        } else {
            newPessoa.setPesSenha(oldpessoa.get().getPesSenha());
        }

        PessoaDTO pesDTO = new PessoaDTO(pessoaService.addPessoa(newPessoa));
        Response response = new Response(pesDTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/importar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> importarPessoas(@RequestBody ArrayList<PessoaDTO> pessoas) {

        List<String> inconsistências = new ArrayList<>();

        if (!pessoas.isEmpty()) {
            for (int i = 0; i < pessoas.size(); i++) {

                if (pessoas.get(i).getPesNome().equals("")
                        || pessoas.get(i).getPesEmail().equals("")
                        || pessoas.get(i).getPesUnidade().getUniId().toString().equals("")) {
                    inconsistências.add("Erro ao importar registro da linha: " + (i + 2));

                } else {
                    Unidade impUnidade = new Unidade();
                    try {
                        impUnidade = unidadeService.buscarUnidadePorId(pessoas.get(i).getPesUnidade().getUniId());
                        if (impUnidade != null) {
                            Optional<Pessoa> optPessoa = pessoaService.buscarPessoaPorEmail(pessoas.get(i).getPesEmail());
                            Pessoa newPessoa;
                            if (optPessoa.isPresent()) {
                                newPessoa = new Pessoa(optPessoa.get());
                                newPessoa.setPesNome(pessoas.get(i).getPesNome());
                                if (!pessoas.get(i).getPesDdd().equals("")) {
                                    newPessoa.setPesDdd(pessoas.get(i).getPesDdd());
                                }
                                if (!pessoas.get(i).getPesTelefone().equals("")) {
                                    newPessoa.setPesTelefone(pessoas.get(i).getPesTelefone());
                                }

                                newPessoa.setPesUnidade(pessoas.get(i).getPesUnidade());

                            } else {
                                newPessoa = new Pessoa(pessoas.get(i));
                                newPessoa.setPesSenha("senha");
                            }

                            PessoaDTO pesDTO = new PessoaDTO(pessoaService.addPessoa(newPessoa));
                            if (pesDTO.getPesId() == null || pesDTO.getPesId() == 0) {
                                inconsistências.add("Erro ao importar registro da linha: " + (i + 2) + ", Pessoa: " + pessoas.get(i).getPesNome());
                            }
                        } else {
                            inconsistências.add("Erro ao importar registro da linha: " + (i + 2));
                        }
                    } catch (NoSuchElementException e) {
                        inconsistências.add("Erro ao importar registro da linha: " + (i + 2));
                    }
                }
            }
        }

        Response response = new Response(inconsistências);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/resetarSenha")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> resetarSenha(@RequestBody PessoaDTO pessoa) {
        Pessoa newPessoa = new Pessoa(pessoa);

        newPessoa.setPesSenha("senha");

        newPessoa = pessoaService.addPessoa(newPessoa);
        Boolean resposta = false;
        if (newPessoa.getPesSenha().equals("senha")) {
            resposta = true;
        }

        Response response = new Response(resposta);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deletarPessoa(@PathVariable String id) {
        Boolean deletou = false;
        Pessoa pessoa = new Pessoa();
        pessoa.setPesId(Long.parseLong(id));

        if (id != null) {
            List<Participante> listaParticipantes = participanteService.buscarPorPessoa(pessoa);

            if (!listaParticipantes.isEmpty()) {
                return ResponseEntity.ok().body(new Response(deletou));
            }

            List<Agendamento> listaAgendamentos = agendamentoService.buscaPorResponsavel(pessoa);

            if (listaAgendamentos.isEmpty()) {
                pessoaService.remover(Long.parseLong(id));
                deletou = !pessoaService.buscarPessoaPorId(Long.parseLong(id)).isPresent();
            }

        }

        return ResponseEntity.ok().body(new Response(deletou));
    }

}
