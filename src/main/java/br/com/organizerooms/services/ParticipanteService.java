package br.com.organizerooms.services;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.repositorios.ParticipanteRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eder Jean Dias
 */
@Service
public class ParticipanteService {

    @Autowired
    ParticipanteRepository participanteRepository;

    public List<Participante> buscarTodosParticipantes() {
        return this.participanteRepository.findAllByOrderByParTipo();
    }

    public Participante buscarParticipantePorId(Long id) {
        return this.participanteRepository.findById(id).get();
    }

    public Participante buscarPorAgendamentoEPessoa(Agendamento agend, Pessoa pessoa) {
        return this.participanteRepository.findByParAgendamentoAndParPessoa(agend, pessoa);
    }

    public List<Participante> buscarParticipantePorAgendamento(Participante part) {
        return this.participanteRepository.findAllByParAgendamento(part);
    }
    
    public List<Participante> buscarPorPessoa(Pessoa pessoa) {
        return this.participanteRepository.findAllByParPessoa(pessoa);
    }

    public void remover(Long id) {
        this.participanteRepository.deleteById(id);
    }

    public Participante add(Participante participante) {
        return this.participanteRepository.save(participante);
    }

    public void remove(Participante participante) {
        this.participanteRepository.delete(participante);
    }

}
