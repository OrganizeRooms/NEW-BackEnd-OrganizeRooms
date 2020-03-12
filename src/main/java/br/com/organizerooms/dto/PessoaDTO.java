package br.com.organizerooms.dto;

import br.com.organizerooms.enums.PerfilEnum;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.models.Unidade;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Leandro Prado
 */
public class PessoaDTO {

    private Long pesId;

    private String pesNome;

    private String pesEmail;

    private PerfilEnum pesPermissao;

    private String pesDescricaoPermissao;

    private Unidade pesUnidade;

    private String pesDdd;

    private String pesTelefone;

    // SIS = Cadastro manual
    // IMP = Por Importação
    private String pesTipoInclusao;

    private Long pesCadastro;

    private Date pesDtCadastro;

    private Long pesAtualizacao;

    private Date pesDtAtualizacao;

    public PessoaDTO() {
    }

    public PessoaDTO(Long pesId, String pesNome, String pesEmail, PerfilEnum pesPermissao, String pesDescricaoPermissao, Unidade pesUnidade,
            String pesDdd, String pesTelefone, String pesTipoInclusao, Long pesCadastro, Date pesDtCadastro, Long pesAtualizacao, Date pesDtAtualizacao) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesEmail = pesEmail;
        this.pesPermissao = pesPermissao;
        this.pesDescricaoPermissao = pesDescricaoPermissao;
        this.pesUnidade = pesUnidade;
        this.pesDdd = pesDdd;
        this.pesTelefone = pesTelefone;
        this.pesTipoInclusao = pesTipoInclusao;
        this.pesCadastro = pesCadastro;
        this.pesDtCadastro = pesDtCadastro;
        this.pesAtualizacao = pesAtualizacao;
        this.pesDtAtualizacao = pesDtAtualizacao;
    }

    public PessoaDTO(Pessoa obj) {
        this.pesId = obj.getPesId();
        this.pesNome = obj.getPesNome();
        this.pesEmail = obj.getPesEmail();
        this.pesPermissao = obj.getPesPermissao();
        this.pesDescricaoPermissao = this.getPesDescricaoPermissao();
        this.pesUnidade = obj.getPesUnidade();
        this.pesDdd = obj.getPesDdd();
        this.pesTelefone = obj.getPesTelefone();
        this.pesTipoInclusao = obj.getPesTipoInclusao();
        this.pesCadastro = obj.getPesCadastro();
        this.pesDtCadastro = obj.getPesDtCadastro();
        this.pesAtualizacao = obj.getPesAtualizacao();
        this.pesDtAtualizacao = obj.getPesDtAtualizacao();
    }

    public String getPesDescricaoPermissao() {
        if (this.pesPermissao.equals(PerfilEnum.ROLE_ADMIN)) {
            return "Administrador";
        }
        if (this.pesPermissao.equals(PerfilEnum.ROLE_USUARIO)) {
            return "Usuario";
        }
        if (this.pesPermissao.equals(PerfilEnum.ROLE_TABLET)) {
            return "Tablet";
        }
        return "";
    }

    public void setPesDescricaoPermissao(String pesDescricaoPermissao) {
        this.pesDescricaoPermissao = pesDescricaoPermissao;
    }

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public String getPesEmail() {
        return pesEmail;
    }

    public void setPesEmail(String pesEmail) {
        this.pesEmail = pesEmail;
    }

    public PerfilEnum getPesPermissao() {
        return pesPermissao;
    }

    public void setPesPermissao(PerfilEnum pesPermissao) {
        this.pesPermissao = pesPermissao;
    }

    public Unidade getPesUnidade() {
        return pesUnidade;
    }

    public void setPesUnidade(Unidade pesUnidade) {
        this.pesUnidade = pesUnidade;
    }

    public String getPesDdd() {
        return pesDdd;
    }

    public void setPesDdd(String pesDdd) {
        this.pesDdd = pesDdd;
    }

    public String getPesTelefone() {
        return pesTelefone;
    }

    public void setPesTelefone(String pesTelefone) {
        this.pesTelefone = pesTelefone;
    }

    public String getPesTipoInclusao() {
        return pesTipoInclusao;
    }

    public void setPesTipoInclusao(String pesTipoInclusao) {
        this.pesTipoInclusao = pesTipoInclusao;
    }

    public Long getPesCadastro() {
        return pesCadastro;
    }

    public void setPesCadastro(Long pesCadastro) {
        this.pesCadastro = pesCadastro;
    }

    public Date getPesDtCadastro() {
        return pesDtCadastro;
    }

    public void setPesDtCadastro(Date pesDtCadastro) {
        this.pesDtCadastro = pesDtCadastro;
    }

    public Long getPesAtualizacao() {
        return pesAtualizacao;
    }

    public void setPesAtualizacao(Long pesAtualizacao) {
        this.pesAtualizacao = pesAtualizacao;
    }

    public Date getPesDtAtualizacao() {
        return pesDtAtualizacao;
    }

    public void setPesDtAtualizacao(Date pesDtAtualizacao) {
        this.pesDtAtualizacao = pesDtAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.pesId);
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
        final PessoaDTO other = (PessoaDTO) obj;
        if (!Objects.equals(this.pesId, other.pesId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PessoaDTO{" + "pesId=" + pesId + ", pesNome=" + pesNome + ", pesEmail=" + pesEmail + ", pesPermissao=" + pesPermissao + ", pesDescricaoPermissao=" + pesDescricaoPermissao + ", pesUnidade=" + pesUnidade + ", pesDdd=" + pesDdd + ", pesTelefone=" + pesTelefone + ", pesTipoInclusao=" + pesTipoInclusao + ", pesCadastro=" + pesCadastro + ", pesDtCadastro=" + pesDtCadastro + ", pesAtualizacao=" + pesAtualizacao + ", pesDtAtualizacao=" + pesDtAtualizacao + '}';
    }

}
