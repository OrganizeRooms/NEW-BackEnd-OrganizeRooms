package br.com.organizerooms.controllers;

import br.com.organizerooms.context.AgendamentoContext;
import br.com.organizerooms.dao.AgendamentoDAO;
import br.com.organizerooms.dto.SalaDTO;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.services.AgendamentoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.organizerooms.services.SalaService;
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
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    SalaService salaService;
    
    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    AgendamentoDAO agendamentoDAO;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO','ROLE_TABLET')")
    public ResponseEntity<Response> buscarTodasSalas() {
        List<Sala> list = salaService.buscarTodasSalas();
        List<SalaDTO> listDto = list.stream().map(obj -> new SalaDTO(obj)).collect(Collectors.toList());
        Response response = new Response(listDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> addOrUpdateSala(@RequestBody SalaDTO sala) {
        Sala newSala = new Sala(sala);
        SalaDTO salaDTO = new SalaDTO(salaService.add(newSala));
        Response response = new Response(salaDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarSalaPorId(@PathVariable String id) {

        Sala lista = salaService.buscarSalaPorId(Long.parseLong(id));
        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/salasdisp")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarSalasDisponiveis(@RequestBody AgendamentoContext ctx) {
        List<SalaDTO> salas = agendamentoDAO.recuperaSala(ctx.getIdUnidade(), ctx.getLotacao(), ctx.getDataInicial(), ctx.getDataFinal(), ctx.getDataAgendamento());
        Response response = new Response(salas);
        return ResponseEntity.ok().body(response);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deletar(@PathVariable String id) {
        Boolean deletou = false;
        Sala sala = new Sala();
        sala.setSalaId(Long.parseLong(id));
        
        if (id != null) {          
            List<Agendamento> listaAgendamentos = agendamentoService.buscaPorSala(sala);
            
            if (listaAgendamentos.isEmpty()){
                salaService.remover(Long.parseLong(id));
                deletou = true;            
            }
        }
        
        return ResponseEntity.ok().body(new Response(deletou));
    }

}
