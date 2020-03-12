package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eder Jean Dias
 */
@Repository
@Transactional(readOnly = true )
public interface ParticipanteRepository extends JpaRepository <Participante, Long >{
   
    public List<Participante> findAllByOrderByParTipo(); 
    
    public List<Participante> findAllByParAgendamento(Participante part);
    
    public Participante findByParAgendamentoAndParPessoa(Agendamento agend, Pessoa pessoa);
    
    public List<Participante> findAllByParPessoa(Pessoa pessoa);
    
}

