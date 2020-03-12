/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.models;

import br.com.organizerooms.dto.NotificacaoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Felipe
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notificacao")
public class Notificacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notId;

    @Column(updatable = false)
    private String notDescricao;

    @Column
    private boolean notAtiva;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date notDtCadastro;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date notDtAtualizacao;

    @Column
    private Long notPesAtualizacao;

    @Column(updatable = false)
    private Long notPesCadastro;

    @Column(updatable = false)
    private Boolean notEnviado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pesId", updatable = false)
    private Pessoa notPessoa;

    public Notificacao() {
    }

    public Notificacao(Long notId, String notDescricao, boolean notAtiva, Date notDtCadastro, Date notDtAtualizacao,
            Long notPesAtualizacao, Long notPesCadastro, Boolean notEnviado, Pessoa notPessoa) {
        this.notId = notId;
        this.notDescricao = notDescricao;
        this.notAtiva = notAtiva;
        this.notDtCadastro = notDtCadastro;
        this.notDtAtualizacao = notDtAtualizacao;
        this.notPesAtualizacao = notPesAtualizacao;
        this.notPesCadastro = notPesCadastro;
        this.notEnviado = notEnviado;
        this.notPessoa = notPessoa;
    }

    public Notificacao(NotificacaoDTO notificacao) {
        this.notId = notificacao.getNotId();
        this.notDescricao = notificacao.getNotDescricao();
        this.notAtiva = notificacao.isNotAtiva();
        this.notDtCadastro = notificacao.getNotDtCadastro();
        this.notDtAtualizacao = Calendar.getInstance().getTime();
        this.notPesAtualizacao = notificacao.getNotPesAtualizacao();
        this.notPesCadastro = notificacao.getNotPesCadastro();
        this.notEnviado = notEnviado;
        this.notPessoa = notificacao.getNotPessoa();
    }

    public Long getNotId() {
        return notId;
    }

    public void setNotId(Long notId) {
        this.notId = notId;
    }

    public String getNotDescricao() {
        return notDescricao;
    }

    public void setNotDescricao(String notDescricao) {
        this.notDescricao = notDescricao;
    }

    public boolean isNotAtiva() {
        return notAtiva;
    }

    public void setNotAtiva(boolean notAtiva) {
        this.notAtiva = notAtiva;
    }

    public Date getNotDtCadastro() {
        return notDtCadastro;
    }

    public void setNotDtCadastro(Date notDtCadastro) {
        this.notDtCadastro = notDtCadastro;
    }

    public Date getNotDtAtualizacao() {
        return notDtAtualizacao;
    }

    public void setNotDtAtualizacao(Date notDtAtualizacao) {
        this.notDtAtualizacao = notDtAtualizacao;
    }

    public Long getNotPesAtualizacao() {
        return notPesAtualizacao;
    }

    public void setNotPesAtualizacao(Long notPesAtualizacao) {
        this.notPesAtualizacao = notPesAtualizacao;
    }

    public Pessoa getNotPessoa() {
        return notPessoa;
    }

    public void setNotPessoa(Pessoa notPessoa) {
        this.notPessoa = notPessoa;
    }

    public Long getNotPesCadastro() {
        return notPesCadastro;
    }

    public void setNotPesCadastro(Long notPesCadastro) {
        this.notPesCadastro = notPesCadastro;
    }

    public Boolean getNotEnviado() {
        return notEnviado;
    }

    public void setNotEnviado(Boolean notEnviado) {
        this.notEnviado = notEnviado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.notId);
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
        final Notificacao other = (Notificacao) obj;
        if (!Objects.equals(this.notId, other.notId)) {
            return false;
        }
        return true;
    }

}
