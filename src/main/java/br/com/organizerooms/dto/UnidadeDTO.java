package br.com.organizerooms.dto;

import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Unidade;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Lucas Jansen
 */
public class UnidadeDTO {

    private Long uniId;

    private String uniNome;

    private Boolean uniAtiva;

    private Date uniDtCadastro;

    private Long uniPesCadastro;

    private Date uniDtAtualizacao;

    private Long uniPesAtualizacao;

    public UnidadeDTO() {
    }

    public UnidadeDTO(Long uniId, String uniNome, Boolean uniAtiva, Date uniDtCadastro,
            Long uniPesCadastro, Date uniDtAtualizacao, Long uniPesAtualizacao) {
        this.uniId = uniId;
        this.uniNome = uniNome;
        this.uniAtiva = uniAtiva;
        this.uniDtCadastro = uniDtCadastro;
        this.uniPesCadastro = uniPesCadastro;
        this.uniDtAtualizacao = uniDtAtualizacao;
        this.uniPesAtualizacao = uniPesAtualizacao;
    }

    public UnidadeDTO(Unidade obj) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.uniId);
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
        final UnidadeDTO other = (UnidadeDTO) obj;
        if (!Objects.equals(this.uniId, other.uniId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UnidadeDTO{" + "uniId=" + uniId + ", uniNome=" + uniNome + ", uniAtiva=" + uniAtiva + ", uniDtCadastro=" + uniDtCadastro + ", uniPesCadastro=" + uniPesCadastro + ", uniDtAtualizacao=" + uniDtAtualizacao + ", uniPesAtualizacao=" + uniPesAtualizacao + '}';
    }

}
