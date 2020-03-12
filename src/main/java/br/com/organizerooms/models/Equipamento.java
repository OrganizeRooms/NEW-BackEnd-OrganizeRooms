/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.models;

import br.com.organizerooms.dto.EquipamentoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author Aluno
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "equipamento")
public class Equipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equId;

    @Column
    private String equNome;

    @Column
    private String equDescricao;

    @ManyToOne
    @JoinColumn(name = "uniId")
    private Unidade equUnidade;

    @Column
    private Boolean equAtiva;

    @Column(updatable = false)
    private Long equPesCadastro;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date equDtCadastro;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date equDtAtualizacao;

    @Column
    private Long equPesAtualizacao;

    @OneToMany(mappedBy = "equipamento")
    private List<ReservaEquipamento> reservaEquipamento;
    
    /*Retirada do joinTable
    @JsonIgnore
    @ManyToMany(mappedBy = "equipamentos")
    private List<Agendamento> agendamentos;*/

    public Equipamento() {
    }

    public Equipamento(Long equId, String equNome, String equDescricao, Unidade equUnidade, Boolean equAtiva,
            Long equPesCadastro, Date equDtCadastro, Date equDtAtualizacao, Long equPesAtualizacao) {
        this.equId = equId;
        this.equNome = equNome;
        this.equDescricao = equDescricao;
        this.equUnidade = equUnidade;
        this.equAtiva = equAtiva;
        this.equPesCadastro = equPesCadastro;
        this.equDtCadastro = equDtCadastro;
        this.equDtAtualizacao = equDtAtualizacao;
        this.equPesAtualizacao = equPesAtualizacao;
    }

    public Equipamento(EquipamentoDTO equipamento) {
        this.equId = equipamento.getEquId();
        this.equNome = equipamento.getEquNome();
        this.equDescricao = equipamento.getEquDescricao();
        this.equAtiva = equipamento.isEquAtiva();
        this.equUnidade = equipamento.getEquUnidade();
        this.equPesCadastro = equipamento.getEquPesCadastro();
        this.equDtCadastro = equipamento.getEquDtCadastro();
        this.equDtAtualizacao = Calendar.getInstance().getTime();
        this.equPesAtualizacao = equipamento.getEquPesAtualizacao();
    }

    public Long getEquId() {
        return equId;
    }

    public void setEquId(Long equId) {
        this.equId = equId;
    }

    public String getEquNome() {
        return equNome;
    }

    public void setEquNome(String equNome) {
        this.equNome = equNome;
    }

    public String getEquDescricao() {
        return equDescricao;
    }

    public void setEquDescricao(String equDescricao) {
        this.equDescricao = equDescricao;
    }

    public Unidade getEquUnidade() {
        return equUnidade;
    }

    public void setEquUnidade(Unidade equUnidade) {
        this.equUnidade = equUnidade;
    }

    public Boolean isEquAtiva() {
        return equAtiva;
    }

    public void setEquAtiva(Boolean equAtiva) {
        this.equAtiva = equAtiva;
    }

    public Long getEquPesCadastro() {
        return equPesCadastro;
    }

    public void setEquPesCadastro(Long equPesCadastro) {
        this.equPesCadastro = equPesCadastro;
    }

    public Date getEquDtCadastro() {
        return equDtCadastro;
    }

    public void setEquDtCadastro(Date equDtCadastro) {
        this.equDtCadastro = equDtCadastro;
    }

    public Date getEquDtAtualizacao() {
        return equDtAtualizacao;
    }

    public void setEquDtAtualizacao(Date equDtAtualizacao) {
        this.equDtAtualizacao = equDtAtualizacao;
    }

    public Long getEquPesAtualizacao() {
        return equPesAtualizacao;
    }

    public void setEquPesAtualizacao(Long equPesAtualizacao) {
        this.equPesAtualizacao = equPesAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.equId);
        hash = 71 * hash + Objects.hashCode(this.equNome);
        hash = 71 * hash + Objects.hashCode(this.equDescricao);
        hash = 71 * hash + (this.equAtiva ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.equDtCadastro);
        hash = 71 * hash + Objects.hashCode(this.equDtAtualizacao);
        hash = 71 * hash + Objects.hashCode(this.equPesAtualizacao);
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
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.equId, other.equId)) {
            return false;
        }
        if (!Objects.equals(this.equNome, other.equNome)) {
            return false;
        }
        if (!Objects.equals(this.equDescricao, other.equDescricao)) {
            return false;
        }
        if (!Objects.equals(this.equAtiva, other.equAtiva)) {
            return false;
        }
        if (!Objects.equals(this.equDtCadastro, other.equDtCadastro)) {
            return false;
        }
        if (!Objects.equals(this.equDtAtualizacao, other.equDtAtualizacao)) {
            return false;
        }
        if (!Objects.equals(this.equPesAtualizacao, other.equPesAtualizacao)) {
            return false;
        }
        return true;
    }

}
