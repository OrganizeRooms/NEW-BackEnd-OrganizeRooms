package br.com.organizerooms.models;

import br.com.organizerooms.dto.MensagemDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Lucas Jansen
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "mensagem")
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menId;

    @Column
    private String menDescricao;
    
    @Column
    private String menMensagem;

    /*
     ***** TIPOS ******
     1 = Assunto e-mail agendamento
     2 = E-mail agendamento Participante Padrão
     3 = E-mail agendamento Participante Obrigatório
     */
    @Column
    private Integer menTipo;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date menDtAtualizacao;

    @Column
    private Long menPesAtualizacao;

    public Mensagem() {
    }

    public Mensagem(Long menId, String menDescricao, String menMensagem, Integer menTipo, Date menDtAtualizacao, Long menPesAtualizacao) {
        this.menId = menId;
        this.menDescricao = menDescricao;
        this.menMensagem = menMensagem;
        this.menTipo = menTipo;
        this.menDtAtualizacao = menDtAtualizacao;
        this.menPesAtualizacao = menPesAtualizacao;
    }

    

    public Mensagem(MensagemDTO mensagem) {
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

    public String getMenDescricao() {
        return menDescricao;
    }

    public void setMenDescricao(String menDescricao) {
        this.menDescricao = menDescricao;
    }

    
    public String getMenMensagem() {
        return menMensagem;
    }

    public void setMenMensagem(String menMensagem) {
        this.menMensagem = menMensagem;
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
        hash = 43 * hash + Objects.hashCode(this.menId);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.menId, other.menId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "menId=" + menId + ", menMensagem=" + menMensagem + ", menTipo=" + menTipo + ", menDtAtualizacao=" + menDtAtualizacao + ", menPesAtualizacao=" + menPesAtualizacao + '}';
    }

}
