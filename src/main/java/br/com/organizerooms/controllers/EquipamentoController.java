package br.com.organizerooms.controllers;

import br.com.organizerooms.context.AgendamentoContext;
import br.com.organizerooms.dao.AgendamentoDAO;
import br.com.organizerooms.dto.EquipamentoDTO;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.ReservaEquipamento;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.services.EquipamentoService;
import br.com.organizerooms.services.ReservaEquipamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoService;

    @Autowired
    ReservaEquipamentoService reservaEquipamentoService;

    @Autowired
    AgendamentoDAO agendamentoDAO;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> addEquipamento(@RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = new Equipamento(equipamentoDTO);
        EquipamentoDTO nEquipamentoDTO = new EquipamentoDTO(equipamentoService.persiste(equipamento));
        Response response = new Response(nEquipamentoDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarPorId(@PathVariable String id) {
        Equipamento equipamento = equipamentoService.buscaPorId(Long.parseLong(id));
        Response response = new Response(equipamento);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/ativo")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarAtivo() {
        List<Equipamento> equipamentos = equipamentoService.buscaPorSituacao();
        Response response = new Response(equipamentos);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarTodos() {
        List<Equipamento> equipamentos = equipamentoService.buscaTodos();
        Response response = new Response(equipamentos);
        return ResponseEntity.ok().body(response);
    }

    // Somente Disponiveis
    @PostMapping("/equidisp")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarEquipamentosDisponiveis(@RequestBody AgendamentoContext ctx) {
        List<EquipamentoDTO> equipamentos = agendamentoDAO.recuperaEquipamento(ctx.getIdUnidade(), ctx.getDataInicial(), ctx.getDataFinal());
        Response response = new Response(equipamentos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deletar(@PathVariable String id) {
        Boolean deletou = false;
        Equipamento equip = new Equipamento();
        equip.setEquId(Long.parseLong(id));

        if (id != null) {
            List<ReservaEquipamento> listaReservas = reservaEquipamentoService.buscarPorEquipamento(equip);

            if (listaReservas.isEmpty()) {
                equipamentoService.remover(Long.parseLong(id));
                deletou = true;
            }
        }

        return ResponseEntity.ok().body(new Response(deletou));
    }
}
