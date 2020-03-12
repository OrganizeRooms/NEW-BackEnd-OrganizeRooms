package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Sala;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eder Jean Dias
 */
@Repository
@Transactional(readOnly = true)
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    public List<Agendamento> findAllByOrderByAgeDescricao();

    @Query(nativeQuery = true, value = "select a.* from Agendamento a "
            + "	INNER JOIN Sala s ON a.age_Sala = s.sala_Id"
            + "	where a.age_pes_responsavel = ?1"
            + "	and a.age_Data >= ?2"
            + "	and a.age_Data <= ?3"
            + "	and s.uni_id = ?4"
            + "	and a.age_Status = ?5")
    public List<Agendamento> recuperaReservasResponsavel(
            Long idResponsavel, String dataInicial, String dataFinal, Long idUnidade, String status
    );

    public List<Agendamento> findByAgePesResponsavel(Pessoa pessoa);

    public List<Agendamento> findByAgeSala(Sala sala);

    /*@Query("select a.ageId, a.ageAssunto, a.ageData, a.ageDescricao, a.ageHoraInicio, a.ageHoraFim, a.ageStatus from Agendamento a "
     + "INNER JOIN Participante p ON a.ageId = p.parAgendamento "
     + "WHERE p.parPessoa = ?1 and a.ageData = ?2 and a.ageStatus in ('AGENDADO', 'EM ANDAMENTO')")*/
    @Query(nativeQuery = true, value = "select a.* from Agendamento a "
            + "INNER JOIN Participante p ON a.age_Id = p.par_Agendamento "
            + "INNER JOIN Pessoa pp ON p.par_Pessoa = pp.pes_Id "
            + "WHERE pp.pes_Id = ?1 and a.age_Data = ?2 and a.age_Status = 'AGENDADO'")
    public List<Agendamento> recuperaAgendamentosParticipante(Long idParticipante, String dataAgrupamento);

    @Query(nativeQuery = true, value = "select a.* from Agendamento a "
            + "INNER JOIN Sala s ON a.age_Sala = s.sala_Id "
            + "where s.sala_Id = ?1 and a.age_Data = ?2 and a.age_Status = 'AGENDADO'")
    public List<Agendamento> recuperaAgendamentoSala(Long idSala, String dataAgrupamento);

}
