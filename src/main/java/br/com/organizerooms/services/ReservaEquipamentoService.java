package br.com.organizerooms.services;

import br.com.organizerooms.dto.ReservaEquipamentoDTO;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.ReservaEquipamento;
import br.com.organizerooms.repositorios.ReservaEquipamentoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eder Jean Dias
 */
@Service
public class ReservaEquipamentoService {

    @Autowired
    ReservaEquipamentoRepository reservaEquipamentoRepository;

    public List<ReservaEquipamento> buscarPorAgendamento(ReservaEquipamento reservaEquipamento) {
        return this.reservaEquipamentoRepository.findAllByAgendamento(reservaEquipamento);
    }

    public List<ReservaEquipamento> buscarPorEquipamento(Equipamento equipamento) {
        return this.reservaEquipamentoRepository.findAllByEquipamento(equipamento);
    }

    public ReservaEquipamento buscarReservaPorId(Long id) {
        return this.reservaEquipamentoRepository.findById(id).get();
    }

    public ReservaEquipamento add(ReservaEquipamento reservaEquipamento) {
        return this.reservaEquipamentoRepository.save(reservaEquipamento);
    }

    public void remove(ReservaEquipamento reservaEquipamento) {
        this.reservaEquipamentoRepository.delete(reservaEquipamento);
    }

}
