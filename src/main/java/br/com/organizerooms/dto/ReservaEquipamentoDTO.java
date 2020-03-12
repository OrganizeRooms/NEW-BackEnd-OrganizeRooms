package br.com.organizerooms.dto;

import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.ReservaEquipamento;

/**
 *
 * @author Eder Jean Dias
 */
public class ReservaEquipamentoDTO {

    private long resId;
    private Equipamento equipamento;
    private Agendamento agendamento;

    public ReservaEquipamentoDTO() {
    }

    public ReservaEquipamentoDTO(ReservaEquipamento obj) {
        this.equipamento = obj.getEquipamento();
        this.agendamento = obj.getAgendamento();
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
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
