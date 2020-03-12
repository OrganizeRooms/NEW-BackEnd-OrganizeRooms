package br.com.organizerooms.controllers;

import br.com.organizerooms.dto.ParticipanteDTO;
import br.com.organizerooms.dto.ReservaEquipamentoDTO;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Response;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.ReservaEquipamento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.organizerooms.services.ParticipanteService;
import br.com.organizerooms.services.ReservaEquipamentoService;
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
@RequestMapping("/reservaEquipamento")
public class ReservaEquipamentoController {

    @Autowired
    ReservaEquipamentoService reservaEquipamentoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarReservaPorId(@PathVariable String id) {
        ReservaEquipamento lista = reservaEquipamentoService.buscarReservaPorId(Long.parseLong(id));
        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/porAgendamento/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> buscarReservaPorAgendamento(@PathVariable String id) {

        List<ReservaEquipamento> lista = null;
        if (!id.equals("")) {
            Agendamento age = new Agendamento();
            age.setAgeId(Long.parseLong(id));
            
            ReservaEquipamento reserv = new ReservaEquipamento();
            reserv.setAgendamento(age);
            lista = reservaEquipamentoService.buscarPorAgendamento(reserv);
        }

        Response response = new Response(lista);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> adicionarReservaEquipamento(@RequestBody ReservaEquipamentoDTO reserva) {
        ReservaEquipamento newReservaEquipamento = new ReservaEquipamento(reserva);
        ReservaEquipamentoDTO reservaEquipamentoDTO = new ReservaEquipamentoDTO(reservaEquipamentoService.add(newReservaEquipamento));
        Response response = new Response(reservaEquipamentoDTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/listaReservas")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> adicionarListaReservas(@RequestBody ArrayList<ReservaEquipamentoDTO> reservas) {

        Boolean retorno = false;
        try {
            if (reservas != null) {
                for (int i = 0; i < reservas.size(); i++) {
                    ReservaEquipamento reser = new ReservaEquipamento(reservas.get(i));

                    reservaEquipamentoService.add(reser);
                }
                retorno = true;
            }
        } catch (Exception e) {
            //
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO')")
    public ResponseEntity<Response> deletarReserva(@PathVariable String id) {

        Boolean retorno = false;
        if (!id.equals("")) {
            ReservaEquipamento delReser = new ReservaEquipamento();
            delReser.setResId(Long.parseLong(id));
            reservaEquipamentoService.remove(delReser);

            try {
                ReservaEquipamento reser = reservaEquipamentoService.buscarReservaPorId(Long.parseLong(id));
            } catch (Exception e) {
                retorno = true;
            }
        }

        Response response = new Response(retorno);
        return ResponseEntity.ok().body(response);
    }

}
