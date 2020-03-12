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
 * @author Felipe Haag
 */
public class EquipamentoDTO {

    private Long equId;
    private String equNome;
    private String equDescricao;
    private Unidade equUnidade;
    private Boolean equAtiva;
    private Long equPesCadastro;
    private Date equDtCadastro;
    private Date equDtAtualizacao;
    private Long equPesAtualizacao;

    public EquipamentoDTO() {
    }

    public EquipamentoDTO(Long equId, String equNome, String equDescricao, Unidade equUnidade, Boolean equAtiva,
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

    public EquipamentoDTO(Equipamento equipamento) {
        this.equId = equipamento.getEquId();
        this.equNome = equipamento.getEquNome();
        this.equDescricao = equipamento.getEquDescricao();
        this.equUnidade = equipamento.getEquUnidade();
        this.equAtiva = equipamento.isEquAtiva();
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
        final EquipamentoDTO other = (EquipamentoDTO) obj;
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
