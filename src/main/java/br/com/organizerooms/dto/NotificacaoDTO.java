/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.dto;

import br.com.organizerooms.models.*;
import java.util.Calendar;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Felipe
 */
public class NotificacaoDTO {

    private Long notId;
    private String notDescricao;
    private Boolean notAtiva;
    private Date notDtCadastro;
    private Date notDtAtualizacao;
    private Long notPesAtualizacao;
    private Pessoa notPessoa;
    private Long notPesCadastro;
    private Boolean notEnviado;
    private EnviaEmailDTO enviaEmail;

    public NotificacaoDTO() {
    }

    public NotificacaoDTO(Long notId, String notDescricao, Boolean notAtiva, Date notDtCadastro, Date notDtAtualizacao, Long notPesAtualizacao, Pessoa notPessoa, Long notPesCadastro, Boolean notEnviado, EnviaEmailDTO enviaEmail) {
        this.notId = notId;
        this.notDescricao = notDescricao;
        this.notAtiva = notAtiva;
        this.notDtCadastro = notDtCadastro;
        this.notDtAtualizacao = notDtAtualizacao;
        this.notPesAtualizacao = notPesAtualizacao;
        this.notPessoa = notPessoa;
        this.notPesCadastro = notPesCadastro;
        this.notEnviado = notEnviado;
        this.enviaEmail = enviaEmail;
    }

    public NotificacaoDTO(Notificacao notificacao) {
        this.notId = notificacao.getNotId();
        this.notDescricao = notificacao.getNotDescricao();
        this.notAtiva = notificacao.isNotAtiva();
        this.notDtCadastro = notificacao.getNotDtCadastro();
        this.notDtAtualizacao = Calendar.getInstance().getTime();
        this.notPesAtualizacao = notificacao.getNotPesAtualizacao();
        this.notPessoa = notificacao.getNotPessoa();
        this.notPesCadastro = notificacao.getNotPesCadastro();
        this.notEnviado = notificacao.getNotEnviado();
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

    public Boolean isNotAtiva() {
        return notAtiva;
    }

    public void setNotAtiva(Boolean notAtiva) {
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

    public EnviaEmailDTO getEnviaEmail() {
        return enviaEmail;
    }

    public void setEnviaEmail(EnviaEmailDTO enviaEmail) {
        this.enviaEmail = enviaEmail;
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
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.notId);
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
        final NotificacaoDTO other = (NotificacaoDTO) obj;
        if (!Objects.equals(this.notId, other.notId)) {
            return false;
        }
        return true;
    }

}
