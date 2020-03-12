package br.com.organizerooms.dto;

import java.util.Date;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Sala;
import br.com.organizerooms.models.Agendamento;
import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Participante;
import br.com.organizerooms.models.ReservaEquipamento;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eder Jean Dias
 */
public class AgendamentoDTO {

    private Long ageId;
    private String ageAssunto;
    private String ageDescricao;
    private String ageStatus;
    private Date ageData;
    private Date ageHoraInicio;
    private Date ageHoraFim;
    private Date ageDtCadastro;
    private Date ageDtAtualizacao;
    private Sala ageSala;
    private Pessoa agePesResponsavel;
    private Long agePesCadastro;
    private Long agePesAtualizacao;
    private List<ReservaEquipamento> ageEquipamentos;
    private List<Participante> ageParticipantes;

    public AgendamentoDTO() {
    }

    public AgendamentoDTO(Long ageId, String ageAssunto, String ageDescricao, String ageStatus,
            Date ageData, Date ageHoraInicio, Date ageHoraFim, Date ageDtCadastro, Date ageDtAtualizacao,
            Sala ageSala, Pessoa agePesResponsavel, Long agePesCadastro, Long agePesAtualizacao,
            List<ReservaEquipamento> ageEquipamentos, List<Participante> ageParticipantes) {
        this.ageId = ageId;
        this.ageAssunto = ageAssunto;
        this.ageDescricao = ageDescricao;
        this.ageStatus = ageStatus;
        this.ageData = ageData;
        this.ageHoraInicio = ageHoraInicio;
        this.ageHoraFim = ageHoraFim;
        this.ageDtCadastro = ageDtCadastro;
        this.ageDtAtualizacao = ageDtAtualizacao;
        this.ageSala = ageSala;
        this.agePesResponsavel = agePesResponsavel;
        this.agePesCadastro = agePesCadastro;
        this.agePesAtualizacao = agePesAtualizacao;
        this.ageEquipamentos = ageEquipamentos;
        this.ageParticipantes = ageParticipantes;
    }

    public AgendamentoDTO(Agendamento obj) {
        this.ageId = obj.getAgeId();
        this.ageAssunto = obj.getAgeAssunto();
        this.ageDescricao = obj.getAgeDescricao();
        this.ageStatus = obj.getAgeStatus();
        this.ageData = obj.getAgeData();
        this.ageHoraInicio = obj.getAgeHoraInicio();
        this.ageHoraFim = obj.getAgeHoraFim();
        this.ageDtCadastro = obj.getAgeDtCadastro();
        this.ageDtAtualizacao = obj.getAgeDtAtualizacao();
        this.ageSala = obj.getAgeSala();
        this.agePesResponsavel = obj.getAgePesResponsavel();
        this.agePesCadastro = obj.getAgePesCadastro();
        this.agePesAtualizacao = obj.getAgePesAtualizacao();
        this.ageEquipamentos = obj.getEquipamentos();
        this.ageParticipantes = obj.getParticipantes();
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public String getAgeAssunto() {
        return ageAssunto;
    }

    public void setAgeAssunto(String ageAssunto) {
        this.ageAssunto = ageAssunto;
    }

    public String getAgeDescricao() {
        return ageDescricao;
    }

    public void setAgeDescricao(String ageDescricao) {
        this.ageDescricao = ageDescricao;
    }

    public String getAgeStatus() {
        return ageStatus;
    }

    public void setAgeStatus(String ageStatus) {
        this.ageStatus = ageStatus;
    }

    public Date getAgeData() {
        return ageData;
    }

    public void setAgeData(Date ageData) {
        this.ageData = ageData;
    }

    public Date getAgeHoraInicio() {
        return ageHoraInicio;
    }

    public void setAgeHoraInicio(Date ageHoraInicio) {
        this.ageHoraInicio = ageHoraInicio;
    }

    public Date getAgeHoraFim() {
        return ageHoraFim;
    }

    public void setAgeHoraFim(Date ageHoraFim) {
        this.ageHoraFim = ageHoraFim;
    }

    public Date getAgeDtCadastro() {
        return ageDtCadastro;
    }

    public void setAgeDtCadastro(Date ageDtCadastro) {
        this.ageDtCadastro = ageDtCadastro;
    }

    public Date getAgeDtAtualizacao() {
        return ageDtAtualizacao;
    }

    public void setAgeDtAtualizacao(Date ageDtAtualizacao) {
        this.ageDtAtualizacao = ageDtAtualizacao;
    }

    public Sala getAgeSala() {
        return ageSala;
    }

    public void setAgeSala(Sala ageSala) {
        this.ageSala = ageSala;
    }

    public Pessoa getAgePesResponsavel() {
        return agePesResponsavel;
    }

    public void setAgePesResponsavel(Pessoa agePesResponsavel) {
        this.agePesResponsavel = agePesResponsavel;
    }

    public Long getAgePesCadastro() {
        return agePesCadastro;
    }

    public void setAgePesCadastro(Long agePesCadastro) {
        this.agePesCadastro = agePesCadastro;
    }

    public Long getAgePesAtualizacao() {
        return agePesAtualizacao;
    }

    public void setAgePesAtualizacao(Long agePesAtualizacao) {
        this.agePesAtualizacao = agePesAtualizacao;
    }

    public List<ReservaEquipamento> getAgeEquipamentos() {
        return ageEquipamentos;
    }

    public void setAgeEquipamentos(List<ReservaEquipamento> ageEquipamentos) {
        this.ageEquipamentos = ageEquipamentos;
    }

    public List<Participante> getAgeParticipantes() {
        return ageParticipantes;
    }

    public void setAgeParticipantes(List<Participante> ageParticipantes) {
        this.ageParticipantes = ageParticipantes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.ageId);
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
        final AgendamentoDTO other = (AgendamentoDTO) obj;
        if (!Objects.equals(this.ageId, other.ageId)) {
            return false;
        }
        return true;
    }

}
