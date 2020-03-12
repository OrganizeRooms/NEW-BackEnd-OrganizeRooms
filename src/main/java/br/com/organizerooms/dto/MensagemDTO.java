package br.com.organizerooms.dto;

import br.com.organizerooms.models.*;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Lucas Jansen
 */
public class MensagemDTO {

    private Long menId;
    private String menDescricao;
    private String menMensagem;
    private Integer menTipo;
    private Date menDtAtualizacao;
    private Long menPesAtualizacao;

    public MensagemDTO() {
    }

    public MensagemDTO(Long menId, String menDescricao, String menMensagem, Integer menTipo, Date menDtAtualizacao, Long menPesAtualizacao) {
        this.menId = menId;
        this.menDescricao = menDescricao;
        this.menMensagem = menMensagem;
        this.menTipo = menTipo;
        this.menDtAtualizacao = menDtAtualizacao;
        this.menPesAtualizacao = menPesAtualizacao;
    }

    public MensagemDTO(Mensagem mensagem) {
        this.menId = mensagem.getMenId();
        this.menMensagem = mensagem.getMenMensagem();
        this.menDescricao = mensagem.getMenDescricao();
        this.menTipo = mensagem.getMenTipo();
        this.menDtAtualizacao = mensagem.getMenDtAtualizacao();
        this.menPesAtualizacao = mensagem.getMenPesAtualizacao();
    }

    public Long getMenId() {
        return menId;
    }

    public void setMenId(Long menId) {
        this.menId = menId;
    }

    public String getMenMensagem() {
        return menMensagem;
    }

    public void setMenMensagem(String menMensagem) {
        this.menMensagem = menMensagem;
    }

    public String getMenDescricao() {
        return menDescricao;
    }

    public void setMenDescricao(String menDescricao) {
        this.menDescricao = menDescricao;
    }

    public Integer getMenTipo() {
        return menTipo;
    }

    public void setMenTipo(Integer menTipo) {
        this.menTipo = menTipo;
    }

    public Date getMenDtAtualizacao() {
        return menDtAtualizacao;
    }

    public void setMenDtAtualizacao(Date menDtAtualizacao) {
        this.menDtAtualizacao = menDtAtualizacao;
    }

    public Long getMenPesAtualizacao() {
        return menPesAtualizacao;
    }

    public void setMenPesAtualizacao(Long menPesAtualizacao) {
        this.menPesAtualizacao = menPesAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.menId);
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
        final MensagemDTO other = (MensagemDTO) obj;
        if (!Objects.equals(this.menId, other.menId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MensagemDTO{" + "menId=" + menId + ", menMensagem=" + menMensagem + ", menTipo=" + menTipo + ", menDtAtualizacao=" + menDtAtualizacao + ", menPesAtualizacao=" + menPesAtualizacao + '}';
    }

}
