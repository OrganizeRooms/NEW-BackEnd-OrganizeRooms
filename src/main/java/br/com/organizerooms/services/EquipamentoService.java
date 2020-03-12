/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.services;

import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Unidade;
import br.com.organizerooms.repositorios.EquipamentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aluno
 */
@Service
public class EquipamentoService {

    @Autowired
    EquipamentoRepository repository;

    public Equipamento persiste(Equipamento equipamento) {
        return this.repository.save(equipamento);
    }

    public void remover(Long id) {
        this.repository.deleteById(id);
    }

    public Equipamento buscaPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public List<Equipamento> buscaPorSituacao() {
        return this.repository.findByEquAtiva(true);
    }

    public List<Equipamento> buscaTodos() {
        return this.repository.findAll();
    }

    public List<Equipamento> buscarPorUnidade(Unidade unidade) {
        return this.repository.findAllByEquUnidade(unidade);
    }
}
