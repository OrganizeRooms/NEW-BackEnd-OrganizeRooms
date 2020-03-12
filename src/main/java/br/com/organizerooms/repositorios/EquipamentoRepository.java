/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organizerooms.repositorios;

import br.com.organizerooms.models.Equipamento;
import br.com.organizerooms.models.Unidade;
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
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    public  List<Equipamento> findByEquAtiva(boolean ativo);
    public List<Equipamento> findAllByEquUnidade(Unidade unidade);
    
}
