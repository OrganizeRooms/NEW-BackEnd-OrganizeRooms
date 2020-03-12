package br.com.organizerooms.models;

import br.com.organizerooms.dto.UnidadeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Lucas Jansen
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "unidade")
public class Unidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniId;

    @Column
    private String uniNome;

    @Column
    private Boolean uniAtiva;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date uniDtCadastro;

    @Column(updatable = false)
    private Long uniPesCadastro;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date uniDtAtualizacao;

    @Column
    private Long uniPesAtualizacao;

    //
    @JsonIgnore
    @OneToMany(mappedBy = "pesUnidade")
    private List<Pessoa> pessoas;

    @JsonIgnore
    @OneToMany(mappedBy = "equUnidade")
    private List<Equipamento> equipamentos;

    @JsonIgnore
    @OneToMany(mappedBy = "salaUnidade")
    private List<Sala> salas;

    public Unidade() {
    }

    public Unidade(Long uniId, String uniNome, Boolean uniAtiva, Date uniDtCadastro,
            Long uniPesCadastro, Date uniDtAtualizacao, Long uniPesAtualizacao) {
        this.uniId = uniId;
        this.uniNome = uniNome;
        this.uniAtiva = uniAtiva;
        this.uniDtCadastro = uniDtCadastro;
        this.uniPesCadastro = uniPesCadastro;
        this.uniDtAtualizacao = uniDtAtualizacao;
        this.uniPesAtualizacao = uniPesAtualizacao;
    }

    public Unidade(UnidadeDTO obj) {
        this.uniId = obj.getUniId();
        this.uniNome = obj.getUniNome();
        this.uniAtiva = obj.getUniAtiva();
        this.uniDtCadastro = obj.getUniDtCadastro();
        this.uniPesCadastro = obj.getUniPesCadastro();
        this.uniDtAtualizacao = obj.getUniDtAtualizacao();
        this.uniPesAtualizacao = obj.getUniPesAtualizacao();
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public String getUniNome() {
        return uniNome;
    }

    public void setUniNome(String uniNome) {
        this.uniNome = uniNome;
    }

    public Boolean getUniAtiva() {
        return uniAtiva;
    }

    public void setUniAtiva(Boolean uniAtiva) {
        this.uniAtiva = uniAtiva;
    }

    public Date getUniDtCadastro() {
        return uniDtCadastro;
    }

    public void setUniDtCadastro(Date uniDtCadastro) {
        this.uniDtCadastro = uniDtCadastro;
    }

    public Date getUniDtAtualizacao() {
        return uniDtAtualizacao;
    }

    public void setUniDtAtualizacao(Date uniDtAtualizacao) {
        this.uniDtAtualizacao = uniDtAtualizacao;
    }

    public Long getUniPesCadastro() {
        return uniPesCadastro;
    }

    public void setUniPesCadastro(Long uniPesCadastro) {
        this.uniPesCadastro = uniPesCadastro;
    }

    public Long getUniPesAtualizacao() {
        return uniPesAtualizacao;
    }

    public void setUniPesAtualizacao(Long uniPesAtualizacao) {
        this.uniPesAtualizacao = uniPesAtualizacao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.uniId);
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
        final Unidade other = (Unidade) obj;
        if (!Objects.equals(this.uniId, other.uniId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unidade{" + "uniId=" + uniId + ", uniNome=" + uniNome + ", uniAtiva=" + uniAtiva + ", uniDtCadastro=" + uniDtCadastro + ", uniPesCadastro=" + uniPesCadastro + ", uniDtAtualizacao=" + uniDtAtualizacao + ", uniPesAtualizacao=" + uniPesAtualizacao + ", pessoas=" + pessoas + '}';
    }

}
