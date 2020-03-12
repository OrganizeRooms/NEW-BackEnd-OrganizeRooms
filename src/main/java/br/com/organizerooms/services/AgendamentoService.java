package br.com.organizerooms.services;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.repositorios.AgendamentoRepository;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eder Jean Dias
 */
@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public List<Agendamento> buscarTodosAgendamentos() {
        return this.agendamentoRepository.findAllByOrderByAgeDescricao();
    }

    public void remover(Long id) {
        this.agendamentoRepository.deleteById(id);
    }

    public Agendamento add(Agendamento agendamento) {
        return this.agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarAgendamentoPorId(Long id) {
        return this.agendamentoRepository.findById(id).get();
    }

    public List<Agendamento> buscaPorResponsavel(Pessoa pessoa) {
        return this.agendamentoRepository.findByAgePesResponsavel(pessoa);
    }

    public List<Agendamento> buscaPorSala(Sala sala) {
        return this.agendamentoRepository.findByAgeSala(sala);
    }

    public List<Agendamento> buscaPorPaticipanteEData(Long id, String data) {
        return this.agendamentoRepository.recuperaAgendamentosParticipante(id, data);
    }

    public List<Agendamento> buscaPorSalaEData(Long id, String data) {
        return this.agendamentoRepository.recuperaAgendamentoSala(id, data);
    }

    public List<Agendamento> buscaReservasResponsavel(
            Long idResponsavel, String dataInicial, String dataFinal, Long idUnidade, String status) {
        return this.agendamentoRepository.recuperaReservasResponsavel(idResponsavel, dataInicial, dataFinal, idUnidade, status);
    }

}
