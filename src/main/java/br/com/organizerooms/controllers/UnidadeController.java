/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.UnidadeDTO;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.services.EquipamentoService;
import br.com.organizerooms.services.PessoaService;
import br.com.organizerooms.services.SalaService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.organizerooms.services.UnidadeService;
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
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    SalaService salaService;

    @Autowired
    EquipamentoService equipamentoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarTodasUnidades() {
        List<Unidade> list = unidadeService.buscarTodasUnidades();
        List<UnidadeDTO> listDto = list.stream().map(obj -> new UnidadeDTO(obj)).collect(Collectors.toList());
        Response response = new Response(listDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> addOrUpdateUnidade(@RequestBody UnidadeDTO unidade) {
        Unidade newUnidade = new Unidade(unidade);
        UnidadeDTO uniDTO = new UnidadeDTO(unidadeService.addUnidade(newUnidade));
        Response response = new Response(uniDTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("porId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarUnidadePorId(@RequestBody String id) {

        Unidade lista = unidadeService.buscarUnidadePorId(Long.parseLong(id));
        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ativo")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarAtivo() {
        List<Unidade> unidades = unidadeService.buscaPorSituacao();
        Response response = new Response(unidades);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deletar(@PathVariable String id) {
        Boolean deletou = false;
        Unidade unidade = new Unidade();
        unidade.setUniId(Long.parseLong(id));

        if (id != null) {

            List<Sala> listaSalas = salaService.buscarPorUnidade(unidade);

            if (!listaSalas.isEmpty()) {
                return ResponseEntity.ok().body(new Response(deletou));
            }

            List<Pessoa> listaPessoas = pessoaService.buscarPorUnidade(unidade);

            if (!listaPessoas.isEmpty()) {
                return ResponseEntity.ok().body(new Response(deletou));
            }
            List<Equipamento> listaEquipamentos = equipamentoService.buscarPorUnidade(unidade);

            if (!listaEquipamentos.isEmpty()) {
                return ResponseEntity.ok().body(new Response(deletou));
            }

            unidadeService.remover(Long.parseLong(id));
            deletou = true;
        }

        return ResponseEntity.ok().body(new Response(deletou));
    }

}
