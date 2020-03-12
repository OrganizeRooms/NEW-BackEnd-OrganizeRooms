package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.ReservaEquipamento;
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
public interface ReservaEquipamentoRepository extends JpaRepository <ReservaEquipamento, Long >{
      
    public List<ReservaEquipamento> findAllByEquipamento(Equipamento equId);

    public List<ReservaEquipamento> findAllByAgendamento(ReservaEquipamento reserv);
    
}

