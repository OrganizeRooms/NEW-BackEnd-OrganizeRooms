package br.com.organizerooms.dto;

import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Participante;

/**
 *
 * @author Eder Jean Dias
 */
public class ParticipanteDTO {

    private Long parId;
    private Integer parTipo;
    private Boolean parConfirmado;
    private Pessoa parPessoa;
    private Agendamento parAgendamento;

    public ParticipanteDTO() {
    }

    public ParticipanteDTO(Long parId, Integer parTipo, Boolean parConfirmado, Pessoa parPessoa, Agendamento parAgendamento) {
        this.parId = parId;
        this.parTipo = parTipo;
        this.parConfirmado = parConfirmado;
        this.parPessoa = parPessoa;
        this.parAgendamento = parAgendamento;
    }

    public ParticipanteDTO(Participante obj) {
        this.parId = obj.getParId();
        this.parTipo = obj.getParTipo();
        this.parConfirmado = obj.getParConfirmado();
        this.parPessoa = obj.getParPessoa();
        this.parAgendamento = obj.getParAgendamento();
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public Integer getParTipo() {
        return parTipo;
    }

    public void setParTipo(Integer parTipo) {
        this.parTipo = parTipo;
    }

    public Boolean getParConfirmado() {
        return parConfirmado;
    }

    public void setParConfirmado(Boolean parConfirmado) {
        this.parConfirmado = parConfirmado;
    }

    public Pessoa getParPessoa() {
        return parPessoa;
    }

    public void setParPessoa(Pessoa parPessoa) {
        this.parPessoa = parPessoa;
    }

    public Agendamento getParAgendamento() {
        return parAgendamento;
    }

    public void setParAgendamento(Agendamento parAgendamento) {
        this.parAgendamento = parAgendamento;
    }

}
