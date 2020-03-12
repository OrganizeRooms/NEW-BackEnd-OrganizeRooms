/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Notificacao;
import br.com.organizerooms.models.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aluno
 */
@Repository
@Transactional(readOnly = true )
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    public List<Notificacao> findByNotPessoaAndNotAtiva(Pessoa pessoa, Boolean ativa);
    
}
