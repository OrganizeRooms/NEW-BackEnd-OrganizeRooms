package br.com.organizerooms.models;

import br.com.organizerooms.dto.ParticipanteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;
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
@Table(name = "participante")
public class Participante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parId;

    // 1 Normal
    // 2 Obrigatório
    @Column
    private Integer parTipo;

    @Column
    private Boolean parConfirmado;

    @ManyToOne
    @JoinColumn(name = "parPessoa")
    private Pessoa parPessoa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parAgendamento")
    private Agendamento parAgendamento;

    public Participante() {
    }

    public Participante(Long parId, Integer parTipo, Boolean parConfirmado, Pessoa parPessoa, Agendamento parAgendamento) {
        this.parId = parId;
        this.parTipo = parTipo;
        this.parConfirmado = parConfirmado;
        this.parPessoa = parPessoa;
        this.parAgendamento = parAgendamento;
    }

    public Participante(ParticipanteDTO obj) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.parId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participante other = (Participante) obj;
        if (!Objects.equals(this.parId, other.parId)) {
            return false;
        }
        return true;
    }

}
