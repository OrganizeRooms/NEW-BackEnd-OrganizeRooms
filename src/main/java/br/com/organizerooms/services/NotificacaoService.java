/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.services;

import br.com.organizerooms.models.Notificacao;
import br.com.organizerooms.models.Pessoa;
import br.com.organizerooms.repositorios.NotificacaoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aluno
 */
@Service
public class NotificacaoService {
    
    @Autowired
    NotificacaoRepository repository;
    
    public Notificacao persiste (Notificacao notificacao){
        return this.repository.save(notificacao);
    };
    
    public Notificacao buscaPorId (Long id){
        return this.repository.findById(id).get();
    };
    
    public List<Notificacao> buscaPorPessoaAtivas (Pessoa pessoa){
        return this.repository.findByNotPessoaAndNotAtiva(pessoa, true);
    };
}
