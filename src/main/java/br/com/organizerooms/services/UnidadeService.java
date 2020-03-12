/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.services;

import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.repositorios.UnidadeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leandro Prado
 */
@Service
public class UnidadeService {

    @Autowired
    UnidadeRepository repository;

    public List<Unidade> buscarTodasUnidades() {
        return this.repository.findAllByOrderByUniNome();
    }

    public void remover(Long id) {
        this.repository.deleteById(id);
    }

    public Unidade addUnidade(Unidade unidade) {
        return this.repository.save(unidade);
    }

    public Unidade buscarUnidadePorId(Long id) {
        return this.repository.findById(id).get();
    }

    public  List<Unidade> buscaPorSituacao (){
        return this.repository.findByUniAtiva(true);
    };
}
