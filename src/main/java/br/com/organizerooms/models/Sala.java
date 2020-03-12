package br.com.organizerooms.models;

import br.com.organizerooms.dto.SalaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Eder Jean Dias
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sala")
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaId;

    @Column
    private String salaNome;

    @Column
    private Integer salaLotacao;

    @Column
    private Boolean salaAtiva;

    @Column(updatable = false)
    private Long salaPesCadastro;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date salaDtCadastro;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date salaDtAtualizacao;

    @Column
    private Long salaPesAtualizacao;

    @ManyToOne
    @JoinColumn(name = "uniId" )
    private Unidade salaUnidade;

    ///
    @JsonIgnore
    @OneToMany(mappedBy = "ageSala")
    private List<Agendamento> agendamentos;

    public Sala() {
    }

    public Sala(Long salaId, String salaNome, Integer salaLotacao, Boolean salaAtiva, Long salaPesCadastro,
            Date salaDtCadastro, Date salaDtAtualizacao, Long salaPesAtualizacao, Unidade salaUnidade) {
        this.salaId = salaId;
        this.salaNome = salaNome;
        this.salaLotacao = salaLotacao;
        this.salaAtiva = salaAtiva;
        this.salaPesCadastro = salaPesCadastro;
        this.salaDtCadastro = salaDtCadastro;
        this.salaDtAtualizacao = salaDtAtualizacao;
        this.salaPesAtualizacao = salaPesAtualizacao;
        this.salaUnidade = salaUnidade;
    }

    public Sala(SalaDTO obj) {
        this.salaId = obj.getSalaId();
        this.salaNome = obj.getSalaNome();
        this.salaLotacao = obj.getSalaLotacao();
        this.salaAtiva = obj.getSalaAtiva();
        this.salaDtCadastro = obj.getSalaDtCadastro();
        this.salaDtAtualizacao = obj.getSalaDtAtualizacao();
        this.salaPesCadastro = obj.getSalaPesCadastro();
        this.salaPesAtualizacao = obj.getSalaPesAtualizacao();
        this.salaUnidade = obj.getSalaUnidade();
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getSalaNome() {
        return salaNome;
    }

    public void setSalaNome(String salaNome) {
        this.salaNome = salaNome;
    }

    public Integer getSalaLotacao() {
        return salaLotacao;
    }

    public void setSalaLotacao(Integer salaLotacao) {
        this.salaLotacao = salaLotacao;
    }

    public Boolean getSalaAtiva() {
        return salaAtiva;
    }

    public void setSalaAtiva(Boolean salaAtiva) {
        this.salaAtiva = salaAtiva;
    }

    public Date getSalaDtCadastro() {
        return salaDtCadastro;
    }

    public void setSalaDtCadastro(Date salaDtCadastro) {
        this.salaDtCadastro = salaDtCadastro;
    }

    public Date getSalaDtAtualizacao() {
        return salaDtAtualizacao;
    }

    public void setSalaDtAtualizacao(Date salaDtAtualizacao) {
        this.salaDtAtualizacao = salaDtAtualizacao;
    }

    public Long getSalaPesCadastro() {
        return salaPesCadastro;
    }

    public void setSalaPesCadastro(Long salaPesCadastro) {
        this.salaPesCadastro = salaPesCadastro;
    }

    public Long getSalaPesAtualizacao() {
        return salaPesAtualizacao;
    }

    public void setSalaPesAtualizacao(Long salaPesAtualizacao) {
        this.salaPesAtualizacao = salaPesAtualizacao;
    }

    public Unidade getSalaUnidade() {
        return salaUnidade;
    }

    public void setSalaUnidade(Unidade salaUnidade) {
        this.salaUnidade = salaUnidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.salaId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (!Objects.equals(this.salaId, other.salaId)) {
            return false;
        }
        return true;
    }
}
