package br.com.organizerooms.models;

import br.com.organizerooms.dto.ReservaEquipamentoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Eder Jean Dias
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reserva_equipamento")
public class ReservaEquipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;

    @ManyToOne
    @JoinColumn(name = "equId")
    private Equipamento equipamento;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ageId")
    private Agendamento agendamento;

    public ReservaEquipamento() {
    }

    public ReservaEquipamento(Long resId, Equipamento equipamento, Agendamento agendamento) {
        this.resId = resId;
        this.equipamento = equipamento;
        this.agendamento = agendamento;
    }

    public ReservaEquipamento(ReservaEquipamentoDTO obj) {
        this.equipamento = obj.getEquipamento();
        this.agendamento = obj.getAgendamento();
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

}
